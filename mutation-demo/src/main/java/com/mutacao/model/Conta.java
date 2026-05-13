package com.mutacao.model;


public class Conta {

    private final String titular;
    private double saldo;
    private boolean ativa;

    public Conta(String titular, double saldoInicial) {
        if (titular == null || titular.isBlank()) {
            throw new IllegalArgumentException("Titular não pode ser vazio.");
        }
        if (saldoInicial < 0) {
            throw new IllegalArgumentException("Saldo inicial não pode ser negativo.");
        }
        this.titular = titular;
        this.saldo   = saldoInicial;
        this.ativa   = true;
    }

    public void depositar(double valor) {
        validarAtiva();
        if (valor <= 0) throw new IllegalArgumentException("Valor de depósito deve ser positivo.");
        saldo += valor;
    }

    public void sacar(double valor) {
        validarAtiva();
        if (valor <= 0) throw new IllegalArgumentException("Valor de saque deve ser positivo.");
        if (valor > saldo) throw new IllegalStateException("Saldo insuficiente.");
        saldo -= valor;
    }

    public void transferir(Conta destino, double valor) {
        this.sacar(valor);
        destino.depositar(valor);
    }

    public void encerrar() {
        validarAtiva();
        this.ativa = false;
    }

    public boolean isAtiva()      { return ativa; }
    public double  getSaldo()     { return saldo; }
    public String  getTitular()   { return titular; }

    private void validarAtiva() {
        if (!ativa) throw new IllegalStateException("Conta encerrada.");
    }
}
