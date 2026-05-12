package com.mutacao.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.mutacao.util.Triangulo.Tipo.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Teste 3 – Classificação de triângulos.
 *
 * Mutantes esperados: inversão de condições de igualdade (== → !=),
 * alteração de operadores lógicos (&& → ||) e troca de tipos retornados.
 */
@DisplayName("Triângulo – Classificação")
class TrianguloClassificacaoTest {

    private Triangulo triangulo;

    @BeforeEach
    void setUp() {
        triangulo = new Triangulo();
    }

    @Test
    @DisplayName("Equilátero com lados iguais")
    void testEquilatero() {
        assertEquals(EQUILATERO, triangulo.classificar(5, 5, 5));
    }

    @Test
    @DisplayName("Isósceles com dois lados iguais (a == b)")
    void testIsoscelesAB() {
        assertEquals(ISOSCELES, triangulo.classificar(4, 4, 3));
    }

    @Test
    @DisplayName("Isósceles com dois lados iguais (b == c)")
    void testIsoscelesBC() {
        assertEquals(ISOSCELES, triangulo.classificar(3, 4, 4));
    }

    @Test
    @DisplayName("Isósceles com dois lados iguais (a == c)")
    void testIsoscelesAC() {
        assertEquals(ISOSCELES, triangulo.classificar(4, 3, 4));
    }

    @Test
    @DisplayName("Escaleno com três lados diferentes")
    void testEscaleno() {
        assertEquals(ESCALENO, triangulo.classificar(3, 4, 5));
    }

    @Test
    @DisplayName("Inválido quando soma de dois lados não supera o terceiro")
    void testInvalidoDesigualdade() {
        assertEquals(INVALIDO, triangulo.classificar(1, 2, 10));
    }

    @Test
    @DisplayName("Inválido quando lado é zero")
    void testInvalidoLadoZero() {
        assertEquals(INVALIDO, triangulo.classificar(0, 4, 4));
    }

    @Test
    @DisplayName("Inválido quando lado é negativo")
    void testInvalidoLadoNegativo() {
        assertEquals(INVALIDO, triangulo.classificar(-1, 4, 4));
    }

    @Test
    @DisplayName("Escaleno com lados decimais")
    void testEscalenoDecimal() {
        assertEquals(ESCALENO, triangulo.classificar(2.5, 3.5, 4.5));
    }

    @Test
    @DisplayName("Inválido quando dois lados somam exatamente o terceiro")
    void testInvalidoSomaExata() {
        // a + b == c  →  não satisfaz desigualdade estrita
        assertEquals(INVALIDO, triangulo.classificar(3, 4, 7));
    }
}
