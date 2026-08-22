package br.com.cassio.eva;

public class TesteProdutoRepository {
    public static void main(String[] args){

        ProdutoRepository produtoRepository = new ProdutoRepository();

        produtoRepository.atualizarQuantidade(1, 0);

        for(Produto produto : produtoRepository.listarAtivos()){
            System.out.println("ID: " +produto.getId() +
                    " Nome: " + produto.getNome() +
                    " Quantidade: " + produto.getQuantidade());
        }



    }
}
