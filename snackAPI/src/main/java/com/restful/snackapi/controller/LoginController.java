package com.restful.snackapi.controller;
import com.restful.snackapi.model.Usuario;
import com.restful.snackapi.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/login")
public class LoginController {

    @Autowired
    private UsuarioService usuarioService;

    // Endpoint para o login do usuário
    @PostMapping
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        try {
            Usuario usuario = usuarioService.
                    login(loginRequest.getEmail(), loginRequest.getSenha());

            // Gerando token JWT para o usuário que foi autenticado
            String token = usuarioService.gerarToken(usuario);

            // Retornando usuario junto com o token se for bem sucedido
            return ResponseEntity.ok(new LoginResponse(usuario, token));

        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

    }

    // Classe para receber os dados vindo da requisição de login
    public static class LoginRequest {
        private String email;
        private String senha;

        // Getters e setters
        public String getEmail() {
            return email;
        }

        public String getSenha() {
            return senha;
        }
    }

    // Classe com a resposta do usuario com o token
    public static class LoginResponse {
        private Usuario usuario;
        private String token;

        public LoginResponse(Usuario usuario, String token) {
            this.usuario = usuario;
            this.token = token;
        }

        // Getters e setters

        public Usuario getUsuario() {
            return usuario;
        }

        public String getToken() {
            return token;
        }
    }





}
