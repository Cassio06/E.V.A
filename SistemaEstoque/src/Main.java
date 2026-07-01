import java.util.Scanner;


public class Main{
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);

        Estoque estoque = new Estoque();

        int opcao;

        do {
            System.out.println("====== SISTEMA DE ESTOQUE ======");
            System.out.println("1 - Adicionar Produto");
            System.out.println("2 - Listar Produtos");
            System.out.println("3 - Vender Produto");
            System.out.println("4 - Repor Produto");
            System.out.println("5 - Resumo Geral");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");


            opcao = input.nextInt();
            input.nextLine();

            switch(opcao){
                case 1:
                    System.out.println("Nome do produto: ");
                    String nome = input.nextLine();

                    System.out.println("Preço pago: ");
                    double precoPago = input.nextDouble();

                    System.out.println("Preço de venda: ");
                    double precoVenda = input.nextDouble();

                    System.out.println("Quantidade: ");
                    int quantidade = input.nextInt();
                    input.nextLine();


                    Produto produto = new Produto(nome, precoPago, precoVenda, quantidade);
                    estoque.adicionarProduto(produto);


                    System.out.println("Produto adicionado com sucesso!");
                    break;

                case 2:
                    estoque.listarProdutos();
                    break;
                case 3:
                    System.out.println("Nome do produto vendido: ");
                    String nomeVenda = input.nextLine();

                    System.out.println("Quantidade vendida: ");
                    int quantidadeVenda = input.nextInt();

                    estoque.venderProduto(nomeVenda, quantidadeVenda);


                    break;
                case 4:
                    System.out.println("Nome do produto para repor: ");
                    String nomeReposicao = input.nextLine();

                    System.out.println("Quantidade para repor: ");
                    int quantidadeReposicao = input.nextInt();
                    input.nextLine();

                    estoque.reporProduto(nomeReposicao, quantidadeReposicao);
                    break;
                case 5:
                    estoque.exibirResumoGeral();
                    break;
                case 0:
                    System.out.println("Saindo");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        } while(opcao != 0);
        input.close();
    }
}