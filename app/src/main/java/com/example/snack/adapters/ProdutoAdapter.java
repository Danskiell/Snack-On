package com.example.snack.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.snack.R;
import com.example.snack.model.Produto;
import java.util.List;

public class ProdutoAdapter extends RecyclerView.Adapter<ProdutoAdapter.ProdutoViewHolder> {

    private final List<Produto> produtoList;
    private final Context context;

    public ProdutoAdapter(List<Produto> produtoList, Context context) {
        this.produtoList = produtoList;
        this.context = context;
    }

    @NonNull
    @Override
    public ProdutoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_produto, parent, false);
        return new ProdutoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProdutoViewHolder holder, int position) {
        Produto produto = produtoList.get(position);

        holder.nomeProduto.setText(produto.getNome_Produto());
        holder.descricaoProduto.setText(produto.getDescricao());
        holder.precoProduto.setText(String.format("R$ %.2f", produto.getPreco_Produto()));

        String imageUrl = produto.getImageUrl();
        Log.d("ProdutoAdapter", "Carregando imagem: " + imageUrl);

        if (imageUrl != null && !imageUrl.trim().isEmpty()) {
            Glide.with(context)
                    .load(imageUrl)
                    .placeholder(R.drawable.placeholder_image) // Imagem padrão
                    .error(R.drawable.placeholder_image) // Em caso de erro no carregamento
                    .into(holder.imagemProduto);
        } else {
            Log.e("ProdutoAdapter", "URL de imagem inválida para o produto: " + produto.getNome_Produto());
            Glide.with(context)
                    .load(R.drawable.placeholder_image)
                    .into(holder.imagemProduto);
        }
    }




    @Override
    public int getItemCount() {
        return produtoList.size();
    }

    static class ProdutoViewHolder extends RecyclerView.ViewHolder {
        TextView nomeProduto, descricaoProduto, precoProduto;
        ImageView imagemProduto;

        public ProdutoViewHolder(@NonNull View itemView) {
            super(itemView);
            nomeProduto = itemView.findViewById(R.id.nomeProduto);
            descricaoProduto = itemView.findViewById(R.id.descricaoProduto);
            precoProduto = itemView.findViewById(R.id.precoProduto);
            imagemProduto = itemView.findViewById(R.id.imagemProduto);
        }
    }
}
