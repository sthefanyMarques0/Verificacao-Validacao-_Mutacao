package com.mutacao.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Teste 7 – Conta bancária: depósito, saque e transferência.
 *
 * Mutantes esperados: troca de += por -=, inversão de verificação de saldo
 * (> → >=), remoção da guarda de conta ativa.
 */
@DisplayName("Conta – Operações Bancárias")
class ContaTest {

    private Conta contaOrigem;
    private Conta contaDestino;

    @BeforeEach
    void setUp() {
        contaOrigem  = new Conta("Alice", 1000.0);
        contaDestino = new Conta("Bob", 500.0);
    }

    @Test
    @DisplayName("Saldo inicial correto")
    void testSaldoInicial() {
        assertEquals(1000.0, contaOrigem.getSaldo(), 1e-9);
    }

    @Test
    @DisplayName("Depósito aumenta o saldo")
    void testDeposito() {
        contaOrigem.depositar(200.0);
        assertEquals(1200.0, contaOrigem.getSaldo(), 1e-9);
    }

    @Test
    @DisplayName("Saque diminui o saldo")
    void testSaque() {
        contaOrigem.sacar(300.0);
        assertEquals(700.0, contaOrigem.getSaldo(), 1e-9);
    }

    @Test
    @DisplayName("Saque maior que saldo lança exceção")
    void testSaqueInsuficiente() {
        assertThrows(IllegalStateException.class, () -> contaOrigem.sacar(1500.0));
    }

    @Test
    @DisplayName("Saque de valor zero ou negativo lança exceção")
    void testSaqueZeroLancaExcecao() {
        assertThrows(IllegalArgumentException.class, () -> contaOrigem.sacar(0));
    }

    @Test
    @DisplayName("Transferência move valor entre contas corretamente")
    void testTransferencia() {
        contaOrigem.transferir(contaDestino, 400.0);
        assertEquals(600.0, contaOrigem.getSaldo(), 1e-9);
        assertEquals(900.0, contaDestino.getSaldo(), 1e-9);
    }

    @Test
    @DisplayName("Conta encerrada não aceita depósito")
    void testContaEncerradaDeposito() {
        contaOrigem.encerrar();
        assertThrows(IllegalStateException.class, () -> contaOrigem.depositar(100.0));
    }

    @Test
    @DisplayName("Conta encerrada não aceita saque")
    void testContaEncerradaSaque() {
        contaOrigem.encerrar();
        assertThrows(IllegalStateException.class, () -> contaOrigem.sacar(100.0));
    }

    @Test
    @DisplayName("Titular inválido lança exceção")
    void testTitularInvalido() {
        assertThrows(IllegalArgumentException.class, () -> new Conta("", 0));
    }

    @Test
    @DisplayName("Conta recém-criada está ativa")
    void testContaAtiva() {
        assertTrue(contaOrigem.isAtiva());
    }
}
