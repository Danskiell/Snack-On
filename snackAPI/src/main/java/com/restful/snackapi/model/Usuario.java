package com.restful.snackapi.model;
import jakarta.persistence.*;

@Entity
@Table(name = "Usuario")
public class Usuario{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_Usuario;

    @Column(name = "email_Usuario", nullable = false, unique = true)
    private String email_Usuario;

    @Column(name = "nome_Usuario", nullable = false)
    private String nome_Usuario;

    @Column(name = "fone_Usuario", nullable = false)
    private String fone_Usuario;

    @Column(name = "sobrenome_Usuario", nullable = false)
    private String sobrenome_Usuario;

    @Column(name = "senha", nullable = false)
    private String senha;

    @Column(name = "cargo", nullable = false)
    private String cargo;

    public Long getId_Usuario() {
        return id_Usuario;
    }

    public void setId_Usuario(Long id_Usuario) {
        this.id_Usuario = id_Usuario;
    }

    public String getEmail_Usuario() {
        return email_Usuario;
    }

    public void setEmail_Usuario(String email_Usuario) {
        this.email_Usuario = email_Usuario;
    }

    public String getNome_Usuario() {
        return nome_Usuario;
    }

    public void setNome_Usuario(String nome_Usuario) {
        this.nome_Usuario = nome_Usuario;
    }

    public String getFone_Usuario() {
        return fone_Usuario;
    }

    public void setFone_Usuario(String fone_Usuario) {
        this.fone_Usuario = fone_Usuario;
    }

    public String getSobrenome_Usuario() {
        return sobrenome_Usuario;
    }

    public void setSobrenome_Usuario(String sobrenome_Usuario) {
        this.sobrenome_Usuario = sobrenome_Usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }
}