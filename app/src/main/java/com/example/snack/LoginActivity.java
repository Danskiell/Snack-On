package com.example.snack;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.snack.ui.MacaraTelefone;
import com.example.snack.model.Usuario;
import com.example.snack.restful.LoginRequest;
import com.example.snack.restful.LoginResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginActivity extends AppCompatActivity {
    private EditText emailEditText , senhaEditText;
    private Button btnLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        // ID dos inputs de email e senha
        emailEditText = findViewById(R.id.editText6);
        senhaEditText = findViewById(R.id.editText7);
        Button loginButton = findViewById(R.id.btnLogin);
        loginButton.setOnClickListener(this::logar);
    }
    public void register(View view) {
        startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
    }

    private void logar(View view) {
        String email = emailEditText.getText().toString();
        String senha = senhaEditText.getText().toString();
        if (email.isEmpty() || senha.isEmpty()) {
            Toast.makeText(this, "Preencha todos os campos!", Toast.LENGTH_SHORT).show();
            return;
        }
        LoginRequest loginRequest = new LoginRequest(email, senha);
        ApiService apiService = RetrofitClient.getRetrofitInstance()
                .create(ApiService.class);
        Call<LoginResponse> call = apiService.login(loginRequest);

        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful() && response.body() != null){
                    LoginResponse loginResponse = response.body();
                    String token = loginResponse.getToken();
                    Usuario usuario = loginResponse.getUsuario();

                    Toast.makeText(LoginActivity.this, "Login feito com sucesso!", Toast.LENGTH_SHORT).show();

                    Intent intent;
                    if ("funcionario".equalsIgnoreCase(usuario.getCargo())){
                        intent = new Intent(LoginActivity.this, FuncionarioActivity.class);
                    } else {
                        intent = new Intent(LoginActivity.this, ComprasActivity.class);
                    }

                    startActivity(intent);
                    finish();

                }else {
                    Toast.makeText(LoginActivity.this, "Usuário ou senha inválidos!", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "Falha ao fazer login: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }

}