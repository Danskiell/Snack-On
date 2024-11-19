package com.example.snack.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.snack.CarrinhoActivity;
import com.example.snack.R;
import com.example.snack.model.Produto;

import java.util.List;

public class CarrinhoAdapter extends RecyclerView.Adapter<CarrinhoAdapter.CarrinhoViewHolder> {

    private final List<Produto> carrinhoList;
    private final Context context;

    public CarrinhoAdapter(List<Produto> carrinhoList, Context context) {
        this.carrinhoList = carrinhoList;
        this.context = context;
    }

    @NonNull
    @Override
    public CarrinhoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_carrinho, parent, false);
        return new CarrinhoViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull CarrinhoViewHolder holder, int position) {
        Produto produto = carrinhoList.get(position);

        holder.nomeProduto.setText(produto.getNome_Produto());
        holder.precoProduto.setText(String.format("R$ %.2f", produto.getPreco_Produto()));

        holder.btnRemoverProduto.setOnClickListener(v -> {
            carrinhoList.remove(position);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, carrinhoList.size());
            Log.d("Carrinho", "Produto removido: " + produto.getNome_Produto());

            if (context instanceof CarrinhoActivity) {
                ((CarrinhoActivity) context).atualizarTotal();
            }
        });


        Glide.with(context)
                .load(produto.getImageUrl())
                .placeholder(R.drawable.placeholder_image)
                .error(R.drawable.placeholder_image)
                .into(holder.imagemProduto);
    }

    @Override
    public int getItemCount() {
        return carrinhoList.size();
    }

    static class CarrinhoViewHolder extends RecyclerView.ViewHolder {
        TextView nomeProduto, precoProduto;
        ImageView imagemProduto, btnRemoverProduto;

        public CarrinhoViewHolder(@NonNull View itemView) {
            super(itemView);
            nomeProduto = itemView.findViewById(R.id.nomeProduto);
            precoProduto = itemView.findViewById(R.id.precoProduto);
            imagemProduto = itemView.findViewById(R.id.imagemProduto);
            btnRemoverProduto = itemView.findViewById(R.id.btnRemoverProduto);
        }
    }
}
