package br.com.cassio.eva;

public class Produto {
    private String nome;
    private double precoVenda;
    private double precoCompra;
    private int quantidade;
    private int estoqueMinimo;

    public Produto(String nome, double precoCompra, double precoVenda, int quantidade, int estoqueMinimo){
        if(nome == null || nome.isBlank()){
            throw new IllegalArgumentException("O nome não pode estar vazio.");

        }
        if (precoCompra <=0 ){
            throw new IllegalArgumentException("O Preço de compra não pode ser igual ou inferior a zero.");

        }
        if (precoVenda <= 0){
            throw new IllegalArgumentException("O Preço de venda não pode ser igual ou inferior a zero.");

        }
        if (precoVenda < precoCompra){
            throw new IllegalArgumentException("O Preço de venda não pode ser menor que o preço de compra.");

        }
        if (quantidade < 0){
            throw new IllegalArgumentException("A quantidade em estoque não pode ser menor do que zero.");

        }
        if (estoqueMinimo < 0){
            throw new IllegalArgumentException("O estoque minimo não pode ser menor do que zero.");
        }
        this.nome = nome.trim();
        this.precoVenda = precoVenda;
        this.precoCompra = precoCompra;
        this.quantidade = quantidade;
        this.estoqueMinimo = estoqueMinimo;
    }



    public String getNome(){

        return nome;
    }
    public double getPrecoVenda(){
        return precoVenda;

    }
    public double getPrecoCompra(){
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

    public boolean alterarPrecoCompra(double novoPrecoCompra){
        if(novoPrecoCompra <= 0 || novoPrecoCompra > precoVenda){
            return false;
        }
        this.precoCompra = novoPrecoCompra;
        return true;
    }

    public boolean alterarPrecoVenda(double novoPrecoVenda){
        if(novoPrecoVenda <= 0 || novoPrecoVenda < precoCompra){
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

    public double valorTotalEmEstoque(){
        return getQuantidade() * getPrecoVenda();

    }

    public double custoTotalEmEstoque(){
        return getPrecoCompra() * getQuantidade();

    }
    public double lucroProduto(){
        return precoVenda - precoCompra;

    }

    public double lucroTotalPossivel(){
        return lucroProduto() * getQuantidade();

    }
}
