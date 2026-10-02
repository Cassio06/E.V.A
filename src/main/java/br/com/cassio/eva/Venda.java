package br.com.cassio.eva;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Venda {
    private List<ItemVenda> itens;
    private Long id;
    private BigDecimal valorTotal;
    private LocalDateTime horarioVenda;


    public Venda(List<ItemVenda> itens,Long id, BigDecimal valorTotal, LocalDateTime horarioVenda){
        if(itens == null || itens.isEmpty()){
            throw new IllegalArgumentException("A venda deve possuir pelo menos um item.");

        }
        if(id != null && id < 1){
            throw new IllegalArgumentException("O id deve ser maior ou igual a 1.");
        }
        if(valorTotal == null || valorTotal.compareTo(BigDecimal.ZERO) < 0){
            throw new IllegalArgumentException("A venda deve conter um valor total maior ou igual a 0. ");
        }
        if(horarioVenda == null){
            throw new IllegalArgumentException("A venda deve obrigatoriamente conter o dia e horario da venda. ");
        }
        for(ItemVenda item : itens){
            if(item == null){
                throw new IllegalArgumentException("A lista não pode conter itens nulos.");
            }
        }
        this.itens = new ArrayList<>(itens);
        this.id = id;
        this.valorTotal = valorTotal;
        this.horarioVenda = horarioVenda;
    }

    public BigDecimal calcularCustoTotal(){
        BigDecimal somaTotal = BigDecimal.ZERO;

        for(ItemVenda item : itens){
            somaTotal = somaTotal.add(item.calcularCustoTotal());
        }
        return somaTotal;
    }

    public BigDecimal calcularLucro(){
        BigDecimal lucroVenda = valorTotal.subtract(calcularCustoTotal());

        return  lucroVenda;
    }
}
