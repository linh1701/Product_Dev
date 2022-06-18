package com.example.bv_asm_networking.ViewHolder;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bv_asm_networking.R;
import com.example.fastfood.Adapter.ItemClickListener;

public class RvProductViewHolder extends RecyclerView.ViewHolder {
    public static ItemClickListener itemEditClickstener;
    public static ItemClickListener itemDeleteClickstener;
    public int position;
    public TextView tvName;
    public TextView tvPrice;
    public TextView tvQuantity;
    public ImageView imageView, ivDelete, ivUpdate;
    public RvProductViewHolder(@NonNull View view) {
        super(view);
        this.tvName = view.findViewById(R.id.tvTen);
        this.tvPrice = view.findViewById(R.id.tvGia);
        this.tvQuantity = view.findViewById(R.id.tvSL);
//        this.imageView = view.findViewById(R.id.imageProduct);
//        this.layout_swipe = view.findViewById(R.id.layout_swipe);
//        this.swipeRevealLayout = view.findViewById(R.id.swipeReveallayout);
//        this.ivDelete = view.findViewById(R.id.ivDelete);
//        this.ivUpdate = view.findViewById(R.id.ivUpdate);

//        ivDelete.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (itemDeleteClickstener != null){
//                    itemDeleteClickstener.onItemClick(position);
//                }
//            }
//        });
//
//        ivUpdate.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (itemEditClickstener != null){
//                    itemEditClickstener.onItemClick(position);
//                }
//            }
//        });
    }
}
