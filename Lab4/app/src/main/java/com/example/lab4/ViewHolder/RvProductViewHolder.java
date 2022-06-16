package com.example.lab4.ViewHolder;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lab4.R;

public class RvProductViewHolder extends RecyclerView.ViewHolder {

    public int position;
    public TextView tvName;
    public TextView tvPrice;
    public TextView tvQuantity;
    public ImageView imageView;
    public RvProductViewHolder(@NonNull View view) {
        super(view);
        this.tvName = view.findViewById(R.id.tvTen);
        this.tvPrice = view.findViewById(R.id.tvGia);
        this.tvQuantity = view.findViewById(R.id.tvSL);
        this.imageView = view.findViewById(R.id.imageProduct);

    }
}
