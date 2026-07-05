public class Produto {
    private String nome;
    private double precoVenda;
    private double precoCompra;
    private int quantidade;
    private int estoqueMinimo;

    public Produto(String nome, double precoCompra, double precoVenda, int quantidade, int estoqueMinimo){
        this.nome = nome;
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
    public boolean vender(int qtd){
        if (qtd <= 0){
            System.out.println("Quantidade inválida para venda");
            return false;

        }
        if (qtd > quantidade){
            System.out.println("Estoque insuficiente. Quantidade disponível: " + quantidade);
            return false;

        }

        quantidade -= qtd;
        return true;

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
        if(novoNome.trim().isEmpty()){
            System.out.println("Nome não pode ficar vazio");
            return false;
        }
        this.nome = novoNome.trim();
        return true;

    }

    public boolean alterarEstoque(int novaQuantidade){
        if(novaQuantidade < 0){
            System.out.println("Quantidade Inválida.");
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
    public void exibirResumo() {
        System.out.println("Produto: " + nome);
        System.out.printf("Preço Venda: %.2f%n", precoVenda);
        System.out.printf("Preço Pago: %.2f%n", precoCompra);
        System.out.println("Estoque: " + quantidade);
        System.out.println("Estoque Minimo: " + estoqueMinimo);
        System.out.printf("Valor Total em Estoque: %.2f%n", valorTotalEmEstoque());
        System.out.printf("Custo Total em Estoque: %.2f%n", custoTotalEmEstoque());
        System.out.printf("Lucro por unidade: %.2f%n", lucroProduto());
        System.out.printf("Lucro Total: %.2f%n", lucroTotalPossivel());


        if (estaComBaixoEstoque()) {
            System.out.println("Status: ⚠ Baixo estoque!");

        }else{
            System.out.println("Status: Estoque Ok.");
        }

    }

}
