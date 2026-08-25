package br.com.cassio.eva;

public class TesteProdutoRepository {
    public static void main(String[] args){

        ProdutoRepository produtoRepository = new ProdutoRepository();

        Produto produto = new Produto(
                1L,
                "Teclado Mecânico",
                249.90,
                379.90,
                0,
                5
        );

        System.out.println(produtoRepository.atualizarDados(produto));

        Produto atualizado = produtoRepository.buscarPorId(1L);

        System.out.println(
                atualizado.getNome() + " | " +
                        atualizado.getPrecoCompra() + " | " +
                        atualizado.getPrecoVenda() + " | " +
                        atualizado.getEstoqueMinimo()
        );
}
}
