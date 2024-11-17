package com.example.snack;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.snack.adapters.ProdutoAdapter;
import com.example.snack.model.Produto;
import com.example.snack.RetrofitClient;
import com.example.snack.ApiService;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SalgadosActivity extends AppCompatActivity {

    private RecyclerView recyclerViewProdutos;
    private ProgressBar progressBar;
    private TextView textViewEmpty;
    private ProdutoAdapter produtoAdapter;
    private List<Produto> produtoList = new ArrayList<>();
    private ApiService produtoApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_salgados);

        // Vinculando os elementos da interface
        recyclerViewProdutos = findViewById(R.id.recyclerViewProdutos);
        progressBar = findViewById(R.id.progressBar);
        textViewEmpty = findViewById(R.id.textViewEmpty);

        // Configurando o RecyclerView
        recyclerViewProdutos.setLayoutManager(new LinearLayoutManager(this));
        produtoAdapter = new ProdutoAdapter(produtoList, this);
        recyclerViewProdutos.setAdapter(produtoAdapter);

        // Instanciando a API
        produtoApi = RetrofitClient.getRetrofitInstance().create(ApiService.class);

        // Carregar produtos automaticamente
        carregarProdutosPorCategoria("Salgados");
    }

    private void carregarProdutosPorCategoria(String categoria) {
        progressBar.setVisibility(View.VISIBLE);
        textViewEmpty.setVisibility(View.GONE);

        produtoApi.getProdutosPorCategoria(categoria).enqueue(new Callback<List<Produto>>() {
            @Override
            public void onResponse(@NonNull Call<List<Produto>> call, @NonNull Response<List<Produto>> response) {
                progressBar.setVisibility(View.GONE);

                if (response.isSuccessful() && response.body() != null) {
                    produtoList.clear();
                    produtoList.addAll(response.body());
                    produtoAdapter.notifyDataSetChanged();

                    if (produtoList.isEmpty()) {
                        textViewEmpty.setVisibility(View.VISIBLE);
                    }
                } else {
                    Toast.makeText(SalgadosActivity.this, "Erro ao carregar produtos.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Produto>> call, @NonNull Throwable t) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(SalgadosActivity.this, "Erro de conexão.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
