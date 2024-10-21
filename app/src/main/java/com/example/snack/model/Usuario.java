package com.example.snack.model;

public class Usuario {

    private String email_usuario;
    private String nome_usuario;
    private String sobrenome_usuario;
    private String cargo;
    private String senha_usuario;
    private String telefone;

    public Usuario(String email_usuario, String nome_usuario, String sobrenome_usuario, String cargo, String senha_usuario, String telefone) {
        this.email_usuario = email_usuario;
        this.nome_usuario = nome_usuario;
        this.sobrenome_usuario = sobrenome_usuario;
        this.cargo = cargo;
        this.senha_usuario = senha_usuario;
        this.telefone = telefone;
    }

    public String getEmail_usuario() {
        return email_usuario;
    }

    public void setEmail_usuario(String email_usuario) {
        this.email_usuario = email_usuario;
    }

    public String getNome_usuario() {
        return nome_usuario;
    }

    public void setNome_usuario(String nome_usuario) {
        this.nome_usuario = nome_usuario;
    }

    public String getSobrenome_usuario() {
        return sobrenome_usuario;
    }

    public void setSobrenome_usuario(String sobrenome_usuario) {
        this.sobrenome_usuario = sobrenome_usuario;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getSenha_usuario() {
        return senha_usuario;
    }

    public void setSenha_usuario(String senha_usuario) {
        this.senha_usuario = senha_usuario;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
}
