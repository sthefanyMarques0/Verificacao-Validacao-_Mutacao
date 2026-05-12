package com.mutacao.util;

/**
 * Operações aritméticas básicas.
 */
public class Calculadora {

    public double somar(double a, double b) {
        return a + b;
    }

    public double subtrair(double a, double b) {
        return a - b;
    }

    public double multiplicar(double a, double b) {
        return a * b;
    }

    public double dividir(double a, double b) {
        if (b == 0) {
            throw new ArithmeticException("Divisão por zero não permitida.");
        }
        return a / b;
    }

    public double potencia(double base, int expoente) {
        if (expoente < 0) {
            throw new IllegalArgumentException("Expoente não pode ser negativo.");
        }
        double resultado = 1;
        for (int i = 0; i < expoente; i++) {
            resultado *= base;
        }
        return resultado;
    }

    public boolean ehPar(int numero) {
        return numero % 2 == 0;
    }

    public int maximo(int a, int b) {
        return a >= b ? a : b;
    }

    public int minimo(int a, int b) {
        return a <= b ? a : b;
    }
}
