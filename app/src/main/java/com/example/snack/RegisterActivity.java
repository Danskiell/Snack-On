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
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class RegisterActivity extends AppCompatActivity {
    private EditText editTextNome, editTextSobrenome, editTextEmail,
            editTextTelefone, editTextSenha, editTextConfirmarSenha;

    private Button buttonRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        EditText nomeEditText = findViewById(R.id.inputName);
        EditText sobrenomeEditText = findViewById(R.id.inputLastName);
        EditText emailEditText = findViewById(R.id.inputEmail);
        EditText telefoneEditText = findViewById(R.id.inputTelefone);
        EditText senhaEditText = findViewById(R.id.inputPass);
        EditText confirmarSenhaEditText = findViewById(R.id.inputConfPass);
        Button buttonCadastro = findViewById(R.id.btnCadastro);

        telefoneEditText.addTextChangedListener(new MacaraTelefone(telefoneEditText));

        buttonCadastro.setOnClickListener(view -> {
            String nome = nomeEditText.getText().toString();
            String sobrenome = sobrenomeEditText.getText().toString();
            String email = emailEditText.getText().toString();
            String telefone = telefoneEditText.getText().toString();
            String senha = senhaEditText.getText().toString();
            String confirmarSenha = confirmarSenhaEditText.getText().toString();

            if(nome.isEmpty() || sobrenome.isEmpty() || email.isEmpty() || senha.isEmpty() || confirmarSenha.isEmpty()) {
                Toast.makeText(RegisterActivity.this , "Preencha todos os campos", Toast.LENGTH_SHORT).show();
                return;
            }

            else if(!senha.equals(confirmarSenha)){
                Toast.makeText(RegisterActivity.this, "As senhas precisam ser iguais", Toast.LENGTH_SHORT).show();
                return;
            }

            // Criando o objeto do usuario
            Usuario usuario = new Usuario(email, nome, sobrenome, null, senha, telefone);

            // Chamando a API para cadastrar o usuario
            ApiService apiService = RetrofitClient.getRetrofitInstance()
                    .create(ApiService.class);

            Call<Usuario> call = apiService.cadastrarUsuario(usuario);
            call.enqueue(new Callback<Usuario>() {
                @Override
                public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                    if(response.isSuccessful() && response.body() != null) {
                        Toast.makeText(RegisterActivity.this, "Cadastro realizado com sucesso!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(RegisterActivity.this, "Erro ao cadastrar, tente novamente!", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Usuario> call, Throwable t){
                Toast.makeText(RegisterActivity.this, "Erro ao conectar com o servidor", Toast.LENGTH_SHORT).show();}

        });
        });




    }

}