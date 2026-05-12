package com.mutacao.service;

import com.mutacao.model.Produto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Teste 8 – Serviço de Carrinho: itens, subtotal, desconto e total.
 *
 * Mutantes esperados: troca de operador de soma no subtotal (sum → 0),
 * alteração da fórmula de desconto (/ 100 → * 100), inversão de isEmpty.
 */
@DisplayName("CarrinhoService – Operações")
class CarrinhoServiceTest {

    private CarrinhoService carrinho;
    private Produto notebook;
    private Produto mouse;

    @BeforeEach
    void setUp() {
        carrinho = new CarrinhoService();
        notebook = new Produto("Notebook", 2000.0, 5);
        mouse    = new Produto("Mouse",      150.0, 10);
    }

    @Test
    @DisplayName("Carrinho novo está vazio")
    void testCarrinhoVazioInicial() {
        assertTrue(carrinho.estaVazio());
    }

    @Test
    @DisplayName("Adicionar item aumenta quantidade")
    void testAdicionarItem() {
        carrinho.adicionarItem(notebook);
        assertEquals(1, carrinho.quantidadeItens());
    }

    @Test
    @DisplayName("Subtotal correto com dois itens")
    void testSubtotal() {
        carrinho.adicionarItem(notebook);
        carrinho.adicionarItem(mouse);
        assertEquals(2150.0, carrinho.subtotal(), 1e-9);
    }

    @Test
    @DisplayName("Total sem desconto igual ao subtotal")
    void testTotalSemDesconto() {
        carrinho.adicionarItem(notebook);
        assertEquals(2000.0, carrinho.total(), 1e-9);
    }

    @Test
    @DisplayName("Total com 10% de desconto")
    void testTotalComDesconto() {
        carrinho.adicionarItem(notebook); // 2000
        carrinho.aplicarDesconto(10);     // -200
        assertEquals(1800.0, carrinho.total(), 1e-9);
    }

    @Test
    @DisplayName("Total com 100% de desconto é zero")
    void testDesconto100() {
        carrinho.adicionarItem(mouse);
        carrinho.aplicarDesconto(100);
        assertEquals(0.0, carrinho.total(), 1e-9);
    }

    @Test
    @DisplayName("Desconto inválido (> 100) lança exceção")
    void testDescontoInvalido() {
        assertThrows(IllegalArgumentException.class,
                () -> carrinho.aplicarDesconto(101));
    }

    @Test
    @DisplayName("Remover item reduz quantidade")
    void testRemoverItem() {
        carrinho.adicionarItem(notebook);
        carrinho.adicionarItem(mouse);
        carrinho.removerItem(mouse);
        assertEquals(1, carrinho.quantidadeItens());
    }

    @Test
    @DisplayName("Limpar zera o carrinho")
    void testLimpar() {
        carrinho.adicionarItem(notebook);
        carrinho.limpar();
        assertTrue(carrinho.estaVazio());
    }

    @Test
    @DisplayName("Produto fora de estoque não pode ser adicionado")
    void testProdutoSemEstoque() {
        Produto semEstoque = new Produto("Raro", 999.0, 0);
        assertThrows(IllegalStateException.class,
                () -> carrinho.adicionarItem(semEstoque));
    }
}
