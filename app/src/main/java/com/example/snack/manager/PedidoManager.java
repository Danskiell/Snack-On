package com.example.snack.manager;

import com.example.snack.model.Pedido;
import com.example.snack.ApiService;
import com.example.snack.RetrofitClient;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PedidoManager {

    private static PedidoManager instance;
    private final ApiService pedidoService;

    private PedidoManager() {
        pedidoService = RetrofitClient.getRetrofitInstance().create(ApiService.class);
    }

    // Método para obter a instância do PedidoManager de forma segura e singleton
    public static synchronized PedidoManager getInstance() {
        if (instance == null) {
            instance = new PedidoManager();
        }
        return instance;
    }

    // Método para adicionar um pedido
    public void adicionarPedido(Pedido pedido, Callback<Pedido> callback) {
        pedidoService.criarPedido(pedido).enqueue(callback);
    }

    // Método para listar pedidos
    public void listarPedidos(Callback<List<Pedido>> callback) {
        pedidoService.listarPedidos().enqueue(callback);
    }

    // Método para buscar um pedido pelo ID
    public void getPedidoById(long idPedido, Callback<Pedido> callback) {
        // Chama a API para obter um pedido pelo ID
        pedidoService.buscarPedido(idPedido).enqueue(callback);
    }
}
