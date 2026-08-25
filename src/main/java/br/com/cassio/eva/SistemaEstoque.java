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
                System.out.println("======SISTEMA ESTOQUE======");
                System.out.println("1-Adicionar.");
                System.out.println("2-Vender.");
                System.out.println("3-Repor.");
                System.out.println("4-Editar.");
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
            boolean resultado = produtoRepository.inserir(produto);
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
            if(!listarProdutosResumido()){
                return;
            }
            System.out.println("=====EDITAR PRODUTO=====");
            Integer idProdutoEditar = entrada.lerIntPositivoOuCancelar("ID do produto que deseja editar(0- P/sair): ");
            if (idProdutoEditar == null){
                return;
            }
            Produto produtoEditar = produtoRepository.buscarPorId(idProdutoEditar);
            while(produtoEditar == null){
                System.out.println("Produto não encontrado.");
                idProdutoEditar = entrada.lerIntPositivoOuCancelar("ID do produto que deseja Editar(Caso deseje sair digite 0): ");
                if (idProdutoEditar == null){
                    return;
                }
                produtoEditar = produtoRepository.buscarPorId(idProdutoEditar);

            }
            exibirProdutoResumido(produtoEditar);

            String novoNome = entrada.lerTextoOpcional("Novo nome(Enter para Manter): ");
            Double novoPrecoCompra;
            Double novoPrecoVenda;
            Integer novoEstoqueMinimo;



            String nomeFinal;

            double precoCompraFinal;
            double precoVendaFinal;
            while(true){

                novoPrecoCompra = entrada.lerDoublePositivoOpcionalOuCancelar("Novo Preço Compra(Enter P/ Manter e 0 P/Sair): ");
                if(novoPrecoCompra != null && novoPrecoCompra == 0.0){
                    return;
                }
                novoPrecoVenda = entrada.lerDoublePositivoOpcionalOuCancelar("Novo Preço Venda(Enter P/ Manter e 0 P/Sair): ");
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
            novoEstoqueMinimo = entrada.lerIntZeroOuPositivoOpcional("Novo Estoque Minimo(Enter para Manter): ");

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
                System.out.println("Erro!");
                return;
            }

            boolean resultadoAtualizarDados = produtoRepository.atualizarDados(produtoEditar);


            }


        private void exibirResumoGeral(){
            System.out.println("=====RESUMO GERAL DO ESTOQUE=====");
            System.out.printf("Valor total em estoque: R$ %.2f%n", estoque.valorTotalDoEstoque());
            System.out.printf("Custo total em estoque: R$ %.2f%n", estoque.custoTotalDoEstoque());
            System.out.printf("Lucro total possivel: R$ %.2f%n", estoque.lucroTotalDoEstoque());
            System.out.println("=================================");
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
        public void exibirProdutoResumido(Produto produto) {
            System.out.println("ID: " + produto.getId());
            System.out.println("Produto: " + produto.getNome());
            System.out.printf("Preço Venda: %.2f%n", produto.getPrecoVenda());
            System.out.printf("Preço Pago: %.2f%n", produto.getPrecoCompra());
            System.out.println("Estoque: " + produto.getQuantidade());
            System.out.println("Estoque Minimo: " + produto.getEstoqueMinimo());


            if (produto.estaComBaixoEstoque()) {
                System.out.println("Status: ⚠ Baixo estoque!");

            }else{
                System.out.println("Status: Estoque Ok.");
            }

        }
    }

