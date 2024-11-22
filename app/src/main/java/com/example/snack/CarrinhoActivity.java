package com.example.snack;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.graphics.Insets;

import com.example.snack.adapters.CarrinhoAdapter;
import com.example.snack.manager.CarrinhoManager;
import com.example.snack.model.Pedido;
import com.example.snack.model.Produto;
import com.example.snack.ApiService;
import com.example.snack.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CarrinhoActivity extends AppCompatActivity {

    private RecyclerView recyclerCarrinho;
    private TextView totalTextView;
    private CarrinhoAdapter carrinhoAdapter;
    private ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrinho);

        // Inicializar Retrofit
        apiService = RetrofitClient.getRetrofitInstance().create(ApiService.class);

        // Configuração de insetos para suportar barras do sistema
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        recyclerCarrinho = findViewById(R.id.recyclerCarrinho);
        totalTextView = findViewById(R.id.r_12_00);

        // Obter os produtos do carrinho
        List<Produto> produtosCarrinho = CarrinhoManager.getInstance().listarCarrinho();

        // Configurar o LayoutManager e o adaptador para o RecyclerView
        recyclerCarrinho.setLayoutManager(new LinearLayoutManager(this));
        carrinhoAdapter = new CarrinhoAdapter(produtosCarrinho, this);
        recyclerCarrinho.setAdapter(carrinhoAdapter);

        Button btnFinalizar = findViewById(R.id.btnFinalizarCarrinho);
        btnFinalizar.setOnClickListener(v -> {
            // Verificar se o carrinho está vazio
            if (produtosCarrinho.isEmpty()) {
                Toast.makeText(this, "Carrinho vazio!", Toast.LENGTH_SHORT).show();
                return;
            }

            // Calcular total do pedido
            double total = CarrinhoManager.getInstance().calcularTotal();

            // Criar pedido
            Pedido novoPedido = new Pedido(0, new ArrayList<>(produtosCarrinho), total, null);

            // Enviar pedido para o backend
            apiService.criarPedido(novoPedido).enqueue(new Callback<Pedido>() {
                @Override
                public void onResponse(Call<Pedido> call, Response<Pedido> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        // Pedido criado com sucesso
                        Toast.makeText(CarrinhoActivity.this, "Pedido finalizado!", Toast.LENGTH_SHORT).show();

                        // Limpar o carrinho
                        CarrinhoManager.getInstance().limparCarrinho();
                        carrinhoAdapter.notifyDataSetChanged();

                        // Navegar para a tela de Detalhes do Pedido
                        Intent intent = new Intent(CarrinhoActivity.this, DetalhesPedidoActivity.class);
                        intent.putExtra("pedidoId", response.body().getIdPedido());
                        startActivity(intent);
                    } else {
                        Toast.makeText(CarrinhoActivity.this, "Erro ao finalizar pedido!", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Pedido> call, Throwable t) {
                    Toast.makeText(CarrinhoActivity.this, "Erro na comunicação: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        });

        // Atualizar o total
        atualizarTotal();
    }

    public void atualizarTotal() {
        double total = CarrinhoManager.getInstance().calcularTotal();
        totalTextView.setText(String.format("Total: R$ %.2f", total));
    }

    // Função para navegar para a tela de compras
    public void gohome(View view) {
        startActivity(new Intent(CarrinhoActivity.this, ComprasActivity.class));
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
}
