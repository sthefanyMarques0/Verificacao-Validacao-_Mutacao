package com.mutacao.util;

/**
 * Validação e força de senhas.
 */
public class Senha {

    public enum Forca { FRACA, MEDIA, FORTE }

    /** Mínimo 8 caracteres, ao menos 1 dígito e 1 letra. */
    public boolean ehValida(String senha) {
        if (senha == null || senha.length() < 8) return false;
        boolean temDigito = false;
        boolean temLetra = false;
        for (char c : senha.toCharArray()) {
            if (Character.isDigit(c))  temDigito = true;
            if (Character.isLetter(c)) temLetra  = true;
        }
        return temDigito && temLetra;
    }

    public Forca calcularForca(String senha) {
        if (!ehValida(senha)) return Forca.FRACA;

        boolean temMaiuscula  = senha.chars().anyMatch(Character::isUpperCase);
        boolean temMinuscula  = senha.chars().anyMatch(Character::isLowerCase);
        boolean temEspecial   = senha.chars().anyMatch(c -> !Character.isLetterOrDigit(c));
        boolean tamanhoGrande = senha.length() >= 12;

        int pontos = 0;
        if (temMaiuscula)  pontos++;
        if (temMinuscula)  pontos++;
        if (temEspecial)   pontos++;
        if (tamanhoGrande) pontos++;

        if (pontos >= 3) return Forca.FORTE;
        if (pontos >= 1) return Forca.MEDIA;
        return Forca.FRACA;
    }
}
