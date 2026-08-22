package br.com.cassio.eva;

import java.util.List;
import java.util.ArrayList;


public class Estoque {
    private final List<Produto> produtos;

    public Estoque(){
        produtos = new ArrayList<>();

    }

    public List<Produto> getProdutos(){
        return new ArrayList<>(produtos);
    }

    public boolean adicionarProduto(Produto produto){
        if(produto == null){
            return false;
        }
        Produto produtoExistente = buscarProduto(produto.getNome());

        if(produtoExistente != null){
            return false;

        }

            produtos.add(produto);
            return true;

    }

    public Produto buscarProduto(String nome){
        if(nome == null || nome.isBlank()){
            return null;
        }
        for (Produto produto : produtos){

            if (produto.getNome().trim().equalsIgnoreCase(nome.trim())) {
                return produto;
            }
        }

        return null;
    }

    public boolean reporProduto(String nome, int qtd){
        Produto produto = buscarProduto(nome);


        if(produto == null){
            return false;
        }

        return produto.repor(qtd);
    }
    public Produto.ResultadoVenda venderProduto(String nome, int qtd){
        Produto produto = buscarProduto(nome);

        if(produto == null){
            return Produto.ResultadoVenda.PRODUTO_NAO_ENCONTRADO;
        }
        return produto.vender(qtd);
    }
    public double valorTotalDoEstoque(){
        double total = 0;

        for(Produto produto : produtos){
            total += produto.valorTotalEmEstoque();
        }
        return total;

    }
    public double custoTotalDoEstoque(){
        double total = 0;
        for(Produto produto : produtos){
            total += produto.custoTotalEmEstoque();
        }
        return total;
    }
    public double lucroTotalDoEstoque(){
        double total = 0;
        for(Produto produto : produtos){
            total += produto.lucroTotalPossivel();

        }
        return total;

    }


}
