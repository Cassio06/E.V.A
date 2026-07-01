public class Produto {
    private String nome;
    private double precoVenda;
    private double precoCompra;
    private int quantidade;


    public Produto(String nome, double precoCompra, double precoVenda, int quantidade){
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

    public void repor(int qtd){
        if(qtd > 0){
            quantidade += qtd;
        } else {
            System.out.println("Valor invalido");

        }

    }
    public boolean vender(int qtd){
        if(qtd > 0 && qtd <= quantidade){
            quantidade -= qtd;
            return true;
        }if(qtd<=0) {
            System.out.println("Valor invalido");
            return false;
        }else {
            System.out.println("Estoque insuficiente");
            return false;
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
    public void exibirResumo(){
        System.out.println("Produto: " + nome);
        System.out.println("Preço Venda: " + precoVenda);
        System.out.println("Preco Pago: " + precoCompra);
        System.out.println("Quantidade: " + quantidade);
        System.out.printf("Valor Total em Estoque: %.2f%n", valorTotalEmEstoque());
        System.out.printf("Custo Total em Estoque: %.2f%n", custoTotalEmEstoque());
        System.out.printf("Lucro por unidade: %.2f%n", lucroProduto());
        System.out.printf("Lucro Total: %.2f%n", lucroTotalPossivel());

    }
}
