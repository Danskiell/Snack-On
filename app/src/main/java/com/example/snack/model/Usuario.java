package com.example.snack.model;

public class Usuario {

    private String email_Usuario;
    private String nome_Usuario;
    private String sobrenome_Usuario;
    private String cargo;
    private String senha;
    private String fone_Usuario;

    public Usuario(String email_usuario, String nome_usuario, String sobrenome_usuario, String cargo, String senha_usuario, String telefone) {
        this.email_Usuario = email_usuario;
        this.nome_Usuario = nome_usuario;
        this.sobrenome_Usuario = sobrenome_usuario;
        this.cargo = cargo;
        this.senha = senha_usuario;
        this.fone_Usuario = telefone;
    }

    public String getEmail_usuario() {
        return email_Usuario;
    }

    public void setEmail_usuario(String email_usuario) {
        this.email_Usuario = email_usuario;
    }

    public String getNome_usuario() {
        return nome_Usuario;
    }

    public void setNome_usuario(String nome_usuario) {
        this.nome_Usuario = nome_usuario;
    }

    public String getSobrenome_usuario() {
        return sobrenome_Usuario;
    }

    public void setSobrenome_usuario(String sobrenome_usuario) {
        this.sobrenome_Usuario = sobrenome_usuario;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getSenha_usuario() {
        return senha;
    }

    public void setSenha_usuario(String senha_usuario) {
        this.senha = senha_usuario;
    }

    public String getTelefone() {
        return fone_Usuario;
    }

    public void setTelefone(String telefone) {
        this.fone_Usuario = telefone;
    }
}
