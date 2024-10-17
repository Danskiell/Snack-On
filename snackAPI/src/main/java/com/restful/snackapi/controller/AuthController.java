package com.restful.snackapi.controller;
import com.restful.snackapi.model.Usuario;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import com.restful.snackapi.service.UsuarioService;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/auth")

public class AuthController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private final String SECRET_KEY = "snackonmelhorappdecantinas";

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody Usuario loginRequest){
       Optional<Usuario> optionalUsuario = usuarioService.getUsuarioByEmail(loginRequest.getEmail_Usuario());

        if (optionalUsuario.isPresent() && passwordEncoder.matches
                (loginRequest.getSenha(), optionalUsuario.get().getSenha())){

            String token = generateToken(optionalUsuario.get().getEmail_Usuario());
            Map<String, String> response = new HashMap<>();
            response.put("token", token);
            return response;
        }else{
            throw new RuntimeException("Erro ao processar as credenciais");
        }

    }

    private String generateToken(String email){
        return Jwts.builder()
                .setSubject(email)
               .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 3600000))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY.getBytes())
                .compact();
    }


}
