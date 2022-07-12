package com.example.bv_asm_networking.ViewHolder;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bv_asm_networking.Adapter.ItemClickListener;
import com.example.bv_asm_networking.R;

public class RvProductViewHolder extends RecyclerView.ViewHolder {
    public int position;
    public TextView tvName;
    public TextView tvPrice;
    public ImageView imageView, ivDelete, ivUpdate;
    public RvProductViewHolder(@NonNull View view) {
        super(view);
        this.tvName = view.findViewById(R.id.tvTen);
        this.tvPrice = view.findViewById(R.id.tvGia);
    }
}
