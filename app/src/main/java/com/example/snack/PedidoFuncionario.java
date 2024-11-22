package com.example.snack;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.snack.adapters.PedidoFuncionarioAdapter;
import com.example.snack.manager.PedidoManager;
import com.example.snack.model.Pedido;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PedidoFuncionario extends AppCompatActivity {

    private RecyclerView recyclerViewPedidos;
    private PedidoFuncionarioAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedido_funcionario);

        recyclerViewPedidos = findViewById(R.id.rvPedidosFuncionario);
        recyclerViewPedidos.setLayoutManager(new LinearLayoutManager(this));

        adapter = new PedidoFuncionarioAdapter(new ArrayList<>(), this);
        recyclerViewPedidos.setAdapter(adapter);

        adapter.setOnItemClickListener(pedido -> {
            Intent intent = new Intent(PedidoFuncionario.this, DetalhesPedidoActivity.class);
            intent.putExtra("pedidoId", pedido.getIdPedido());
            startActivity(intent);
        });

        carregarPedidos();
    }

    private void carregarPedidos() {
        PedidoManager.getInstance().listarPedidos(new Callback<List<Pedido>>() {
            @Override
            public void onResponse(Call<List<Pedido>> call, Response<List<Pedido>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    adapter.atualizarPedidos(response.body());
                } else {
                    Toast.makeText(PedidoFuncionario.this, "Erro ao carregar pedidos", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Pedido>> call, Throwable t) {
                Toast.makeText(PedidoFuncionario.this, "Erro de rede: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
