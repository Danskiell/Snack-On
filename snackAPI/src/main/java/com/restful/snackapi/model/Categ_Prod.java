package com.restful.snackapi.model;
import jakarta.persistence.*;

@Entity
@Table(name = "categ_Prod")
public class Categ_Prod {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_Categ;

    @Column(name = "tipo_Categ", nullable = false)
    private String tipo_Categ;

    public Long getId_Categ() {
        return id_Categ;
    }

    public void setId_Categ(Long id_Categ) {
        this.id_Categ = id_Categ;
    }

    public String getTipo_Categ() {
        return tipo_Categ;
    }

    public void setTipo_Categ(String tipo_Categ) {
        this.tipo_Categ = tipo_Categ;
    }
}