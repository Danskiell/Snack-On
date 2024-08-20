package com.restful.snackapi.repository;
import coom.restful.snackapi.model.categ_Prod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategProdRepository extends JpaRepository<categ_Prod, Long>{
    // Espaço reservado para consultas SQL

}
