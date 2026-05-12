package com.mutacao.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Teste 1 – Operações básicas da Calculadora.
 *
 * Mutantes esperados: substituição de operadores aritméticos (+, -, *, /)
 * e de valores de retorno constantes.
 */
@DisplayName("Calculadora – Operações Básicas")
class CalculadoraOperacoesBasicasTest {

    private Calculadora calc;

    @BeforeEach
    void setUp() {
        calc = new Calculadora();
    }

    @Test
    @DisplayName("Soma de dois positivos")
    void testSomaPositivos() {
        assertEquals(7.0, calc.somar(3, 4), 1e-9);
    }

    @Test
    @DisplayName("Soma com número negativo")
    void testSomaComNegativo() {
        assertEquals(1.0, calc.somar(5, -4), 1e-9);
    }

    @Test
    @DisplayName("Subtração simples")
    void testSubtracao() {
        assertEquals(6.0, calc.subtrair(10, 4), 1e-9);
    }

    @Test
    @DisplayName("Subtração resulta em negativo")
    void testSubtracaoNegativa() {
        assertEquals(-3.0, calc.subtrair(2, 5), 1e-9);
    }

    @Test
    @DisplayName("Multiplicação de dois positivos")
    void testMultiplicacao() {
        assertEquals(12.0, calc.multiplicar(3, 4), 1e-9);
    }

    @Test
    @DisplayName("Multiplicação por zero")
    void testMultiplicacaoPorZero() {
        assertEquals(0.0, calc.multiplicar(99, 0), 1e-9);
    }

    @Test
    @DisplayName("Divisão exata")
    void testDivisao() {
        assertEquals(2.5, calc.dividir(5, 2), 1e-9);
    }

    @Test
    @DisplayName("Divisão por zero lança exceção")
    void testDivisaoPorZero() {
        assertThrows(ArithmeticException.class, () -> calc.dividir(10, 0));
    }

    @Test
    @DisplayName("Soma com zero retorna o mesmo valor")
    void testSomaComZero() {
        assertEquals(42.0, calc.somar(42, 0), 1e-9);
    }

    @Test
    @DisplayName("Multiplicação por um retorna o mesmo valor")
    void testMultiplicacaoPorUm() {
        assertEquals(7.0, calc.multiplicar(7, 1), 1e-9);
    }
}
