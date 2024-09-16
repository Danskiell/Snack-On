package com.restful.snackapi.model;
import jakarta.persistence.*;
import java.io.Serializable;
@Entity
@Table(name="Mov_Coma_Gera")
@IdClass(MovComaGeraId.class)
public class Mov_Coma_Gera implements Serializable{
    @Id
    @ManyToOne
    @JoinColumn(name = "fk_Mov_Id_Mov", nullable = false)
    private Movimentacao movimentacao;

    @Id
    @ManyToOne
    @JoinColumn(name = "fk_Comanda_Id_Coma", nullable = false)
    private Comanda comanda;


    public Movimentacao getMovimentacao() {
        return movimentacao;
    }

    public void setMovimentacao(Movimentacao movimentacao) {
        this.movimentacao = movimentacao;
    }

    public Comanda getComanda() {
        return comanda;
    }

    public void setComanda(Comanda comanda) {
        this.comanda = comanda;
    }
}