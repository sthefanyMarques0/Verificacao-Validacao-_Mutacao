package com.mutacao.service;

import com.mutacao.model.Produto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Serviço de carrinho de compras.
 */
public class CarrinhoService {

    private final List<Produto> itens = new ArrayList<>();
    private double desconto = 0.0;   // percentual 0-100

    public void adicionarItem(Produto p) {
        if (p == null) throw new IllegalArgumentException("Produto não pode ser nulo.");
        if (!p.estaDisponivel()) throw new IllegalStateException("Produto fora de estoque.");
        itens.add(p);
    }

    public void removerItem(Produto p) {
        if (!itens.remove(p)) {
            throw new IllegalArgumentException("Produto não encontrado no carrinho.");
        }
    }

    public List<Produto> getItens() {
        return Collections.unmodifiableList(itens);
    }

    public int quantidadeItens() {
        return itens.size();
    }

    public double subtotal() {
        return itens.stream().mapToDouble(Produto::getPreco).sum();
    }

    public void aplicarDesconto(double percentual) {
        if (percentual < 0 || percentual > 100) {
            throw new IllegalArgumentException("Desconto deve estar entre 0 e 100.");
        }
        this.desconto = percentual;
    }

    public double total() {
        double sub = subtotal();
        return sub - (sub * desconto / 100.0);
    }

    public boolean estaVazio() {
        return itens.isEmpty();
    }

    public void limpar() {
        itens.clear();
        desconto = 0.0;
    }
}
