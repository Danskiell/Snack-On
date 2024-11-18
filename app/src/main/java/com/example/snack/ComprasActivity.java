package com.example.snack;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


public class ComprasActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_compras);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    public void config(View view){
        startActivity(new Intent(ComprasActivity.this, ProfileActivity.class));

    }
    public void carrinho(View view){
        startActivity(new Intent(ComprasActivity.this, CarrinhoActivity.class));
    }
    public void gohome(View view){
        startActivity(new Intent(ComprasActivity.this, ComprasActivity.class));

    }

    // Aplicando categoria para o carregamento dos produtos
    public void salgados(View view){
        Intent intent = new Intent(ComprasActivity.this, CategoriaActivity.class);
        intent.putExtra("categoria", "Salgados");
        startActivity(intent);
    }

    public void doces(View view){
        Intent intent = new Intent(ComprasActivity.this, CategoriaActivity.class);
        intent.putExtra("categoria", "Doces");
        startActivity(intent);
    }

    public void bebidas(View view){
        Intent intent = new Intent(ComprasActivity.this, CategoriaActivity.class);
        intent.putExtra("categoria", "Bebidas");
        startActivity(intent);
    }

    public void lanches(View view){
        Intent intent = new Intent(ComprasActivity.this, CategoriaActivity.class);
        intent.putExtra("categoria", "Lanches");
        startActivity(intent);
    }

    public void bolos(View view){
        Intent intent = new Intent(ComprasActivity.this, CategoriaActivity.class);
        intent.putExtra("categoria", "Bolos");
        startActivity(intent);
    }

    public void outros(View view){
        Intent intent = new Intent(ComprasActivity.this, CategoriaActivity.class);
        intent.putExtra("categoria", "Outros");
        startActivity(intent);
    }












}