package br.com.cassio.eva;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class VendaTest {

    @Test
    void deveCalcularCustoTotalELucro(){
        List<ItemVenda> itens = new ArrayList<>();
        ItemVenda item1 = new ItemVenda(1L, 2, BigDecimal.valueOf(200), BigDecimal.valueOf(300));
        ItemVenda item2 = new ItemVenda(2L, 2, BigDecimal.valueOf(400), BigDecimal.valueOf(600));

        itens.add(item1);
        itens.add(item2);

        Venda venda = new Venda(itens, 3L, BigDecimal.valueOf(1800), LocalDateTime.now());

        assertEquals(0, BigDecimal.valueOf(1200).compareTo(venda.calcularCustoTotal()));

        assertEquals(0, BigDecimal.valueOf(600).compareTo(venda.calcularLucro()));




    }
    @Test
    void deveCalcularPrejuizoDeVendaEmConjunto(){
        List<ItemVenda> itens = new ArrayList<>();
        ItemVenda item1 = new ItemVenda(1L, 2, BigDecimal.valueOf(200), null);
        ItemVenda item2 = new ItemVenda(2L, 2, BigDecimal.valueOf(400), null);


        itens.add(item1);
        itens.add(item2);

        Venda venda = new Venda(itens, null, BigDecimal.valueOf(900), LocalDateTime.now());

        assertEquals(0, BigDecimal.valueOf(1200).compareTo(venda.calcularCustoTotal()));

        assertEquals(0, BigDecimal.valueOf(-300).compareTo(venda.calcularLucro()));


    }
    @Test
    void deveManterItensQuandoListaOriginalForAlterada(){
        List<ItemVenda> itens = new ArrayList<>();

        ItemVenda item1 = new ItemVenda(1L, 2, BigDecimal.valueOf(200), BigDecimal.valueOf(300));

        itens.add(item1);

        Venda venda = new Venda(itens, 3L, BigDecimal.valueOf(600), LocalDateTime.now());

        itens.clear();

        assertEquals(0, BigDecimal.valueOf(400).compareTo(venda.calcularCustoTotal()));
        assertEquals(0, BigDecimal.valueOf(200).compareTo(venda.calcularLucro()));
    }
}
