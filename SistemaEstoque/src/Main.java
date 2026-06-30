public class Main{
    public static void main(String[] args){
        double valorTotalGeral = 0;
        ProdutoEstoque p1 = new ProdutoEstoque("Fonte", 219.90, 135.33, 2);
        ProdutoEstoque p2 = new ProdutoEstoque("Kz Edx Pro X", 44.90, 14.33, 5);
        ProdutoEstoque p3 = new ProdutoEstoque("B550M Plus Tuf", 749.90, 549.65, 1);

        valorTotalGeral = p1.valorTotalEmEstoque() + p2.valorTotalEmEstoque() + p3.valorTotalEmEstoque();

        p1.exibirProduto();
        p2.exibirProduto();
        p3.exibirProduto();
        System.out.printf("O valor total em estoque é: %.2f", valorTotalGeral);
    }
}