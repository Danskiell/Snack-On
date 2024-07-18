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

import com.example.Gerenciar.Usuarios;

public class RegisterActivity extends AppCompatActivity {
    private Usuarios usuarios;
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
    usuarios = new Usuarios(this);

        EditText nomeEditText = findViewById(R.id.inputName);
        EditText sobrenomeEditText = findViewById(R.id.inputLastName);
        EditText emailEditText = findViewById(R.id.inputEmail);
        EditText telefoneEditText = findViewById(R.id.inputTelefone);
        EditText senhaEditText = findViewById(R.id.inputPass);
        EditText confirmarSenhaEditText = findViewById(R.id.inputConfPass);
        Button buttonCadastro = findViewById(R.id.btnCadastro);

        buttonCadastro.setOnClickListener(view -> {
            String nome = nomeEditText.getText().toString();
            String sobrenome = sobrenomeEditText.getText().toString();
            String email = emailEditText.getText().toString();
            String telefone = telefoneEditText.getText().toString();
            String senha = senhaEditText.getText().toString();
            String confirmarSenha = confirmarSenhaEditText.getText().toString();

            if(!senha.equals(confirmarSenha)){
                Toast.makeText(RegisterActivity.this, "As senhas precisam ser iguais", Toast.LENGTH_SHORT).show();
                return;
            }
            if (usuarios.adicionarUsuario(nome, sobrenome, email, telefone, senha)){
                Toast.makeText(RegisterActivity.this, "Usuário cadastrado", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            }else {
                Toast.makeText(RegisterActivity.this, "Falha ao cadastrar", Toast.LENGTH_SHORT).show();
            }
        });


    }


}