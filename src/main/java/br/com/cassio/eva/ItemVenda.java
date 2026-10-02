package br.com.cassio.eva;

import java.math.BigDecimal;

public class ItemVenda {
    private final Long produtoId;
    private final int quantidade;
    private final BigDecimal custoUnitario;
    private final BigDecimal vendaUnitaria;

    public ItemVenda(Long produtoId, int quantidade, BigDecimal custoUnitario, BigDecimal vendaUnitaria){
        if(produtoId == null || produtoId < 1){
            throw new IllegalArgumentException("ID do produto deve ser maior ou igual a 1.");

        }
        if(quantidade < 1){
            throw new IllegalArgumentException("A quantidade vendida deve ser maior ou igual a 1.");
        }
        if(custoUnitario == null || custoUnitario.compareTo(BigDecimal.ZERO) < 0){
            throw new IllegalArgumentException("O custo unitario deve ser maior ou igual a 0.");
        }

        if(vendaUnitaria != null && vendaUnitaria.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("O valor de venda unitario deve ser maior ou igual a 0.");
        }
        this.produtoId = produtoId;
        this.quantidade = quantidade;
        this.custoUnitario = custoUnitario;
        this.vendaUnitaria = vendaUnitaria;


    }

    public BigDecimal calcularCustoTotal(){
        return custoUnitario.multiply(BigDecimal.valueOf(quantidade));
    }





}
