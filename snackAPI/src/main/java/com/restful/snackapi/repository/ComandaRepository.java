package com.restful.snackapi.repository;
import com.restful.snackapi.model.Comanda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComandaRepository extends JpaRepository<Comanda, Long>{
    // Espaço reservado para consultas SQL

}
