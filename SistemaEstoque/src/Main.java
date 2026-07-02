import java.util.Scanner;


public class Main{
    static Scanner input = new Scanner(System.in);

    public static void main(String[] args){



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

            switch(opcao) {
                case 1:

                    String nome = lerTexto("Nome do produto: ");

                    double precoPago = lerDoublePositivo("Preço pago: ");

                    double precoVenda;

                    do {
                        precoVenda = lerDoublePositivo("Preço de venda: ");
                        if (precoVenda < precoPago) {
                            System.out.println("Preço de venda menor que o preço pago. Isso daria prejuízo");
                        }
                    } while (precoVenda < precoPago);

                    int quantidade = lerIntPositivo("Quantidade: ");
                    input.nextLine();


                    Produto produto = new Produto(nome, precoPago, precoVenda, quantidade);
                    estoque.adicionarProduto(produto);

                    break;

                case 2:
                    estoque.listarProdutos();
                    break;
                case 3:
                    Produto produtoVenda = null;
                    String nomeVenda = null;
                    while (produtoVenda == null) {
                        nomeVenda = lerTexto("Nome do produto vendido: ");
                        produtoVenda = estoque.buscarProduto(nomeVenda);

                        if (produtoVenda == null) {
                            System.out.println("Produto não encontrado. Digite novamente.");
                        }
                    }

                    int quantidadeVenda = lerIntPositivo("Quantidade vendida: ");

                    while(quantidadeVenda > produtoVenda.getQuantidade()){
                        System.out.println("Estoque insuficiente. Quantidade disponível: " + produtoVenda.getQuantidade());
                        quantidadeVenda = lerIntPositivo("Quantidade vendida: ");
                    }
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
    public static String lerTexto(String mensagem){
        while(true){
            System.out.println(mensagem);
            String entrada = input.nextLine().trim();
            if (!entrada.isEmpty()){
                return entrada;
            }
            System.out.println("Entrada inválida. O texto não pode ficar vazio.");
        }
    }
    public static double lerDoublePositivo(String mensagem){
        while(true){
            System.out.println(mensagem);
            String entrada = input.nextLine().trim().replace(",",".");

            try{
                double valor = Double.parseDouble(entrada);

                if(valor > 0){
                    return valor;
                }
                System.out.println("Valor invalido. Digite um número maior que zero.");

            } catch(NumberFormatException e){
                System.out.println("Entrada inválida. Digite um número válido");
            }
        }
    }
    public static int lerIntPositivo(String mensagem){
        while(true){
            System.out.println(mensagem);
            String entrada = input.nextLine().trim();

            try{
                int valor = Integer.parseInt(entrada);

                if(valor > 0){
                    return valor;
                }

                System.out.println("Quantidade invalida. Digite um número maior que zero.");

            } catch(NumberFormatException e){
                System.out.println("Entrada inválida. Digite um número inteiro válido.");
            }
        }
    }
    public static int lerIntZeroOuPositivo(String mensagem){
        while(true){
            System.out.println(mensagem);
            String entrada = input.nextLine().trim();

            try {
                int valor = Integer.parseInt(entrada);

                if(valor >= 0){
                    return valor;

                }

                System.out.println("Digite um número maior ou igual a zero.");

            } catch(NumberFormatException e){
                System.out.println("Entrada inválida. Digite um número inteiro válido.");
            }
        }
    }
}
