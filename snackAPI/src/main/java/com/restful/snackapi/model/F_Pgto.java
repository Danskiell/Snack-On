package com.restful.snackapi.model;
import jakarta.persistence.*;

@Entity
@Table(name = "F_Pgto")
public class F_Pgto{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_Pgto;

    @Column(name = "nome_Pgto", nullable = false)
    private String nome_Pgto;

    public Long getId_Pgto() {
        return id_Pgto;
    }

    public void setId_Pgto(Long id_Pgto) {
        this.id_Pgto = id_Pgto;
    }

    public String getNome_Pgto() {
        return nome_Pgto;
    }

    public void setNome_Pgto(String nome_Pgto) {
        this.nome_Pgto = nome_Pgto;
    }
}