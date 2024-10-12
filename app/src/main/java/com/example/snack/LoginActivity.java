package com.example.snack;
import com.example.snack.Auth0Manager;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.example.snack.R;

public class LoginActivity extends AppCompatActivity {
    private Auth0Manager auth0Manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        // Inicianmdo o Authmanager na activity de login
        auth0Manager = new Auth0Manager(this);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    public void register(View view){
        startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
    }
    public void logar(View view){
        auth0Manager.login(this);
    }
    public void esqueceu(View view){
        startActivity(new Intent(LoginActivity.this, SenhaActivity.class));

    }

}