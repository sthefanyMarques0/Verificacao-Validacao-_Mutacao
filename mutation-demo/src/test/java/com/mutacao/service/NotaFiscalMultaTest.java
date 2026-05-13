package com.mutacao.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

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

        assertEquals(21.0, nf.calcularMultaAtraso(1000.0, 1), 1e-9);
    }

    @Test
    @DisplayName("Multa com 10 dias")
    void testMultaDezDias() {
        
        assertEquals(30.0, nf.calcularMultaAtraso(1000.0, 10), 1e-9);
    }

    @Test
    @DisplayName("Multa com 30 dias")
    void testMultaTrintaDias() {
    
        assertEquals(50.0, nf.calcularMultaAtraso(1000.0, 30), 1e-9);
    }

    @ParameterizedTest(name = "Valor={0}, dias={1}, multaEsperada={2}")
    @CsvSource({
            "500.0,  1,  10.5",   
            "500.0,  5,  12.5",   
            "2000.0, 2,  44.0",   
            "100.0, 100, 12.0"    
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

        assertTrue(multa10 > multa5);
    }
}
