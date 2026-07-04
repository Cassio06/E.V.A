import java.lang.reflect.Array;
import java.util.ArrayList;



public class Estoque {
    private ArrayList<Produto> produtos;

    public Estoque(){
        produtos = new ArrayList<>();

    }

    public void adicionarProduto(Produto produto){
        Produto produtoExistente = buscarProduto(produto.getNome());
        if(produtoExistente != null){
            System.out.println("Produto já cadastrado: " + produto.getNome());
            return;

        }

            produtos.add(produto);
            System.out.println("Produto adicionado: " + produto.getNome());

    }

    public void listarProdutos(){
        if (produtos.isEmpty()){
            System.out.println("Estoque Vazio.");

        } else{
            for (Produto produto : produtos){
                produto.exibirResumo();
                System.out.println("-----------------------");
            }
        }

    }
    public Produto buscarProduto(String nome){
        for (Produto produto : produtos){
            if (produto.getNome().trim().equalsIgnoreCase(nome.trim())) {
                return produto;
            }
        }
        return null;
    }

    public void reporProduto(String nome, int qtd){
        Produto produto = buscarProduto(nome);


        if(produto == null){
            System.out.println("Produto não encontrado.");
            return;
        }

        boolean reposicaoRealizada = produto.repor(qtd);

        if(reposicaoRealizada){
            System.out.println("Reposição de produto feita para " + produto.getNome());

        }else{
            System.out.println("Quantidade inválida para reposição");
        }
    }
    public void venderProduto(String nome, int qtd){
        Produto produto = buscarProduto(nome);

        if(produto == null){
            System.out.println("Produto não encontrado");
            return;
        }
        boolean vendaRealizada = produto.vender(qtd);

        if(vendaRealizada){
            System.out.println("Venda processada para: " + produto.getNome());

        }else{
            System.out.println("Venda não realizada. Verifique a quantidade");

        }
    }
    public double valorTotaldoEstoque(){
        double total = 0;

        for(Produto produto : produtos){
            total += produto.valorTotalEmEstoque();
        }
        return total;

    }
    public double custoTotaldoEstoque(){
        double total = 0;
        for(Produto produto : produtos){
            total += produto.custoTotalEmEstoque();
        }
        return total;
    }
    public double lucroTotaldoEstoque(){
        double total = 0;
        for(Produto produto : produtos){
            total += produto.lucroTotalPossivel();

        }
        return total;

    }

    public void exibirResumoGeral(){
        System.out.println("=====RESUMO GERAL DO ESTOQUE=====");
        System.out.printf("Valor total em estoque: R$ %.2f%n", valorTotaldoEstoque());
        System.out.printf("Custo total em estoque: R$ %.2f%n", custoTotaldoEstoque());
        System.out.printf("Lucro total possivel: R$ %.2f%n", lucroTotaldoEstoque());
        System.out.println("=================================");
    }

}
