package com.restful.snackapi.model;
import jakarta.persistence.*;

@Entity
@Table(name = "Comanda")
public class Comanda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_Comanda;

    @Column(name = "desc_Comanda", nullable = false)
    private String desc_Comanda;

    public Long getId_Comanda() {
        return id_Comanda;
    }

    public void setId_Comanda(Long id_Comanda) {
        this.id_Comanda = id_Comanda;
    }

    public String getDesc_Comanda() {
        return desc_Comanda;
    }

    public void setDesc_Comanda(String desc_Comanda) {
        this.desc_Comanda = desc_Comanda;
    }
}