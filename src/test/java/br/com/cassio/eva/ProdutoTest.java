package br.com.cassio.eva;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class ProdutoTest {

    @Test
    void deveReporProduto(){
        Produto produto1 = new Produto("Mouse", new BigDecimal("10.00"), new BigDecimal("20.00"), 10, 2);
        Produto produto2 = new Produto("Teclado", new BigDecimal("10.00"), new BigDecimal("20.00"), 10, 2);


        boolean conseguiuRepor = produto1.repor(5);
        boolean conseguiuRepor2 = produto2.repor(0);

        assertTrue(conseguiuRepor);
        assertFalse(conseguiuRepor2);
        assertEquals(10, produto2.getQuantidade());
        assertEquals(15, produto1.getQuantidade());
    }

    @Test
    void deveVenderProduto(){
        Produto produto = new Produto("Mouse",new BigDecimal("10.00"), new BigDecimal("20.00"), 10, 2);


        Produto.ResultadoVenda resultadoVenda = produto.vender(3);


        assertEquals(Produto.ResultadoVenda.SUCESSO, resultadoVenda);
        assertEquals(7, produto.getQuantidade());


    }

    @Test
    void deveRecusarVendaComEstoqueInsuficiente(){
        Produto produto = new Produto("Mouse",new BigDecimal("10.00"), new BigDecimal("20.00"), 10, 2);

        Produto.ResultadoVenda resultadoVenda = produto.vender(11);

        assertEquals(Produto.ResultadoVenda.ESTOQUE_INSUFICIENTE, resultadoVenda);
        assertEquals(10, produto.getQuantidade());
    }

    @Test
    void deveRecusarVendaComNumeroInvalido(){
        Produto produto = new Produto("Mouse",new BigDecimal("10.00"), new BigDecimal("20.00"), 10, 2);

        Produto.ResultadoVenda resultadoVenda = produto.vender(0);

        assertEquals(Produto.ResultadoVenda.NUMERO_INVALIDO, resultadoVenda);
        assertEquals(10, produto.getQuantidade());
    }
}
