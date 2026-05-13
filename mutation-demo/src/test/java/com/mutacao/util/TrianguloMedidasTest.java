package com.mutacao.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


@DisplayName("Triangulo - Medidas")
class TrianguloMedidasTest {

    private Triangulo triangulo;

    @BeforeEach
    void setUp() {
        triangulo = new Triangulo();
    }

    @Test
    @DisplayName("Perimetro de equilatero de lado 3")
    void testPerimetroEquilatero() {
        assertEquals(9.0, triangulo.perimetro(3, 3, 3), 1e-9);
    }

    @Test
    @DisplayName("Perimetro de escaleno")
    void testPerimetroEscaleno() {
        assertEquals(12.0, triangulo.perimetro(3, 4, 5), 1e-9);
    }

    @Test
    @DisplayName("Perimetro de triangulo invalido lanca excecao")
    void testPerimetroInvalido() {
        assertThrows(IllegalArgumentException.class,
                () -> triangulo.perimetro(1, 2, 10));
    }

    @Test
    @DisplayName("Area do triangulo retangulo 3-4-5")
    void testAreaRetangulo345() {
        assertEquals(6.0, triangulo.area(3, 4, 5), 1e-6);
    }

    @Test
    @DisplayName("Area do equilatero de lado 2")
    void testAreaEquilatero() {
        assertEquals(Math.sqrt(3), triangulo.area(2, 2, 2), 1e-6);
    }

    @Test
    @DisplayName("Area de triangulo invalido lanca excecao")
    void testAreaInvalido() {
        assertThrows(IllegalArgumentException.class,
                () -> triangulo.area(0, 4, 4));
    }

    @Test
    @DisplayName("Verificacao de validade positiva")
    void testEhValidoPositivo() {
        assertTrue(triangulo.ehValido(5, 6, 7));
    }

    @Test
    @DisplayName("Verificacao de validade negativa - degenerado")
    void testEhValidoNegativo() {
        assertFalse(triangulo.ehValido(1, 1, 3));
    }

    @Test
    @DisplayName("Area do isosceles 5-5-6")
    void testAreaIsosceles() {
        assertEquals(12.0, triangulo.area(5, 5, 6), 1e-6);
    }

    @Test
    @DisplayName("Perimetro com lados decimais")
    void testPerimetroDecimal() {
        assertEquals(10.5, triangulo.perimetro(3.5, 3.5, 3.5), 1e-9);
    }
}
