package com.example.fastfood.ViewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.chauthai.swipereveallayout.SwipeRevealLayout;
import com.example.fastfood.Adapter.ItemClickListener;
import com.example.fastfood.R;

public class CategoryViewHolder extends RecyclerView.ViewHolder{
    public static ItemClickListener itemEditClickstener;
    public static ItemClickListener itemDeleteClickstener;
    public int position;
    public TextView tvName;
    public ImageView imageView, ivDelete, ivUpdate;
    public LinearLayout layout_swipe;
    public SwipeRevealLayout swipeRevealLayout;
    public CategoryViewHolder(@NonNull View view) {
        super(view);
        this.tvName = view.findViewById(R.id.tvNameCategory);
        this.imageView = view.findViewById(R.id.imageCateAdapter);
        this.layout_swipe = view.findViewById(R.id.layout_swipe1);
        this.swipeRevealLayout = view.findViewById(R.id.swipeReveallayout1);
        this.ivDelete = view.findViewById(R.id.ivDeleteCate);
        this.ivUpdate = view.findViewById(R.id.ivUpdateCate);

        ivDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (itemDeleteClickstener != null){
                    itemDeleteClickstener.onItemClick(position);
                }
            }
        });

        ivUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (itemEditClickstener != null){
                    itemEditClickstener.onItemClick(position);
                }
            }
        });
    }
}
