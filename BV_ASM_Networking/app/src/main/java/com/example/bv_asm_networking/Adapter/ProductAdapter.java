package com.example.bv_asm_networking.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.bv_asm_networking.R;
import com.example.bv_asm_networking.ViewHolder.RvProductViewHolder;
import com.example.bv_asm_networking.model.Product;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<RvProductViewHolder> {
    public int position;
    private List<Product> data;
    private Context context;

    public ProductAdapter( Context context,List<Product> data) {
        this.data = data;
        this.context = context;
    }

    @NonNull
    @Override
    public RvProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
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
    public void onBindViewHolder(@NonNull RvProductViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Product product = data.get(position);
        holder.tvName.setText(product.getName()+"");
        holder.tvPrice.setText(product.getPrice()+" VND");
        holder.tvQuantity.setText(product.getQuantity()+" sp");
//        Glide.with(context).load(product.getImage()).into(holder.imageView);
        holder.position = position;
    }

    public Product getItem(int position){
        if (data == null || position >= data.size()){
            return null;
        }
        return data.get(position);
    }

//    public void setOnItemEditClickstener(ItemClickListener itemEditClickstener) {
//        RvProductViewHolder.itemEditClickstener = itemEditClickstener;
//    }
//
//    public void setOnItemDeleteClickstener(ItemClickListener itemDeleteClickstener) {
//        RvProductViewHolder.itemDeleteClickstener = itemDeleteClickstener;
//    }

    @Override
    public int getItemCount() {
        if (data == null)
            return 0;
        return data.size();
    }
}
