package com.restful.snackapi.repository;
import com.restful.snackapi.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
    // Espaço reservado para consultas SQL

}
