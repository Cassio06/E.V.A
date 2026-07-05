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
            System.out.println("6 - Editar Produto");
            System.out.println("0 - Sair");


            opcao = lerIntZeroOuPositivo("Escolha uma opção: ");

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

                    int estoqueMinimo = lerIntZeroOuPositivo("Estoque Minimo: ");



                    Produto produto = new Produto(nome, precoPago, precoVenda, quantidade, estoqueMinimo);
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
                    String nomeReposicao = lerTexto("Nome do produto para repor: ");

                    int quantidadeReposicao = lerIntPositivo("Quantidade para repor:" );

                    estoque.reporProduto(nomeReposicao, quantidadeReposicao);
                    break;
                case 5:
                    estoque.exibirResumoGeral();
                    break;
                case 6:
                    System.out.println("=====EDITAR PRODUTO=====");
                    String nomeProdutoEditar = lerTexto("Produto que deseja Editar: ");
                    boolean edicaoCancelada = false;
                    Produto produtoEditar = estoque.buscarProduto(nomeProdutoEditar);
                    while(produtoEditar == null){
                        System.out.println("Produto não encontrado.");
                        nomeProdutoEditar = lerTexto("Produto que deseja Editar(Caso deseje sair digite 0): ");
                        if (nomeProdutoEditar.equals("0")){
                            edicaoCancelada = true;
                            break;
                        }
                        produtoEditar = estoque.buscarProduto(nomeProdutoEditar);

                    }
                    if(!edicaoCancelada) {
                        System.out.println("========================");
                        System.out.println("1-Editar Preço de Compra.");
                        System.out.println("2-Editar Preço de Venda.");
                        System.out.println("3-Editar Estoque Minimo.");
                        System.out.println("4-Editar Nome.");
                        System.out.println("5-Editar Estoque.");
                        System.out.println("0-Sair");
                        System.out.println("========================");

                        int opcaoEditar = lerIntZeroOuPositivo("");
                        switch (opcaoEditar) {
                            case 1:

                                boolean alterouCompra = produtoEditar.alterarPrecoCompra(lerDoublePositivo("Novo preço de compra: "));
                                if(alterouCompra){
                                    System.out.println("Preço de compra alterado com sucesso!!");
                                }else{
                                    System.out.println("Preço de compra inválido. Ele não pode ser maior que o preço de venda.");
                                }

                                break;

                            case 2:
                                boolean alterouVenda = produtoEditar.alterarPrecoVenda(lerDoublePositivo("Novo preço de Venda: "));
                                if(alterouVenda){
                                    System.out.println("Preço de venda alterado com sucesso!!");
                                }else{
                                    System.out.println("Preço de venda inválido. Ele não pode ser menor que o preço de compra.");
                                }

                                break;
                            case 3:
                                produtoEditar.alterarEstoqueMinimo(lerIntZeroOuPositivo("Novo estoque minimo: "));
                                System.out.println("Estoque Minimo alterado com sucesso!!");
                                break;
                            case 4:
                                produtoEditar.alterarNome(lerTexto("Novo nome do Produto: "));
                                System.out.println("Nome alterado com sucesso!!");
                                break;
                            case 5:
                                produtoEditar.alterarEstoque(lerIntZeroOuPositivo("Novo Estoque: "));
                                System.out.println("Estoque alterado com sucesso!!");
                                break;
                            case 0:
                                System.out.println("Saindo");
                                break;
                            default:
                                System.out.println("Opção inválida");
                        }

                    }
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
