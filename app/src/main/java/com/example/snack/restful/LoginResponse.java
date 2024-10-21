package com.example.snack.restful;

import com.example.snack.model.Usuario;

public class LoginResponse {
    private Usuario usuario;
    private String token;

    public LoginResponse(Usuario usuario, String token) {
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
