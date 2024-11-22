package com.example.snack.model;

import java.util.List;

public class Pedido {
    private long idPedido; // ID único para cada pedido
    private List<Produto> produtos; // Produtos no pedido
    private double total; // Valor total do pedido
    private String dataPedido; // Data do pedido

    public Pedido(long idPedido, List<Produto> produtos, double total, String dataPedido) {
        this.idPedido = idPedido;
        this.produtos = produtos;
        this.total = total;
        this.dataPedido = dataPedido;
    }

    public long getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(long idPedido) {
        this.idPedido = idPedido;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(String dataPedido) {
        this.dataPedido = dataPedido;
    }
}
