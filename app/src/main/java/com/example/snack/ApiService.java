package com.example.snack;
import com.example.snack.model.Usuario;
import com.example.snack.restful.LoginRequest;
import com.example.snack.restful.LoginResponse;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.GET;
import retrofit2.http.Part;
import retrofit2.http.Path;
import okhttp3.MultipartBody;


public interface ApiService {
    @POST("api/cadastro")
    Call<Usuario> cadastrarUsuario(@Body Usuario usuario);

    @POST("api/login")
    Call<LoginResponse> login(@Body LoginRequest loginRequest);

    @GET("api/usuarios")
    Call<List<Usuario>> getAllUsuarios();


    @Multipart
    @POST("produtos/upload")
    Call<String> uploadImage(@Part MultipartBody.Part file);

}
