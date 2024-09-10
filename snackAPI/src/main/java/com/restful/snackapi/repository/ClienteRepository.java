package com.restful.snackapi.repository;
import com.restful.snackapi.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long>{
    // Espaço reservado para consultas SQL

}
