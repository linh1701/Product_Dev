package com.example.fastfood.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.chauthai.swipereveallayout.ViewBinderHelper;
import com.example.fastfood.R;
import com.example.fastfood.ViewHolder.CategoryViewHolder;
import com.example.fastfood.ViewHolder.RvProductViewHolder;
import com.example.fastfood.model.Category;
import com.example.fastfood.model.Product;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryViewHolder>{
    public int position;
    private List<Category> data;
    private Context context;
    private ViewBinderHelper viewBinderHelper = new ViewBinderHelper();

    public CategoryAdapter( Context context,List<Category> data) {
        this.data = data;
        this.context = context;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(context).inflate(R.layout.layout_category_item,parent,
                false);
        return new CategoryViewHolder(view);
    }
    public void updateData(List<Category> list){
        data.clear();
        data.addAll(list);
        this.notifyDataSetChanged();
    }
    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Category category = data.get(position);
        holder.tvName.setText(category.getName()+"");
        Glide.with(context).load(category.getImage()).into(holder.imageView);
        viewBinderHelper.bind(holder.swipeRevealLayout, String.valueOf(category.getId()));
        holder.position = position;
    }

    public Category getItem(int position){
        if (data == null || position >= data.size()){
            return null;
        }
        return data.get(position);
    }

    public void setOnItemEditClickstener(ItemClickListener itemEditClickstener) {
        CategoryViewHolder.itemEditClickstener = itemEditClickstener;
    }

    public void setOnItemDeleteClickstener(ItemClickListener itemDeleteClickstener) {
        CategoryViewHolder.itemDeleteClickstener = itemDeleteClickstener;
    }

    @Override
    public int getItemCount() {
        if (data == null)
            return 0;
        return data.size();
    }
}
