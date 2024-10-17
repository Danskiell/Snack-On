package com.restful.snackapi.controller;
import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.restful.snackapi.model.Usuario;
import com.restful.snackapi.security.JWTUtil;
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

    @GetMapping
    public ResponseEntity<?> getAllUsuarios(@RequestHeader("Authorization") String token){
        try {
            String email = JWTUtil.validateToken(token.replace("Bearer ", ""));
            return ResponseEntity.ok(usuarioService.getAllUsuarios());
        }catch (Exception e){
            return ResponseEntity.status(401).body("Token não é valido");
        }
    }

    @PostMapping
    public ResponseEntity<Usuario> createUsuario(@RequestBody Usuario usuario){
        Usuario savedUsuario = usuarioService.saveUsuario(usuario);
        return ResponseEntity.ok(savedUsuario);
    }





}
