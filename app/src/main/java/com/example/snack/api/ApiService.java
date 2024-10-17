package com.example.snack.api;
import android.health.connect.changelog.ChangeLogTokenRequest;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;


public interface ApiService {
    @Headers("Content-Type: application/json")
    @POST("usuarios")
    Call<Void> enviarToken(@Body TokenRequest tokenRequest);

    class TokenRequest{
        String token;

        public TokenRequest(String token) {
            this.token = token;
        }
    }
}
