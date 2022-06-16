package com.example.lab4.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.lab4.R;
import com.example.lab4.ViewHolder.RvProductViewHolder;
import com.example.lab4.model.Product;

import java.util.List;

public class RvProductAdapter extends RecyclerView.Adapter<RvProductViewHolder> {
    public int position;
    private List<Product> data;
    private Context context;

    public RvProductAdapter( Context context,List<Product> data) {
        this.data = data;
        this.context = context;
    }

    @NonNull
    @Override
    public RvProductViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(context).inflate(R.layout.layout_product_item,parent,
                false);
        return new RvProductViewHolder(view);
    }
    public void updateData(List<Product> list){
        data.clear();
        data.addAll(list);
        this.notifyDataSetChanged();
    }
    @Override
    public void onBindViewHolder(@NonNull RvProductViewHolder holder, int position) {
        Product product = data.get(position);
        holder.tvName.setText(product.getName()+"");
        holder.tvPrice.setText(product.getPrice()+"");
        holder.tvQuantity.setText(product.getQuantity()+"");
        Glide.with(context).load(product.getImage()).into(holder.imageView);
        holder.position = position;
    }

    @Override
    public int getItemCount() {
        if (data == null)
            return 0;
        return data.size();
    }
}
