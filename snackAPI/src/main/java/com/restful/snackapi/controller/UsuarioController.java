package com.restful.snackapi.controller;
import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.restful.snackapi.model.Usuario;
import com.restful.snackapi.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    private final String SECRET = System.getenv(("JWT_SECRET"));


    // Processo 1 -  Listando todos os usuarios
    @GetMapping
    public List<Usuario> getAllUsuarios() {
        return usuarioService.getAllUsuarios();
    }
    // Processo 2 - Buscar Usuário por ID
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> getUsuarioById(@PathVariable Long id) {
        Optional<Usuario> usuarioOptional = usuarioService.getUsuarioById(id);
        if(usuarioOptional.isPresent()){
            return ResponseEntity.ok(usuarioOptional.get());
        }
        return ResponseEntity.notFound().build();
    }

    // Criando usuário com o Auth0 para a emtidade do usuário
    @PostMapping
    public ResponseEntity<Usuario> createUsuarioWithAuth(
            @RequestHeader("Authorization") String token,
            @RequestBody Usuario usuario) {
        try {
            // Verificando o token JWT
            DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC256(SECRET))
                    .build()
                    .verify(token.replace("Bearer ", ""));
            // Pegando o email do token gerado
            String email = decodedJWT.getClaim("email").asString();

            // Verificando se o usuario ja tem no banco
            Optional<Usuario> existingUser = usuarioService.getUsuarioByEmail(email);
            if(existingUser.isPresent()) {
                return ResponseEntity.ok(existingUser.get()); // Usuario ja existe
            }

            // Agora se o usuario nao existir mesmo, criar um novo
            Usuario savedUsuario = usuarioService.saveUsuario(usuario);
            return ResponseEntity.ok(savedUsuario);

        }catch (JWTVerificationException e) {
            return ResponseEntity.status(401).build();
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> updateUsuario(@PathVariable Long id, @RequestBody Usuario usuario) {
        Optional<Usuario> existingUsuarioOptional = usuarioService.getUsuarioById(id);
        if(existingUsuarioOptional.isPresent()){
            Usuario existingUsuario = existingUsuarioOptional.get();
            existingUsuario.setNome_Usuario(usuario.getNome_Usuario());
            existingUsuario.setEmail_Usuario(usuario.getEmail_Usuario());

            usuarioService.saveUsuario(existingUsuario);
            return ResponseEntity.ok(existingUsuario);
        }
        return ResponseEntity.notFound().build();

    }



}
