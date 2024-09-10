package com.restful.snackapi.model;
import jakarta.persistence.*;

@Entity
@Table(name = "Mov_Pgto_Tem")
public class Mov_Pgto_Tem{
    @Id
    @ManyToOne
    @JoinColumn(name = "fk_Movimentacao_id_Mov", nullable = false)
    private Movimentacao movimentacao;

    @Id
    @ManyToOne
    @JoinColumn(name = "fk_F_Pgto_id_Pgto", nullable = false)
    private F_Pgto f_Pgto;

    public Movimentacao getMovimentacao() {
        return movimentacao;
    }

    public void setMovimentacao(Movimentacao movimentacao) {
        this.movimentacao = movimentacao;
    }

    public F_Pgto getF_Pgto() {
        return f_Pgto;
    }

    public void setF_Pgto(F_Pgto f_Pgto) {
        this.f_Pgto = f_Pgto;
    }
}