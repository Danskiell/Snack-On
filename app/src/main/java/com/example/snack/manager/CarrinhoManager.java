package com.example.snack.manager;

import com.example.snack.model.Produto;

import java.util.ArrayList;
import java.util.List;

public class CarrinhoManager {

    private static CarrinhoManager instance; // Singleton
    private final List<Produto> carrinho; // Lista de produtos no carrinho

    private CarrinhoManager() {
        carrinho = new ArrayList<>();
    }

    // Singleton para obter a instância do CarrinhoManager
    public static synchronized CarrinhoManager getInstance() {
        if (instance == null) {
            instance = new CarrinhoManager();
        }
        return instance;
    }

    public void adicionarAoCarrinho(Produto produto) {
        carrinho.add(produto);
    }

    public void removerDoCarrinho(Produto produto) {
        carrinho.remove(produto);
    }

    public List<Produto> listarCarrinho() {
        return carrinho;
    }

    public double calcularTotal() {
        double total = 0;
        for (Produto produto : carrinho) {
            total += produto.getPreco_Produto();
        }
        return total;
    }

    public void limparCarrinho() {
        carrinho.clear();
    }
}
