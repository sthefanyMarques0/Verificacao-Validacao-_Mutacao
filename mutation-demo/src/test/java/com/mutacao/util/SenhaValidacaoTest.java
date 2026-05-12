package com.mutacao.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static com.mutacao.util.Senha.Forca.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Teste 5 – Validação e força de senhas.
 *
 * Mutantes esperados: alteração do limiar de tamanho (< 8 → <= 8),
 * inversão de flags booleanas, alteração de limiar de pontos (>= 3 → > 3).
 */
@DisplayName("Senha – Validação e Força")
class SenhaValidacaoTest {

    private Senha senha;

    @BeforeEach
    void setUp() {
        senha = new Senha();
    }

    // --- ehValida ---

    @Test
    @DisplayName("Senha nula é inválida")
    void testNulaInvalida() {
        assertFalse(senha.ehValida(null));
    }

    @Test
    @DisplayName("Senha com menos de 8 caracteres é inválida")
    void testCurtaInvalida() {
        assertFalse(senha.ehValida("Ab1"));
    }

    @Test
    @DisplayName("Senha com exatamente 8 caracteres, letra e dígito é válida")
    void testExatamente8Valida() {
        assertTrue(senha.ehValida("Abcde1fg")); // 8 chars, tem letra e dígito
    }

    @Test
    @DisplayName("Senha sem dígito é inválida")
    void testSemDigitoInvalida() {
        assertFalse(senha.ehValida("abcdefgh")); // 8 chars, sem dígito
    }

    @Test
    @DisplayName("Senha sem letra é inválida")
    void testSemLetraInvalida() {
        assertFalse(senha.ehValida("12345678")); // 8 chars, sem letra
    }

    // --- calcularForca ---

    @Test
    @DisplayName("Senha inválida retorna força FRACA")
    void testForcaFraca() {
        assertEquals(FRACA, senha.calcularForca("abc"));
    }

    @Test
    @DisplayName("Senha apenas minúsculas + dígito retorna MEDIA")
    void testForcaMedia() {
        // Tem minúscula, tem dígito mas não tem maiúscula, especial e < 12 chars
        assertEquals(MEDIA, senha.calcularForca("abcde1fg")); // pontos = 1 (minúscula)
    }

    @Test
    @DisplayName("Senha com maiúscula, minúscula e especial retorna FORTE")
    void testForcaForte() {
        assertEquals(FORTE, senha.calcularForca("Abcde1@h")); // pontos: maiúscula, minúscula, especial = 3
    }

    @Test
    @DisplayName("Senha com 12+ chars, maiúscula, minúscula e especial retorna FORTE")
    void testForcaForteLonga() {
        assertEquals(FORTE, senha.calcularForca("Abcde1@hIjkL")); // pontos = 4
    }

    @ParameterizedTest(name = "Senha válida: {0}")
    @ValueSource(strings = {"Senha123", "pass1234", "ABCDEF1g", "test1234"})
    @DisplayName("Senhas que possuem letra e dígito com 8+ chars são válidas")
    void testSenhasValidas(String s) {
        assertTrue(senha.ehValida(s));
    }
}
