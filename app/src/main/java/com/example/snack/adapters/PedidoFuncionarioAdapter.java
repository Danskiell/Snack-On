package com.example.snack.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.snack.R;
import com.example.snack.model.Pedido;

import java.util.List;

public class PedidoFuncionarioAdapter extends RecyclerView.Adapter<PedidoFuncionarioAdapter.PedidoViewHolder> {

    private List<Pedido> pedidos;
    private final Context context;
    private OnItemClickListener onItemClickListener;

    public PedidoFuncionarioAdapter(List<Pedido> pedidos, Context context) {
        this.pedidos = pedidos;
        this.context = context;
    }

    @NonNull
    @Override
    public PedidoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_pedido_funcionario, parent, false);
        return new PedidoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PedidoViewHolder holder, int position) {
        Pedido pedido = pedidos.get(position);

        holder.tvPedidoId.setText(String.format("Pedido #%d", pedido.getIdPedido()));
        holder.tvPedidoData.setText(String.format("Data: %s", pedido.getDataPedido()));
        holder.tvPedidoTotal.setText(String.format("Total: R$ %.2f", pedido.getTotal()));

        holder.itemView.setOnClickListener(v -> {
            if (onItemClickListener != null) {
                onItemClickListener.onItemClick(pedido);
            }
        });
    }

    @Override
    public int getItemCount() {
        return pedidos.size();
    }

    public void atualizarPedidos(List<Pedido> novosPedidos) {
        this.pedidos = novosPedidos;
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    public interface OnItemClickListener {
        void onItemClick(Pedido pedido);
    }

    public static class PedidoViewHolder extends RecyclerView.ViewHolder {
        TextView tvPedidoId, tvPedidoData, tvPedidoTotal;

        public PedidoViewHolder(@NonNull View itemView) {
            super(itemView);
            tvPedidoId = itemView.findViewById(R.id.tvPedidoId);
            tvPedidoData = itemView.findViewById(R.id.tvPedidoData);
            tvPedidoTotal = itemView.findViewById(R.id.tvPedidoTotal);
        }
    }
}
