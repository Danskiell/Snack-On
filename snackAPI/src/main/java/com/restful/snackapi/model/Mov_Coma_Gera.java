package com.seuprojeto.model;
import javax.persistence.*;

@Entity
@Table(name="Mov_Coma_Gera")
public class Mov_Coma_Gera {
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