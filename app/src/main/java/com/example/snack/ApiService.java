package com.example.snack;

import com.example.snack.model.Usuario;
import com.example.snack.model.Produto;
import com.example.snack.restful.LoginRequest;
import com.example.snack.restful.LoginResponse;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

import java.util.List;

public interface ApiService {
    // Cadastro de usuário
    @POST("api/cadastro")
    Call<Usuario> cadastrarUsuario(@Body Usuario usuario);

    // Login
    @POST("api/login")
    Call<LoginResponse> login(@Body LoginRequest loginRequest);

    // Listar todos os usuários
    @GET("api/usuarios")
    Call<List<Usuario>> getAllUsuarios();

    // Cadastro de produto
    @Multipart
    @POST("api/produtos")
    Call<Void> cadastrarProduto(
            @Part("nome") RequestBody nome,
            @Part("preco") RequestBody preco,
            @Part("quantidade") RequestBody quantidade,
            @Part("descricao") RequestBody descricao,
            @Part("categoria") RequestBody categoria,
            @Part MultipartBody.Part imagem
    );

    @GET("api/produtos/categoria/{categoria}")
    Call<List<Produto>> getProdutosPorCategoria(@Path("categoria") String categoria);

    }
