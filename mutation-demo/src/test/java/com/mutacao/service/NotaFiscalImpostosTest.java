package com.mutacao.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static com.mutacao.service.NotaFiscalService.TipoServico.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Teste 9 – Cálculo de impostos e total da Nota Fiscal.
 *
 * Mutantes esperados: substituição de alíquotas (0.05 → 0.0),
 * inversão do limiar de retenção (> 5000 → >= 5000),
 * troca de operador na fórmula de juros/multa.
 */
@DisplayName("NotaFiscalService – Impostos")
class NotaFiscalImpostosTest {

    private NotaFiscalService nf;

    @BeforeEach
    void setUp() {
        nf = new NotaFiscalService();
    }

    @Test
    @DisplayName("ISS sobre serviço é 5%")
    void testImpostoServico() {
        assertEquals(50.0, nf.calcularImpostos(1000.0, SERVICO), 1e-9);
    }

    @Test
    @DisplayName("ICMS sobre produto é 12%")
    void testImpostoProduto() {
        assertEquals(120.0, nf.calcularImpostos(1000.0, PRODUTO), 1e-9);
    }

    @Test
    @DisplayName("ICMS + IPI sobre produto com IPI é 22%")
    void testImpostoProdutoComIPI() {
        assertEquals(220.0, nf.calcularImpostos(1000.0, PRODUTO_COM_IPI), 1e-9);
    }

    @Test
    @DisplayName("Total de serviço = base + 5%")
    void testTotalServico() {
        assertEquals(1050.0, nf.calcularTotal(1000.0, SERVICO), 1e-9);
    }

    @Test
    @DisplayName("Total de produto = base + 12%")
    void testTotalProduto() {
        assertEquals(1120.0, nf.calcularTotal(1000.0, PRODUTO), 1e-9);
    }

    @Test
    @DisplayName("Total de produto com IPI = base + 22%")
    void testTotalProdutoComIPI() {
        assertEquals(1220.0, nf.calcularTotal(1000.0, PRODUTO_COM_IPI), 1e-9);
    }

    @Test
    @DisplayName("Retenção requerida para valor acima de 5000")
    void testRetencaoRequerida() {
        assertTrue(nf.requerRetencao(5001.0));
    }

    @Test
    @DisplayName("Retenção não requerida para valor igual a 5000")
    void testRetencaoNaoRequerida5000() {
        assertFalse(nf.requerRetencao(5000.0));
    }

    @Test
    @DisplayName("Retenção não requerida para valor abaixo de 5000")
    void testRetencaoNaoRequeridaAbaixo() {
        assertFalse(nf.requerRetencao(4999.0));
    }

    @Test
    @DisplayName("Valor base negativo lança exceção")
    void testValorNegativoLancaExcecao() {
        assertThrows(IllegalArgumentException.class,
                () -> nf.calcularImpostos(-1.0, SERVICO));
    }
}
