package com.restful.snackapi.repository;
import com.restful.snackapi.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
    // Espaço reservado para consultas SQL
    @Query("SELECT u FROM Usuario u WHERE u.email_Usuario = ?1")
    Optional<Usuario> findByEmail_Usuario(String email_Usuario);

}
