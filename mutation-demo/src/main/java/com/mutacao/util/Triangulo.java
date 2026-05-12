package com.mutacao.util;

/**
 * Classifica e valida triângulos.
 */
public class Triangulo {

    public enum Tipo { EQUILATERO, ISOSCELES, ESCALENO, INVALIDO }

    public Tipo classificar(double a, double b, double c) {
        if (!ehValido(a, b, c)) {
            return Tipo.INVALIDO;
        }
        if (a == b && b == c) {
            return Tipo.EQUILATERO;
        }
        if (a == b || b == c || a == c) {
            return Tipo.ISOSCELES;
        }
        return Tipo.ESCALENO;
    }

    public boolean ehValido(double a, double b, double c) {
        if (a <= 0 || b <= 0 || c <= 0) return false;
        return (a + b > c) && (a + c > b) && (b + c > a);
    }

    public double perimetro(double a, double b, double c) {
        if (!ehValido(a, b, c)) {
            throw new IllegalArgumentException("Triângulo inválido.");
        }
        return a + b + c;
    }

    public double area(double a, double b, double c) {
        if (!ehValido(a, b, c)) {
            throw new IllegalArgumentException("Triângulo inválido.");
        }
        double s = perimetro(a, b, c) / 2.0;
        return Math.sqrt(s * (s - a) * (s - b) * (s - c));
    }
}
