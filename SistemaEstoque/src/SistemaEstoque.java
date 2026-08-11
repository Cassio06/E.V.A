    import java.util.List;


    public class SistemaEstoque {

        private final Estoque estoque;
        private final EntradaConsole entrada;

        public SistemaEstoque() {
            estoque = new Estoque();
            entrada = new EntradaConsole();
        }


        public void executar(){
            int opcao;

            do {
                System.out.println("======SISTEMA ESTOQUE======");
                System.out.println("1-Adicionar Produto.");
                System.out.println("2-Vender Produto.");
                System.out.println("3-Repor Produto.");
                System.out.println("4-Editar Produto.");
                System.out.println("5-Resumo Geral.");
                System.out.println("6-Listar Produtos.");
                System.out.println("0-Sair");
                opcao = entrada.lerIntZeroOuPositivo("Escolha uma opção: ");

                switch(opcao){
                    case 1 -> adicionarProduto();
                    case 2 -> venderProduto();
                    case 3 -> reporProduto();
                    case 4 -> editarProduto();
                    case 5 -> exibirResumoGeral();
                    case 6 -> listarProdutos();
                    case 0 -> System.out.println("Saindo...");
                    default -> System.out.println("Escolha uma opção valida");
                }
            }while(opcao != 0);
        }


        private void adicionarProduto(){


            String nome = entrada.lerTexto("Nome do produto: ");

            while(estoque.buscarProduto(nome.trim()) != null){
                System.out.println("Produto já cadastrado");
                nome = entrada.lerTexto("Nome do produto: ");
            }
            double precoPago = entrada.lerDoublePositivo("Preço pago: ");

            double precoVenda;

            do {
                precoVenda = entrada.lerDoublePositivo("Preço de venda: ");
                if (precoVenda < precoPago) {
                    System.out.println("Preço de venda menor que o preço pago. Isso daria prejuízo");
                }
            } while (precoVenda < precoPago);

            int quantidade = entrada.lerIntZeroOuPositivo("Quantidade: ");

            int estoqueMinimo = entrada.lerIntZeroOuPositivo("Estoque Minimo: ");



            Produto produto = new Produto(nome, precoPago, precoVenda, quantidade, estoqueMinimo);
            boolean resultado = estoque.adicionarProduto(produto);
            if (resultado){
                System.out.println("Produto Cadastrado.");

            }else{
                System.out.println("Erro no Cadastro.");
            }

        }

        private void venderProduto(){
            Produto produtoVenda = null;
            String nomeVenda = null;
            while (produtoVenda == null) {
                nomeVenda = entrada.lerTexto("Nome do produto vendido: ");
                produtoVenda = estoque.buscarProduto(nomeVenda);

                if (produtoVenda == null) {
                    System.out.println("Produto não encontrado. Digite novamente.");
                }
            }
            if(produtoVenda.getQuantidade() == 0){
                System.out.println("Produto sem estoque.");
                return;
            }

            int quantidadeVenda = entrada.lerIntPositivo("Quantidade vendida: ");

            while(quantidadeVenda > produtoVenda.getQuantidade()){
                System.out.println("Estoque insuficiente. Quantidade disponível: " + produtoVenda.getQuantidade());
                quantidadeVenda = entrada.lerIntPositivo("Quantidade vendida: ");
            }
            Produto.ResultadoVenda resultado = estoque.venderProduto(nomeVenda, quantidadeVenda);
            switch(resultado){
                case SUCESSO -> System.out.println("Venda feita com sucesso.");
                case ESTOQUE_INSUFICIENTE -> System.out.println("Estoque insuficiente.");
                case NUMERO_INVALIDO -> System.out.println("Insira um número valido");
                case PRODUTO_NAO_ENCONTRADO -> System.out.println("Produto não encontrado.");
            }
        }
        private void reporProduto(){
            String nomeReposicao = entrada.lerTexto("Nome do produto para repor: ");
            Produto produtoReposicao = estoque.buscarProduto(nomeReposicao);
            while(produtoReposicao == null){
                System.out.println("Produto não existe.");
                nomeReposicao = entrada.lerTexto("Nome do produto para repor: ");
                produtoReposicao = estoque.buscarProduto(nomeReposicao);
            }
            int quantidadeReposicao = entrada.lerIntPositivo("Quantidade para repor:" );

            boolean resultado = estoque.reporProduto(nomeReposicao, quantidadeReposicao);
            if(resultado){
                System.out.println("Reposição feita com sucesso.");

            }else{
                System.out.println("Erro na reposição.");
            }
        }
        private void editarProduto(){
            System.out.println("=====EDITAR PRODUTO=====");
            String nomeProdutoEditar = entrada.lerTextoOuCancelar("Produto que deseja Editar(0-Sair): ");
            if (nomeProdutoEditar == null){
                return;
            }
            Produto produtoEditar = estoque.buscarProduto(nomeProdutoEditar);
            while(produtoEditar == null){
                System.out.println("Produto não encontrado.");
                nomeProdutoEditar = entrada.lerTextoOuCancelar("Produto que deseja Editar(Caso deseje sair digite 0): ");
                if (nomeProdutoEditar == null){
                    return;
                }
                produtoEditar = estoque.buscarProduto(nomeProdutoEditar);

            }
                System.out.println("========================");
                System.out.println("1-Editar Preço de Compra.");
                System.out.println("2-Editar Preço de Venda.");
                System.out.println("3-Editar Estoque Minimo.");
                System.out.println("4-Editar Nome.");
                System.out.println("5-Editar Estoque.");
                System.out.println("0-Sair");
                System.out.println("========================");

                int opcaoEditar = entrada.lerIntZeroOuPositivo("");
                switch (opcaoEditar) {
                    case 1:

                        boolean alterouCompra = produtoEditar.alterarPrecoCompra(entrada.lerDoublePositivo("Novo preço de compra: "));
                        if(alterouCompra){
                            System.out.println("Preço de compra alterado com sucesso!!");
                        }else{
                            System.out.println("Preço de compra inválido. Ele não pode ser maior que o preço de venda.");
                        }

                        break;

                    case 2:
                        boolean alterouVenda = produtoEditar.alterarPrecoVenda(entrada.lerDoublePositivo("Novo preço de Venda: "));
                        if(alterouVenda){
                            System.out.println("Preço de venda alterado com sucesso!!");
                        }else{
                            System.out.println("Preço de venda inválido. Ele não pode ser menor que o preço de compra.");
                        }

                        break;
                    case 3:
                        produtoEditar.alterarEstoqueMinimo(entrada.lerIntZeroOuPositivo("Novo estoque minimo: "));
                        System.out.println("Estoque Minimo alterado com sucesso!!");
                        break;
                    case 4:
                        String novoNome = entrada.lerTexto("Novo nome do produto: ");
                        Produto produtoComEsseNome = estoque.buscarProduto(novoNome);

                        while(produtoComEsseNome != null && produtoComEsseNome != produtoEditar){
                            System.out.println("Nome pertence a outro produto.");
                            novoNome = entrada.lerTexto("Novo nome do produto: ");
                            produtoComEsseNome = estoque.buscarProduto(novoNome);
                        }
                        produtoEditar.alterarNome(novoNome);
                        System.out.println("Nome alterado com sucesso!!");
                        break;
                    case 5:
                        produtoEditar.alterarEstoque(entrada.lerIntZeroOuPositivo("Novo Estoque: "));
                        System.out.println("Estoque alterado com sucesso!!");
                        break;
                    case 0:
                        System.out.println("Saindo");
                        break;
                    default:
                        System.out.println("Opção inválida");
                }

            }


        private void exibirResumoGeral(){
            System.out.println("=====RESUMO GERAL DO ESTOQUE=====");
            System.out.printf("Valor total em estoque: R$ %.2f%n", estoque.valorTotalDoEstoque());
            System.out.printf("Custo total em estoque: R$ %.2f%n", estoque.custoTotalDoEstoque());
            System.out.printf("Lucro total possivel: R$ %.2f%n", estoque.lucroTotalDoEstoque());
            System.out.println("=================================");
        }
        private void listarProdutos(){
            List<Produto> produtos = estoque.getProdutos();

            if(produtos.isEmpty()){
                System.out.println("Estoque vazio.");
                return;

            }
            for(Produto produto : produtos){
                exibirProduto(produto);
                System.out.println("------------------------------");
            }
        }
        public void exibirProduto(Produto produto) {
            System.out.println("Produto: " + produto.getNome());
            System.out.printf("Preço Venda: %.2f%n", produto.getPrecoVenda());
            System.out.printf("Preço Pago: %.2f%n", produto.getPrecoCompra());
            System.out.println("Estoque: " + produto.getQuantidade());
            System.out.println("Estoque Minimo: " + produto.getEstoqueMinimo());
            System.out.printf("Valor Total em Estoque: %.2f%n", produto.valorTotalEmEstoque());
            System.out.printf("Custo Total em Estoque: %.2f%n", produto.custoTotalEmEstoque());
            System.out.printf("Lucro por unidade: %.2f%n", produto.lucroProduto());
            System.out.printf("Lucro Total: %.2f%n", produto.lucroTotalPossivel());


            if (produto.estaComBaixoEstoque()) {
                System.out.println("Status: ⚠ Baixo estoque!");

            }else{
                System.out.println("Status: Estoque Ok.");
            }

        }
    }



