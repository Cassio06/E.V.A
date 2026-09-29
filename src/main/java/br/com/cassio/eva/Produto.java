package br.com.cassio.eva;

import java.math.BigDecimal;

public class Produto {
    private Long id;
    private String nome;
    private BigDecimal precoVenda;
    private BigDecimal precoCompra;
    private int quantidade;
    private int estoqueMinimo;

    public Produto(String nome, BigDecimal precoCompra, BigDecimal precoVenda, int quantidade, int estoqueMinimo){
        if(nome == null || nome.isBlank()){
            throw new IllegalArgumentException("O nome não pode estar vazio.");

        }
        if(precoCompra == null || precoCompra.compareTo(BigDecimal.ZERO) <= 0){
            throw new IllegalArgumentException("O preço de compra deve ser maior que zero.");
        }
        if(precoVenda == null || precoVenda.compareTo(BigDecimal.ZERO) <= 0){
            throw new IllegalArgumentException("O preço de venda deve ser maior que zero.");
        }
        if(precoVenda.compareTo(precoCompra) < 0){
            throw new IllegalArgumentException("O preço de venda não pode ser menor que o preço de compra.");
        }
        if (quantidade < 0){
            throw new IllegalArgumentException("A quantidade em estoque deve ser maior ou igual a zero.");

        }
        if (estoqueMinimo < 0){
            throw new IllegalArgumentException("O estoque mínimo deve ser maior ou igual a zero.");
        }
        this.nome = nome.trim();
        this.precoVenda = precoVenda;
        this.precoCompra = precoCompra;
        this.quantidade = quantidade;
        this.estoqueMinimo = estoqueMinimo;
    }

    public Produto(long id, String nome, BigDecimal precoCompra, BigDecimal precoVenda, int quantidade, int estoqueMinimo){
        this(nome, precoCompra, precoVenda, quantidade, estoqueMinimo);

        if(id <=0){
            throw new IllegalArgumentException("O ID deve ser maior que zero.");
        }

        this.id = id;
    }

    public Long getId(){
        return id;
    }
    public String getNome(){

        return nome;
    }
    public BigDecimal getPrecoVenda(){
        return precoVenda;

    }
    public BigDecimal getPrecoCompra(){
        return precoCompra;

    }
    public int getQuantidade(){
        return quantidade;

    }
    public enum ResultadoVenda{
        SUCESSO,
        NUMERO_INVALIDO,
        ESTOQUE_INSUFICIENTE,
        PRODUTO_NAO_ENCONTRADO
    }
    public int getEstoqueMinimo(){
        return estoqueMinimo;
    }

    public boolean estaComBaixoEstoque(){
        return quantidade <= estoqueMinimo;

    }
    public boolean repor(int qtd){
        if(qtd <= 0){
            return false;
        }

        quantidade += qtd;
        return true;


    }
    public ResultadoVenda vender(int qtd){
        if (qtd <= 0){
            return ResultadoVenda.NUMERO_INVALIDO;

        }
        if (qtd > quantidade){
            return ResultadoVenda.ESTOQUE_INSUFICIENTE;

        }

        quantidade -= qtd;
        return ResultadoVenda.SUCESSO;

    }

    public boolean alterarPrecos(BigDecimal novoPrecoCompra, BigDecimal novoPrecoVenda){
        if(novoPrecoCompra.compareTo(novoPrecoVenda) > 0 || novoPrecoCompra.compareTo(BigDecimal.ZERO) <= 0  || novoPrecoVenda.compareTo(BigDecimal.ZERO) <= 0 ){
            return false;
        }else{
            precoCompra = novoPrecoCompra;
            precoVenda = novoPrecoVenda;
            return true;
        }
    }

    public boolean alterarPrecoCompra(BigDecimal novoPrecoCompra){
        if(novoPrecoCompra.compareTo(BigDecimal.ZERO) <= 0 || novoPrecoCompra.compareTo(precoVenda) > 0){
            return false;
        }
        this.precoCompra = novoPrecoCompra;
        return true;
    }

    public boolean alterarPrecoVenda(BigDecimal novoPrecoVenda){
        if(novoPrecoVenda.compareTo(BigDecimal.ZERO) <= 0 || novoPrecoVenda.compareTo(precoCompra) < 0){
            return false;
        }
        this.precoVenda = novoPrecoVenda;
        return true;
    }

    public boolean alterarEstoqueMinimo(int novoEstoqueMinimo){
        if(novoEstoqueMinimo < 0){
            return false;

        }
        this.estoqueMinimo = novoEstoqueMinimo;
        return true;
    }

    public boolean alterarNome(String novoNome){
        if(novoNome == null || novoNome.isBlank()){
            return false;
        }
        this.nome = novoNome.trim();
        return true;

    }

    public boolean alterarEstoque(int novaQuantidade){
        if(novaQuantidade < 0){
            return false;

        }
        this.quantidade = novaQuantidade;
        return true;
    }

    public BigDecimal valorTotalEmEstoque(){
        
        return getPrecoVenda().multiply(BigDecimal.valueOf(getQuantidade()));

    }

    public BigDecimal custoTotalEmEstoque(){
        return getPrecoCompra().multiply(BigDecimal.valueOf(getQuantidade()));

    }
    public BigDecimal lucroProduto(){
        return precoVenda.subtract(precoCompra);

    }

    public BigDecimal lucroTotalPossivel(){
        return lucroProduto().multiply(BigDecimal.valueOf(getQuantidade()));

    }
}
