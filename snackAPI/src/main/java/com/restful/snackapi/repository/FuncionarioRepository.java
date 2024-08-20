package com.restful.snackapi.repository;
import coom.restful.snackapi.model.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long>{
    // Espaço reservado para consultas SQL

}
