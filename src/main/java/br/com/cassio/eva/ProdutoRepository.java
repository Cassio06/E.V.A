package br.com.cassio.eva;


import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;


public class ProdutoRepository {

    public boolean inserir(Produto produto){

     String sql = "INSERT INTO produtos (nome, preco_compra, preco_venda, quantidade, estoque_minimo ) VALUES (?, ?, ?, ?, ?)";

     try(Connection conexao = ConexaoBanco.conectar();
     PreparedStatement comando = conexao.prepareStatement(sql))
        {
            comando.setString(1, produto.getNome());
            comando.setBigDecimal(2, produto.getPrecoCompra());
            comando.setBigDecimal(3, produto.getPrecoVenda());
            comando.setInt(4, produto.getQuantidade());
            comando.setInt(5, produto.getEstoqueMinimo());
            int linhasAfetadas = comando.executeUpdate();
            return linhasAfetadas == 1;

     } catch (SQLException e) {
         throw new FalhaPersistenciaException("Erro ao inserir produto: ", e);
     }
    }

    public List<Produto> listarAtivos(){
        List<Produto> produtos = new ArrayList<>();
        String sql = "SELECT id, nome, preco_compra, preco_venda, " +
                "quantidade, estoque_minimo " +
                "FROM produtos " +
                "WHERE ativo = TRUE " +
                "ORDER BY id";
        try(Connection conexao = ConexaoBanco.conectar();
            PreparedStatement comando = conexao.prepareStatement(sql);
            ResultSet resultado = comando.executeQuery()){
            while(resultado.next()){
                long id = resultado.getLong("id");
                String nome = resultado.getString("nome");
                BigDecimal precoCompra = resultado.getBigDecimal("preco_compra");
                BigDecimal precoVenda = resultado.getBigDecimal("preco_venda");
                int quantidade = resultado.getInt("quantidade");
                int estoqueMinimo = resultado.getInt("estoque_minimo");
                Produto produto = new Produto(
                  id,
                  nome,
                  precoCompra,
                  precoVenda,
                  quantidade,
                  estoqueMinimo
                );
                produtos.add(produto);
            }
        }catch (SQLException e){
            throw new FalhaPersistenciaException("Falha ao consultar os produtos.", e);
        }
        return produtos;
    }
    public Produto buscarPorId (long id){
        String sql = "SELECT id, nome, preco_compra, preco_venda, quantidade, estoque_minimo " +
                "FROM produtos " +
                "WHERE id = ? AND ativo = TRUE";
        if(id <=0){
            return null;
        }
        try(Connection conexao = ConexaoBanco.conectar();
            PreparedStatement comando = conexao.prepareStatement(sql)){
            comando.setLong(1, id);
            try(ResultSet resultado = comando.executeQuery()){
                if(resultado.next()){
                    long idEncontrado = resultado.getLong("id");
                    String nome = resultado.getString("nome");
                    BigDecimal precoCompra = resultado.getBigDecimal("preco_compra");
                    BigDecimal precoVenda = resultado.getBigDecimal("preco_venda");
                    int quantidade = resultado.getInt("quantidade");
                    int estoqueMinimo = resultado.getInt("estoque_minimo");
                    Produto produto = new Produto(idEncontrado,
                            nome,
                            precoCompra,
                            precoVenda,
                            quantidade,
                            estoqueMinimo);
                    return produto;
                }
            }
        }catch(SQLException e){
            throw new FalhaPersistenciaException("Falha ao buscar o produto.", e);
        }

        return null;

    }

    public boolean atualizarQuantidade(long id, int novaQuantidade){
        String sql = "UPDATE produtos SET quantidade = ? " +
                "WHERE id = ? AND ativo = TRUE";
        if(id <= 0 || novaQuantidade < 0){
            return false;
        }
        try(Connection conexao = ConexaoBanco.conectar();
            PreparedStatement comando = conexao.prepareStatement(sql)){
            comando.setInt(1, novaQuantidade);
            comando.setLong(2, id);
            int linhasAfetadas = comando.executeUpdate();
            return linhasAfetadas == 1;
        }catch(SQLException e){
            throw new FalhaPersistenciaException("Erro ao atualizar a quantidade: ", e);
        }
    }

    public boolean atualizarDados(Produto produto){
        String sql = "UPDATE produtos SET nome = ?, preco_compra = ?, preco_venda = ?, estoque_minimo = ? " +
                "WHERE id = ? AND ativo = TRUE";
        if(produto == null || produto.getId() == null || produto.getId() <= 0){
            return false;
        }
        try(Connection conexao = ConexaoBanco.conectar();
        PreparedStatement comando = conexao.prepareStatement(sql)){
            comando.setString(1, produto.getNome());
            comando.setBigDecimal(2, produto.getPrecoCompra());
            comando.setBigDecimal(3, produto.getPrecoVenda());
            comando.setInt(4, produto.getEstoqueMinimo());
            comando.setLong(5, produto.getId());
            int linhasAfetadas = comando.executeUpdate();
            return linhasAfetadas == 1;
        }catch(SQLException e){
            throw new FalhaPersistenciaException("Erro ao atualizar as informações do produto: " ,e );
        }
    }
}
