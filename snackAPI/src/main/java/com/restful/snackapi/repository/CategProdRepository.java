package com.restful.snackapi.repository;
import com.restful.snackapi.model.Categ_Prod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategProdRepository extends JpaRepository<categ_Prod, Long>{
    // Espaço reservado para consultas SQL

}
