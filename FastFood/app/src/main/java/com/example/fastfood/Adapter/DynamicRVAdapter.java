package com.example.fastfood.Adapter;

import android.app.Activity;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fastfood.DRVinterface.LoadMore;
import com.example.fastfood.R;
import com.example.fastfood.model.DynamicrvModel;
import com.example.fastfood.model.StaticRvModel;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;



public class DynamicRVAdapter extends RecyclerView.Adapter<DynamicRVAdapter.DynamicRvHolder>{
    public ArrayList<DynamicrvModel> dynamicrvModels;

    public DynamicRVAdapter(ArrayList<DynamicrvModel> dynamicrvModels){
        this.dynamicrvModels = dynamicrvModels;
    }

    public class DynamicRvHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView textView;
        ConstraintLayout constraintLayout;

        public DynamicRvHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageFood);
            textView = itemView.findViewById(R.id.name);
            constraintLayout = itemView.findViewById(R.id.constraintLayout2);
        }
    }
    @NonNull
    @NotNull
    @Override
    public DynamicRVAdapter.DynamicRvHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dymanic_rv_item,parent,false);
        DynamicRvHolder dynamicRvHolder = new DynamicRvHolder(view);
        return dynamicRvHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull DynamicRVAdapter.DynamicRvHolder holder, int position) {
        DynamicrvModel currentItem = dynamicrvModels.get(position);
        holder.imageView.setImageResource(currentItem.getImage());
        holder.textView.setText(currentItem.getName());
    }

    @Override
    public int getItemCount() {
        return dynamicrvModels.size();
    }


}

