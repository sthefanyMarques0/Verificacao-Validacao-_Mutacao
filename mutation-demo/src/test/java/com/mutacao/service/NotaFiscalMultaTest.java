package com.mutacao.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Teste 10 – Cálculo de multa e juros por atraso na Nota Fiscal.
 *
 * Mutantes esperados: substituição do multiplicador da multa (0.02 → 0.0),
 * alteração do multiplicador diário (0.001 → 0.01),
 * troca de operador de soma por subtração na fórmula final.
 */
@DisplayName("NotaFiscalService – Multa por Atraso")
class NotaFiscalMultaTest {

    private NotaFiscalService nf;

    @BeforeEach
    void setUp() {
        nf = new NotaFiscalService();
    }

    @Test
    @DisplayName("Sem atraso (0 dias) não gera multa")
    void testSemAtraso() {
        assertEquals(0.0, nf.calcularMultaAtraso(1000.0, 0), 1e-9);
    }

    @Test
    @DisplayName("Dias negativos não geram multa")
    void testDiasNegativos() {
        assertEquals(0.0, nf.calcularMultaAtraso(1000.0, -5), 1e-9);
    }

    @Test
    @DisplayName("Multa com 1 dia: 2% fixo + 0,1% por dia")
    void testMultaUmDia() {
        // multa = 1000 * 0.02 = 20
        // juros = 1000 * 0.001 * 1 = 1
        // total = 21
        assertEquals(21.0, nf.calcularMultaAtraso(1000.0, 1), 1e-9);
    }

    @Test
    @DisplayName("Multa com 10 dias")
    void testMultaDezDias() {
        // multa = 1000 * 0.02 = 20
        // juros = 1000 * 0.001 * 10 = 10
        // total = 30
        assertEquals(30.0, nf.calcularMultaAtraso(1000.0, 10), 1e-9);
    }

    @Test
    @DisplayName("Multa com 30 dias")
    void testMultaTrintaDias() {
        // multa = 1000 * 0.02 = 20
        // juros = 1000 * 0.001 * 30 = 30
        // total = 50
        assertEquals(50.0, nf.calcularMultaAtraso(1000.0, 30), 1e-9);
    }

    @ParameterizedTest(name = "Valor={0}, dias={1}, multaEsperada={2}")
    @CsvSource({
            "500.0,  1,  10.5",   // 500*0.02 + 500*0.001*1  = 10 + 0.5  = 10.5
            "500.0,  5,  12.5",   // 500*0.02 + 500*0.001*5  = 10 + 2.5  = 12.5
            "2000.0, 2,  44.0",   // 2000*0.02 + 2000*0.001*2 = 40 + 4   = 44.0
            "100.0, 100, 12.0"    // 100*0.02 + 100*0.001*100 = 2 + 10   = 12.0
    })
    @DisplayName("Parametrizado: valor, dias e multa esperada")
    void testMultaParametrizado(double valor, int dias, double esperado) {
        assertEquals(esperado, nf.calcularMultaAtraso(valor, dias), 1e-9);
    }

    @Test
    @DisplayName("Multa cresce proporcionalmente com o valor")
    void testMultaProporcional() {
        double multaBase   = nf.calcularMultaAtraso(1000.0, 5);
        double multaDobro  = nf.calcularMultaAtraso(2000.0, 5);
        assertEquals(multaBase * 2, multaDobro, 1e-9);
    }

    @Test
    @DisplayName("Multa cresce proporcionalmente com os dias")
    void testJurosCrescemComDias() {
        double multa5  = nf.calcularMultaAtraso(1000.0, 5);
        double multa10 = nf.calcularMultaAtraso(1000.0, 10);
        // juros dobram; multa fixa permanece a mesma, então multa10 > multa5
        assertTrue(multa10 > multa5);
    }
}
