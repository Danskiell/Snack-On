package com.restful.snackapi.repository;
import coom.restful.snackapi.model.Movimentacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovimentacaoRepository extends JpaRepository<Movimentacao, Long>{
    // Espaço reservado para consultas SQL

}
