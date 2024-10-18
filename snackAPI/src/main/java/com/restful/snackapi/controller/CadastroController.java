package com.restful.snackapi.controller;
import com.restful.snackapi.model.Usuario;
import com.restful.snackapi.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cadastro")
public class CadastroController {

    @Autowired
    private UsuarioService usuarioService;

    // Endpoint para criação de novo usuário
    @PostMapping
    public ResponseEntity<?> cadastrarUsuario(
        @RequestBody Usuario usuario){
        try {
            // Chamando o service de registro de usuario
            Usuario novoUsuario = usuarioService.cadastrarUsuario(usuario);

            // Gerando o token de novo usuario
            String token = usuarioService.gerarToken(novoUsuario);

            // Retorna usuario junto com o toke que foi gerado
            return ResponseEntity.status(HttpStatus.CREATED).body(
                    new CadastroResponse(novoUsuario, token)

            );
        }catch (RuntimeException e) {
            if (e.getMessage().equals("Email já cadastrado!")){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro no servidor Snack");
        }

    }

    // Classe para a resposta que terá o usuario e o token
    public static class CadastroResponse {
        private Usuario usuario;
        private String token;

        public CadastroResponse(Usuario usuario, String token) {
            this.usuario = usuario;
            this.token = token;
        }

        public Usuario getUsuario() {
            return usuario;
        }

        public String getToken() {
            return token;
        }

    }

}
