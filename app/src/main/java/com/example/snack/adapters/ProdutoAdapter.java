package com.example.snack.adapters;

import com.example.snack.manager.CarrinhoManager;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
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
        return new ProdutoViewHolder(view); // Construtor corrigido
    }

    @Override
    public void onBindViewHolder(@NonNull ProdutoViewHolder holder, int position) {
        Produto produto = produtoList.get(position);

        holder.nomeProduto.setText(produto.getNome_Produto());
        holder.descricaoProduto.setText(produto.getDescricao());
        holder.precoProduto.setText(String.format("R$ %.2f", produto.getPreco_Produto()));
        final Animation anim = AnimationUtils.loadAnimation(context, R.anim.add_to_cart);

        // Carregar imagem com Glide
        Glide.with(context)
                .load(produto.getImageUrl())
                .placeholder(R.drawable.placeholder_image)
                .error(R.drawable.placeholder_image)
                .into(holder.imagemProduto);

        // Configurar o clique no botão de adicionar ao carrinho
        holder.btnAddCarrinho.setOnClickListener(v -> {
            CarrinhoManager.getInstance().adicionarAoCarrinho(produto);
            Log.d("Carrinho", "Produto adicionado: " + produto.getNome_Produto());
            holder.btnAddCarrinho.startAnimation(anim);
            holder.iconeButton.startAnimation(anim);
        });
    }

    @Override
    public int getItemCount() {
        return produtoList.size();
    }

    // ViewHolder correto
    static class ProdutoViewHolder extends RecyclerView.ViewHolder {
        TextView nomeProduto, descricaoProduto, precoProduto;
        ImageView imagemProduto, iconeButton;
        Button btnAddCarrinho;

        public ProdutoViewHolder(@NonNull View itemView) {
            super(itemView);
            nomeProduto = itemView.findViewById(R.id.nomeProduto);
            descricaoProduto = itemView.findViewById(R.id.descricaoProduto);
            precoProduto = itemView.findViewById(R.id.precoProduto);
            imagemProduto = itemView.findViewById(R.id.imagemProduto);
            btnAddCarrinho = itemView.findViewById(R.id.btnAdicionarCarrinho);
            iconeButton = itemView.findViewById(R.id.iconeButton);
        }
    }
}
