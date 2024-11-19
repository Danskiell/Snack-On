package com.example.snack;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.graphics.Insets;
import com.example.snack.adapters.CarrinhoAdapter;
import com.example.snack.manager.CarrinhoManager;
import com.example.snack.model.Produto;

import java.util.List;

public class CarrinhoActivity extends AppCompatActivity {

    private RecyclerView recyclerCarrinho;
    private TextView totalTextView;
    private CarrinhoAdapter carrinhoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrinho);

        // Configuração de insetos para suportar barras do sistema
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        recyclerCarrinho = findViewById(R.id.recyclerCarrinho);  // Remover a duplicação da variável
        totalTextView = findViewById(R.id.r_12_00);

        // Obter os produtos do carrinho
        List<Produto> produtosCarrinho = CarrinhoManager.getInstance().listarCarrinho();

        // Configurar o LayoutManager e o adaptador para o RecyclerView
        recyclerCarrinho.setLayoutManager(new LinearLayoutManager(this));
        carrinhoAdapter = new CarrinhoAdapter(produtosCarrinho, this);
        recyclerCarrinho.setAdapter(carrinhoAdapter);



        // Inicialização dos componentes
        recyclerCarrinho = findViewById(R.id.recyclerCarrinho);
        totalTextView = findViewById(R.id.r_12_00);

        // Obter os produtos do carrinho

        // Configurar o adaptador para o RecyclerView
        carrinhoAdapter = new CarrinhoAdapter(produtosCarrinho, this);
        recyclerCarrinho.setAdapter(carrinhoAdapter);

        // Atualizar o total
        double total = CarrinhoManager.getInstance().calcularTotal();
        totalTextView.setText(String.format("Total: R$ %.2f", total));
    }

    // Função para navegar para a tela de compras
    public void goHome(View view) {
        Intent intent = new Intent(CarrinhoActivity.this, ComprasActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); // Evita empilhar atividades
        startActivity(intent);
    }

    // Função para navegar para a tela de perfil
    public void config(View view) {
        Intent intent = new Intent(CarrinhoActivity.this, ProfileActivity.class);
        startActivity(intent);
    }

    // Função para navegar de volta à tela do carrinho
    public void carrinho(View view) {
        Intent intent = new Intent(CarrinhoActivity.this, CarrinhoActivity.class);
        startActivity(intent);
    }

    public void atualizarTotal() {
        double total = CarrinhoManager.getInstance().calcularTotal();
        totalTextView.setText(String.format("Total: R$ %.2f", total));
    }

}
