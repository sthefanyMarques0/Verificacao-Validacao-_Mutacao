package com.mutacao.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Teste 6 – Modelo Produto: criação, estoque e valor.
 *
 * Mutantes esperados: inversão de condição (< 0 → <= 0), alteração do
 * operador de adição/subtração de estoque, substituição do multiplicador
 * em valorTotalEmEstoque.
 */
@DisplayName("Produto – Modelo")
class ProdutoTest {

    private Produto produto;

    @BeforeEach
    void setUp() {
        produto = new Produto("Notebook", 2500.0, 10);
    }

    @Test
    @DisplayName("Produto criado com atributos corretos")
    void testCriacao() {
        assertEquals("Notebook", produto.getNome());
        assertEquals(2500.0, produto.getPreco(), 1e-9);
        assertEquals(10, produto.getQuantidade());
    }

    @Test
    @DisplayName("Nome vazio lança exceção")
    void testNomeVazioLancaExcecao() {
        assertThrows(IllegalArgumentException.class,
                () -> new Produto("", 100.0, 5));
    }

    @Test
    @DisplayName("Preço negativo lança exceção")
    void testPrecoNegativoLancaExcecao() {
        assertThrows(IllegalArgumentException.class,
                () -> new Produto("Item", -1.0, 5));
    }

    @Test
    @DisplayName("Quantidade negativa lança exceção")
    void testQuantidadeNegativaLancaExcecao() {
        assertThrows(IllegalArgumentException.class,
                () -> new Produto("Item", 10.0, -1));
    }

    @Test
    @DisplayName("Adicionar estoque aumenta quantidade")
    void testAdicionarEstoque() {
        produto.adicionarEstoque(5);
        assertEquals(15, produto.getQuantidade());
    }

    @Test
    @DisplayName("Remover estoque diminui quantidade")
    void testRemoverEstoque() {
        produto.removerEstoque(3);
        assertEquals(7, produto.getQuantidade());
    }

    @Test
    @DisplayName("Remover mais do que o estoque lança exceção")
    void testRemoverMaisQueEstoque() {
        assertThrows(IllegalStateException.class, () -> produto.removerEstoque(11));
    }

    @Test
    @DisplayName("Valor total em estoque = preço × quantidade")
    void testValorTotal() {
        assertEquals(25000.0, produto.valorTotalEmEstoque(), 1e-9);
    }

    @Test
    @DisplayName("Produto com quantidade zero não está disponível")
    void testNaoDisponivel() {
        Produto p = new Produto("Raro", 100.0, 0);
        assertFalse(p.estaDisponivel());
    }

    @Test
    @DisplayName("Produto com quantidade positiva está disponível")
    void testDisponivel() {
        assertTrue(produto.estaDisponivel());
    }
}
