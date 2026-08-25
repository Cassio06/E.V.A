package br.com.cassio.eva;

import java.util.List;


    public class SistemaEstoque {

        private final ProdutoRepository produtoRepository;
        private final Estoque estoque;
        private final EntradaConsole entrada;

        public SistemaEstoque() {

            produtoRepository = new ProdutoRepository();
            estoque = new Estoque();
            entrada = new EntradaConsole();
        }


        public void executar(){
            int opcao;

            do {
                System.out.println("===== SISTEMA DE ESTOQUE =====");
                System.out.println("1 - Adicionar produto");
                System.out.println("2 - Vender produto");
                System.out.println("3 - Repor produto");
                System.out.println("4 - Editar produto");
                System.out.println("5 - Exibir resumo geral");
                System.out.println("6 - Listar produtos");
                System.out.println("0 - Sair");
                opcao = entrada.lerIntZeroOuPositivo("Escolha uma opção: ");

                switch(opcao){
                    case 1 -> adicionarProduto();
                    case 2 -> venderProduto();
                    case 3 -> reporProduto();
                    case 4 -> editarProduto();
                    case 5 -> exibirResumoGeral();
                    case 6 -> listarProdutos();
                    case 0 -> System.out.println("Saindo...");
                    default -> System.out.println("Escolha uma opção válida.");
                }
            }while(opcao != 0);
        }


        private void adicionarProduto(){


            String nome = entrada.lerTexto("Nome do produto: ");

            double precoPago = entrada.lerDoublePositivo("Preço de compra: ");

            double precoVenda;

            do {
                precoVenda = entrada.lerDoublePositivo("Preço de venda: ");
                if (precoVenda < precoPago) {
                    System.out.println("O preço de venda não pode ser menor que o preço de compra.");
                }
            } while (precoVenda < precoPago);

            int quantidade = entrada.lerIntZeroOuPositivo("Quantidade: ");

            int estoqueMinimo = entrada.lerIntZeroOuPositivo("Estoque mínimo: ");



            Produto produto = new Produto(nome, precoPago, precoVenda, quantidade, estoqueMinimo);
            boolean resultado = produtoRepository.inserir(produto);
            if (resultado){
                System.out.println("Produto cadastrado com sucesso.");

            }else{
                System.out.println("Não foi possível cadastrar o produto.");
            }

        }

        private void venderProduto(){
            Produto produtoVenda = null;
            String nomeVenda = null;
            while (produtoVenda == null) {
                nomeVenda = entrada.lerTexto("Nome do produto para venda: ");
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
                System.out.println("Estoque insuficiente. Quantidade disponível: " + produtoVenda.getQuantidade() + ".");
                quantidadeVenda = entrada.lerIntPositivo("Quantidade vendida: ");
            }
            Produto.ResultadoVenda resultado = estoque.venderProduto(nomeVenda, quantidadeVenda);
            switch(resultado){
                case SUCESSO -> System.out.println("Venda realizada com sucesso.");
                case ESTOQUE_INSUFICIENTE -> System.out.println("Estoque insuficiente.");
                case NUMERO_INVALIDO -> System.out.println("Digite uma quantidade válida.");
                case PRODUTO_NAO_ENCONTRADO -> System.out.println("Produto não encontrado.");
            }
        }
        private void reporProduto(){
            String nomeReposicao = entrada.lerTexto("Nome do produto para repor: ");
            Produto produtoReposicao = estoque.buscarProduto(nomeReposicao);
            while(produtoReposicao == null){
                System.out.println("Produto não encontrado.");
                nomeReposicao = entrada.lerTexto("Nome do produto para repor: ");
                produtoReposicao = estoque.buscarProduto(nomeReposicao);
            }
            int quantidadeReposicao = entrada.lerIntPositivo("Quantidade para repor: " );

            boolean resultado = estoque.reporProduto(nomeReposicao, quantidadeReposicao);
            if(resultado){
                System.out.println("Reposição realizada com sucesso.");

            }else{
                System.out.println("Não foi possível repor o produto.");
            }
        }
        private void editarProduto(){
            if(!listarProdutosResumido()){
                return;
            }
            System.out.println("===== EDITAR PRODUTO =====");
            Integer idProdutoEditar = entrada.lerIntPositivoOuCancelar("ID do produto que deseja editar (digite 0 para sair): ");
            if (idProdutoEditar == null){
                return;
            }
            Produto produtoEditar = produtoRepository.buscarPorId(idProdutoEditar);
            while(produtoEditar == null){
                System.out.println("Produto não encontrado.");
                idProdutoEditar = entrada.lerIntPositivoOuCancelar("ID do produto que deseja editar (digite 0 para sair): ");
                if (idProdutoEditar == null){
                    return;
                }
                produtoEditar = produtoRepository.buscarPorId(idProdutoEditar);

            }
            exibirProdutoResumido(produtoEditar);

            String novoNome = entrada.lerTextoOpcional("Novo nome (pressione Enter para manter): ");
            Double novoPrecoCompra;
            Double novoPrecoVenda;
            Integer novoEstoqueMinimo;



            String nomeFinal;

            double precoCompraFinal;
            double precoVendaFinal;
            while(true){

                novoPrecoCompra = entrada.lerDoublePositivoOpcionalOuCancelar("Novo preço de compra (pressione Enter para manter ou digite 0 para sair): ");
                if(novoPrecoCompra != null && novoPrecoCompra == 0.0){
                    return;
                }
                novoPrecoVenda = entrada.lerDoublePositivoOpcionalOuCancelar("Novo preço de venda (pressione Enter para manter ou digite 0 para sair): ");
                if(novoPrecoVenda != null && novoPrecoVenda == 0.0){
                    return;
                }

                if(novoPrecoCompra == null){
                    precoCompraFinal = produtoEditar.getPrecoCompra();

                }else{
                    precoCompraFinal = novoPrecoCompra;
                }
                if(novoPrecoVenda == null){
                    precoVendaFinal = produtoEditar.getPrecoVenda();
                }else{
                    precoVendaFinal = novoPrecoVenda;
                }

                if(precoCompraFinal > precoVendaFinal){
                    System.out.println("O preço de compra não pode ser maior que o preço de venda.");
                } else{
                    break;
                }



            }
            novoEstoqueMinimo = entrada.lerIntZeroOuPositivoOpcional("Novo estoque mínimo (pressione Enter para manter): ");

            if(novoNome == null && novoPrecoCompra == null && novoPrecoVenda == null && novoEstoqueMinimo == null){
                System.out.println("Nenhum dado do produto foi alterado.");
                return;
            }

            if(novoNome == null){
                nomeFinal = produtoEditar.getNome();
            }else{
                nomeFinal = novoNome;
            }

            int estoqueMinimoFinal;

            if(novoEstoqueMinimo == null){
                estoqueMinimoFinal = produtoEditar.getEstoqueMinimo();

            }else{
                estoqueMinimoFinal = novoEstoqueMinimo;
            }

            boolean resultadoNome = produtoEditar.alterarNome(nomeFinal);
            boolean resultadoPrecos= produtoEditar.alterarPrecos(precoCompraFinal, precoVendaFinal);
            boolean resultadoEstoqueMinimo = produtoEditar.alterarEstoqueMinimo(estoqueMinimoFinal);

            if(!resultadoNome || ! resultadoPrecos || !resultadoEstoqueMinimo){
                System.out.println("Não foi possível atualizar os dados do produto.");
                return;
            }

            boolean resultadoAtualizarDados = produtoRepository.atualizarDados(produtoEditar);


            }


        private void exibirResumoGeral(){
            System.out.println("===== RESUMO GERAL DO ESTOQUE =====");
            System.out.printf("Valor total em estoque: R$ %.2f%n", estoque.valorTotalDoEstoque());
            System.out.printf("Custo total em estoque: R$ %.2f%n", estoque.custoTotalDoEstoque());
            System.out.printf("Lucro total possível: R$ %.2f%n", estoque.lucroTotalDoEstoque());
            System.out.println("===================================");
        }
        private void listarProdutos(){
            List<Produto> produtos = produtoRepository.listarAtivos();

            if(produtos.isEmpty()){
                System.out.println("Estoque vazio.");
                return;

            }
            for(Produto produto : produtos){
                exibirProduto(produto);
                System.out.println("------------------------------");
            }
        }
        private boolean listarProdutosResumido(){
            List<Produto> produtos = produtoRepository.listarAtivos();

            if(produtos.isEmpty()){
                System.out.println("Estoque vazio.");
                return false;

            }
            for(Produto produto : produtos){
                exibirProdutoResumido(produto);
                System.out.println("------------------------------");
            }
            return true;
        }
        public void exibirProduto(Produto produto) {
            System.out.println("ID: " + produto.getId());
            System.out.println("Produto: " + produto.getNome());
            System.out.printf("Preço de venda: R$ %.2f%n", produto.getPrecoVenda());
            System.out.printf("Preço de compra: R$ %.2f%n", produto.getPrecoCompra());
            System.out.println("Quantidade em estoque: " + produto.getQuantidade());
            System.out.println("Estoque mínimo: " + produto.getEstoqueMinimo());
            System.out.printf("Valor total em estoque: R$ %.2f%n", produto.valorTotalEmEstoque());
            System.out.printf("Custo total em estoque: R$ %.2f%n", produto.custoTotalEmEstoque());
            System.out.printf("Lucro por unidade: R$ %.2f%n", produto.lucroProduto());
            System.out.printf("Lucro total possível: R$ %.2f%n", produto.lucroTotalPossivel());


            if (produto.estaComBaixoEstoque()) {
                System.out.println("Status: ⚠ baixo estoque.");

            }else{
                System.out.println("Status: estoque adequado.");
            }

        }
        public void exibirProdutoResumido(Produto produto) {
            System.out.println("ID: " + produto.getId());
            System.out.println("Produto: " + produto.getNome());
            System.out.printf("Preço de venda: R$ %.2f%n", produto.getPrecoVenda());
            System.out.printf("Preço de compra: R$ %.2f%n", produto.getPrecoCompra());
            System.out.println("Quantidade em estoque: " + produto.getQuantidade());
            System.out.println("Estoque mínimo: " + produto.getEstoqueMinimo());


            if (produto.estaComBaixoEstoque()) {
                System.out.println("Status: ⚠ baixo estoque.");

            }else{
                System.out.println("Status: estoque adequado.");
            }

        }
    }
