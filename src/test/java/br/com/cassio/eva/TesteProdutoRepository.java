package br.com.cassio.eva;

public class TesteProdutoRepository {
    public static void main(String[] args){

        ProdutoRepository produtoRepository = new ProdutoRepository();

        Produto produtoEncontrado = produtoRepository.buscarPorId(6);

        if (produtoEncontrado != null){
            System.out.println("Encontrado: " +
                    produtoEncontrado.getId() + " - " +
                    produtoEncontrado.getNome());

        }else{
            System.out.println("Produto não encontrado.");
        }

        Produto produtoInexistente = produtoRepository.buscarPorId(100);

        if (produtoInexistente != null){
            System.out.println("Encontrado: " +
                    produtoInexistente.getId() + " - " +
                    produtoInexistente.getNome());

        }else{
            System.out.println("Produto não encontrado.");
        }




    }
}
