public class ProdutoEstoque {
    private String nome;
    private double precoVenda;
    private double precoCompra;
    private int quantidade;


    public ProdutoEstoque(String nome, double precoVenda, double precoCompra, int quantidade){
        this.nome = nome;
        this.precoVenda = precoVenda;
        this.precoCompra = precoCompra;
        this.quantidade = quantidade;

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

    public void adicionarEstoque(int qtd){
        if(qtd > 0){
            quantidade += qtd;
        } else {
            System.out.println("Valor invalido");

        }

    }
    public void removerEstoque(int qtd){
        if(qtd > 0 && qtd <= quantidade){
            quantidade -= qtd;

        }else {
            System.out.println("Valor invalido");
        }
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
    public void exibirProduto(){
        System.out.println("Produto: " + nome);
        System.out.println("Preço Venda: " + precoVenda);
        System.out.println("Preco Pago: " + precoCompra);
        System.out.println("Quantidade: " + quantidade);
        System.out.println("Valor Total em Estoque: " + valorTotalEmEstoque());
        System.out.println("Custo Total em Estoque: " + custoTotalEmEstoque());
        System.out.println("Lucro por unidade: " + lucroProduto());
        System.out.println("Lucro Total: " + lucroTotalPossivel());

    }
}
