package com.example.snack;
import com.example.snack.model.Usuario;
import com.example.snack.restful.LoginRequest;
import com.example.snack.restful.LoginResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiService {
    @POST("api/cadastro")
    Call<Usuario> cadastrarUsuario(@Body Usuario usuario);

    @POST("api/login")
    Call<LoginResponse> login(@Body LoginRequest loginRequest);

    @GET("api/usuarios")
    Call<List<Usuario>> getAllUsuarios();

}
