package com.restful.snackapi.model;
import jakarta.persistence.*;

@Entity
@Table(name = "Funcionario")
public class Funcionario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_Fun;

    @ManyToOne
    @JoinColumn(name = "id_Usuario", nullable = false)
    private Usuario usuario;

    public Long getId_Fun() {
        return id_Fun;
    }

    public void setId_Fun(Long id_Fun) {
        this.id_Fun = id_Fun;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}