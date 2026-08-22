package br.com.cassio.eva;


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
            comando.setDouble(2, produto.getPrecoCompra());
            comando.setDouble(3, produto.getPrecoVenda());
            comando.setInt(4, produto.getQuantidade());
            comando.setInt(5, produto.getEstoqueMinimo());
            int linhasAfetadas = comando.executeUpdate();
            return linhasAfetadas == 1;

     } catch (SQLException e) {
         System.out.println("Erro ao inserir produto: " + e.getMessage());
     }
     return false;
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
                double precoCompra = resultado.getDouble("preco_compra");
                double precoVenda = resultado.getDouble("preco_venda");
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
            System.out.println("Erro ao listar produtos: " + e.getMessage());
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
                    double precoCompra = resultado.getDouble("preco_compra");
                    double precoVenda = resultado.getDouble("preco_venda");
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
            System.out.println("Erro ao buscar produto: " + e.getMessage());
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
            System.out.println("Erro ao atualizar a quantidade: " + e.getMessage());
        }
        return false;
    }
}
