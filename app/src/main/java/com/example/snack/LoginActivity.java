package com.example.snack;
import com.example.snack.Auth0Manager;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.snack.R;
import com.auth0.android.result.Credentials;
import com.auth0.android.result.UserProfile;
import com.example.snack.api.ApiService;
import com.example.snack.api.RetrofitClientInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import com.auth0.android.Auth0;
import com.auth0.android.authentication.AuthenticationException;
import com.auth0.android.provider.WebAuthProvider;
import com.auth0.android.result.Credentials;


public class LoginActivity extends AppCompatActivity {
    private Auth0Manager auth0Manager;
    private Auth0 auth0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        auth0 = new Auth0(
                getString(R.string.com_auth0_client_id),
                getString(R.string.com_auth0_domain)

        );
        iniciarLogin();
    }
    public void register(View view){
        startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
    }
    public void iniciarLogin(){
        Log.d("LoginActivity", "Iniciando processo de login......");

        WebAuthProvider.login(auth0)
                .withScheme("com.example.snack")
                .withScope("openid profile email")
                .withAudience("https://dev-r1uf0w8hoq10d3ef.us.auth0.com/userinfo")
                .start(this, new com.auth0.android.callback.Callback<Credentials, AuthenticationException>() {
                    @Override
                    public void onSuccess(Credentials credentials) {
                        // Salvar os tokens de autenticação no SharedPreferences
                        // Utilizar o credentials para fazer chamadas à API do backend
                        Log.d("Auth0", "Autenticado com sucesso:");
                        String accessToken = credentials.getAccessToken();
                        Toast.makeText(LoginActivity.this, "Login feito com sucesso", Toast.LENGTH_SHORT).show();

                        sendTokenToApi(accessToken);
                    }

                    @Override
                    public void onFailure(AuthenticationException exception) {
                        Log.e("Auth0", "Falha ao autenticar", exception);
                        Toast.makeText(LoginActivity.this, "Erro ao autenticar", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void sendTokenToApi(String token){
        ApiService apiService = RetrofitClientInstance.getRetrofitInstance().create(ApiService.class);
        Call<Void> call = apiService.enviarToken(new ApiService.TokenRequest(token));
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(response.isSuccessful()){
                    Log.d("LoginActivity", "Token enviado para API");
                    startActivity(new Intent(LoginActivity.this, ComprasActivity.class));
                    finish();
                } else {
                    Log.e("LoginActivity", "Erro ao enviar token para API: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.e("LoginActivity", "Erro ao enviar token: " + t.getMessage());

            }
        });
    }

    public void esqueceu(View view){
        startActivity(new Intent(LoginActivity.this, SenhaActivity.class));

    }

}