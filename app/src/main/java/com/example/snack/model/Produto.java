package com.example.snack.model;

public class Produto {
    private Long id_Produto;
    private String nome_Produto;
    private Double preco_Produto;
    private Integer qntd_Produto;
    private String categoria;
    private String descricao;  // Novo campo para descrição
    private String imageUrl;

    // Getters e Setters

    public Long getId_Produto() {
        return id_Produto;
    }

    public void setId_Produto(Long id_Produto) {
        this.id_Produto = id_Produto;
    }

    public String getNome_Produto() {
        return nome_Produto;
    }

    public void setNome_Produto(String nome_Produto) {
        this.nome_Produto = nome_Produto;
    }

    public Double getPreco_Produto() {
        return preco_Produto;
    }

    public void setPreco_Produto(Double preco_Produto) {
        this.preco_Produto = preco_Produto;
    }

    public Integer getQntd_Produto() {
        return qntd_Produto;
    }

    public void setQntd_Produto(Integer qntd_Produto) {
        this.qntd_Produto = qntd_Produto;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getDescricao() {
        return descricao;  // Getter para a descrição
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;  // Setter para a descrição
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
