package com.restful.snackapi.model;
import java.io.Serializable;
import java.util.Objects;
import jakarta.persistence.Embeddable;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;

@Embeddable
public class MovComaGeraId implements Serializable{

    private Long movimentacao;
    private Long comanda;

    public  MovComaGeraId(){}

public MovComaGeraId(Long movimentacao, Long comanda){
    this.movimentacao = movimentacao;
    this.comanda = comanda;
}

    public Long getMovimentacao() {
        return movimentacao;
    }

    public void setMovimentacao(Long movimentacao) {
        this.movimentacao = movimentacao;
    }

    public Long getComanda() {
        return comanda;
    }

    public void setComanda(Long comanda) {
        this.comanda = comanda;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o) return true;
        if(o == null || getClass()!= o.getClass()) return false;
        MovComaGeraId that = (MovComaGeraId) o;
        return Objects.equals(movimentacao, that.movimentacao) && Objects.equals(comanda, that.comanda);

    }
    @Override
    public int hashCode() {
        return Objects.hash(movimentacao, comanda);
    }


}
