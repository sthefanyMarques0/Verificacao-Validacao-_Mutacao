package com.mutacao.model;

import java.util.Objects;


public class Produto {

    private final String nome;
    private double preco;
    private int quantidade;

    public Produto(String nome, double preco, int quantidade) {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("Nome não pode ser vazio.");
        }
        if (preco < 0) {
            throw new IllegalArgumentException("Preço não pode ser negativo.");
        }
        if (quantidade < 0) {
            throw new IllegalArgumentException("Quantidade não pode ser negativa.");
        }
        this.nome = nome;
        this.preco = preco;
        this.quantidade = quantidade;
    }

    public String getNome()       { return nome; }
    public double getPreco()      { return preco; }
    public int    getQuantidade() { return quantidade; }

    public void setPreco(double preco) {
        if (preco < 0) throw new IllegalArgumentException("Preço não pode ser negativo.");
        this.preco = preco;
    }

    public void adicionarEstoque(int qtd) {
        if (qtd <= 0) throw new IllegalArgumentException("Quantidade deve ser positiva.");
        this.quantidade += qtd;
    }

    public void removerEstoque(int qtd) {
        if (qtd <= 0) throw new IllegalArgumentException("Quantidade deve ser positiva.");
        if (qtd > this.quantidade) throw new IllegalStateException("Estoque insuficiente.");
        this.quantidade -= qtd;
    }

    public double valorTotalEmEstoque() {
        return preco * quantidade;
    }

    public boolean estaDisponivel() {
        return quantidade > 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Produto p)) return false;
        return nome.equals(p.nome);
    }

    @Override
    public int hashCode() { return Objects.hash(nome); }

    @Override
    public String toString() {
        return "Produto{nome='%s', preco=%.2f, quantidade=%d}".formatted(nome, preco, quantidade);
    }
}
