package com.restful.snackapi.repository;
import coom.restful.snackapi.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long>{
    // Espaço reservado para consultas SQL

}
