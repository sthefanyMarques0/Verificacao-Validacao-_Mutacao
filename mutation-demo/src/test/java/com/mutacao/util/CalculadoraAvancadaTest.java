package com.mutacao.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;


@DisplayName("Calculadora – Funções Avançadas")
class CalculadoraAvancadaTest {

    private Calculadora calc;

    @BeforeEach
    void setUp() {
        calc = new Calculadora();
    }

    @Test
    @DisplayName("Potência quadrada")
    void testPotenciaQuadrada() {
        assertEquals(9.0, calc.potencia(3, 2), 1e-9);
    }

    @Test
    @DisplayName("Potência com expoente zero retorna 1")
    void testPotenciaExpoZero() {
        assertEquals(1.0, calc.potencia(5, 0), 1e-9);
    }

    @Test
    @DisplayName("Potência com expoente 1 retorna a base")
    void testPotenciaExpoUm() {
        assertEquals(7.0, calc.potencia(7, 1), 1e-9);
    }

    @Test
    @DisplayName("Potência com expoente negativo lança exceção")
    void testPotenciaExpoNegativo() {
        assertThrows(IllegalArgumentException.class, () -> calc.potencia(2, -1));
    }

    @ParameterizedTest(name = "{0} é par? {1}")
    @CsvSource({"0,true", "2,true", "4,true", "1,false", "3,false", "-1,false", "-2,true"})
    @DisplayName("Paridade de inteiros")
    void testEhPar(int numero, boolean esperado) {
        assertEquals(esperado, calc.ehPar(numero));
    }

    @Test
    @DisplayName("Máximo quando primeiro é maior")
    void testMaximoPrimeiroMaior() {
        assertEquals(8, calc.maximo(8, 3));
    }

    @Test
    @DisplayName("Máximo quando segundo é maior")
    void testMaximoSegundoMaior() {
        assertEquals(10, calc.maximo(5, 10));
    }

    @Test
    @DisplayName("Máximo com valores iguais")
    void testMaximoIguais() {
        assertEquals(4, calc.maximo(4, 4));
    }

    @Test
    @DisplayName("Mínimo quando primeiro é menor")
    void testMinimoPrimeiroMenor() {
        assertEquals(2, calc.minimo(2, 9));
    }

    @Test
    @DisplayName("Mínimo com valores iguais")
    void testMinimoIguais() {
        assertEquals(6, calc.minimo(6, 6));
    }
}
