package com.example.snack;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.snack.manager.PedidoManager;
import com.example.snack.model.Pedido;
import com.example.snack.adapters.ProdutoAdapter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetalhesPedidoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_pedido);

        TextView tvPedidoId = findViewById(R.id.tvPedidoId);
        TextView tvPedidoTotal = findViewById(R.id.tvPedidoTotal);
        TextView tvPedidoData = findViewById(R.id.tvPedidoData);
        RecyclerView rvProdutos = findViewById(R.id.rvProdutos);

        // Obtém o ID do pedido enviado pela Intent
        long idPedido = getIntent().getLongExtra("pedidoId", -1);

        if (idPedido == -1) {
            Toast.makeText(this, "Erro ao carregar informações do pedido", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        // Busca o pedido pelo ID
        PedidoManager.getInstance().getPedidoById(idPedido, new Callback<Pedido>() {
            @Override
            public void onResponse(Call<Pedido> call, Response<Pedido> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Pedido pedido = response.body();

                    // Exibe as informações do pedido
                    tvPedidoId.setText(String.format("Pedido #%d", pedido.getIdPedido()));
                    tvPedidoTotal.setText(String.format("Total: R$ %.2f", pedido.getTotal()));
                    tvPedidoData.setText(String.format("Data: %s", pedido.getDataPedido()));

                    // Configura RecyclerView para exibir os produtos do pedido
                    rvProdutos.setLayoutManager(new LinearLayoutManager(DetalhesPedidoActivity.this));
                    ProdutoAdapter adapter = new ProdutoAdapter(pedido.getProdutos(), DetalhesPedidoActivity.this);
                    rvProdutos.setAdapter(adapter);
                } else {
                    Toast.makeText(DetalhesPedidoActivity.this, "Erro ao carregar detalhes do pedido", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Pedido> call, Throwable t) {
                Toast.makeText(DetalhesPedidoActivity.this, "Erro de rede: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
