package com.example.fastfood.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fastfood.DRVinterface.UpdateRecyclerView;
import com.example.fastfood.R;
import com.example.fastfood.model.DynamicrvModel;
import com.example.fastfood.model.StaticRvModel;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class StaticRvAdapter extends RecyclerView.Adapter<StaticRvAdapter.StaticRvViewHolder>{
    private ArrayList<StaticRvModel> items;
    int row_index = -1;
    Activity activity;
    UpdateRecyclerView updateRecyclerView;
    boolean check = true;
    boolean select= true;

    public StaticRvAdapter(ArrayList<StaticRvModel> items, Activity activity, UpdateRecyclerView updateRecyclerView){
        this.items = items;
        this.activity = activity;
        this.updateRecyclerView =updateRecyclerView;
    }

    @Override
    public StaticRvViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.static_rv_item,parent, false);
        StaticRvViewHolder staticRvViewHolder = new StaticRvViewHolder(view);
        return  staticRvViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull StaticRvViewHolder holder, int position) {
        StaticRvModel currentItem = items.get(position);
        holder.imageView.setImageResource(currentItem.getImage());
        holder.textView.setText(currentItem.getText());

        if (check){
            ArrayList<DynamicrvModel> items = new ArrayList<DynamicrvModel>();
            items.add(new DynamicrvModel("pizza", R.drawable.pizza));
            items.add(new DynamicrvModel("pizza2", R.drawable.pizza));
            items.add(new DynamicrvModel("pizza3", R.drawable.pizza));
            items.add(new DynamicrvModel("pizza4", R.drawable.pizza));
            items.add(new DynamicrvModel("pizza5", R.drawable.pizza));
            items.add(new DynamicrvModel("pizza6", R.drawable.pizza));
            items.add(new DynamicrvModel("pizza7", R.drawable.pizza));
            items.add(new DynamicrvModel("pizza8", R.drawable.pizza));
            items.add(new DynamicrvModel("pizza9", R.drawable.pizza));

            updateRecyclerView.callback(position,items);
            check = false;
        }

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                row_index = position;
                notifyDataSetChanged();

                if (position == 0){
                    ArrayList<DynamicrvModel> items = new ArrayList<DynamicrvModel>();
                    items.add(new DynamicrvModel("pizza", R.drawable.pizza));
                    items.add(new DynamicrvModel("pizza2", R.drawable.pizza));
                    items.add(new DynamicrvModel("pizza3", R.drawable.pizza));
                    items.add(new DynamicrvModel("pizza4", R.drawable.pizza));
                    items.add(new DynamicrvModel("pizza5", R.drawable.pizza));
                    items.add(new DynamicrvModel("pizza6", R.drawable.pizza));
                    items.add(new DynamicrvModel("pizza7", R.drawable.pizza));
                    items.add(new DynamicrvModel("pizza8", R.drawable.pizza));
                    items.add(new DynamicrvModel("pizza9", R.drawable.pizza));

                    updateRecyclerView.callback(position,items);
                }

                else if (position == 1){
                    ArrayList<DynamicrvModel> items = new ArrayList<DynamicrvModel>();
                    items.add(new DynamicrvModel("Bánh mì", R.drawable.fast_food));
                    items.add(new DynamicrvModel("Bánh mì", R.drawable.fast_food));
                    items.add(new DynamicrvModel("Bánh mì", R.drawable.fast_food));
                    items.add(new DynamicrvModel("Bánh mì", R.drawable.fast_food));
                    items.add(new DynamicrvModel("Bánh mì", R.drawable.fast_food));
                    items.add(new DynamicrvModel("Bánh mì", R.drawable.fast_food));
                    items.add(new DynamicrvModel("Bánh mì", R.drawable.fast_food));
                    items.add(new DynamicrvModel("Bánh mì", R.drawable.fast_food));
                    updateRecyclerView.callback(position,items);
                }

                else if (position == 2){
                    ArrayList<DynamicrvModel> items = new ArrayList<DynamicrvModel>();
                    items.add(new DynamicrvModel("Nước ngọt", R.drawable.soft_drink));
                    items.add(new DynamicrvModel("Nước ngọt", R.drawable.soft_drink));
                    items.add(new DynamicrvModel("Nước ngọt", R.drawable.soft_drink));
                    items.add(new DynamicrvModel("Nước ngọt", R.drawable.soft_drink));
                    items.add(new DynamicrvModel("Nước ngọt", R.drawable.soft_drink));
                    items.add(new DynamicrvModel("Nước ngọt", R.drawable.soft_drink));
                    items.add(new DynamicrvModel("Nước ngọt", R.drawable.soft_drink));
                    items.add(new DynamicrvModel("Nước ngọt", R.drawable.soft_drink));

                    updateRecyclerView.callback(position,items);
                }

                else if (position == 3){
                    ArrayList<DynamicrvModel> items = new ArrayList<DynamicrvModel>();
                    items.add(new DynamicrvModel("Khoai chiên", R.drawable.french_fries));
                    items.add(new DynamicrvModel("Khoai chiên", R.drawable.french_fries));
                    items.add(new DynamicrvModel("Khoai chiên", R.drawable.french_fries));
                    items.add(new DynamicrvModel("Khoai chiên", R.drawable.french_fries));
                    items.add(new DynamicrvModel("Khoai chiên", R.drawable.french_fries));
                    items.add(new DynamicrvModel("Khoai chiên", R.drawable.french_fries));
                    items.add(new DynamicrvModel("Khoai chiên", R.drawable.french_fries));

                    updateRecyclerView.callback(position,items);
                }

                else if (position == 4){
                    ArrayList<DynamicrvModel> items = new ArrayList<DynamicrvModel>();
                    items.add(new DynamicrvModel("Hamburger", R.drawable.hamburger));
                    items.add(new DynamicrvModel("Hamburger", R.drawable.hamburger));
                    items.add(new DynamicrvModel("Hamburger", R.drawable.hamburger));
                    items.add(new DynamicrvModel("Hamburger", R.drawable.hamburger));
                    items.add(new DynamicrvModel("Hamburger", R.drawable.hamburger));
                    items.add(new DynamicrvModel("Hamburger", R.drawable.hamburger));
                    items.add(new DynamicrvModel("Hamburger", R.drawable.hamburger));

                    updateRecyclerView.callback(position,items);
                }
            }
        });

        if (select){
            if (position==0){
                holder.linearLayout.setBackgroundResource(R.drawable.static_rv_selected_bg);
                select = true;
            }else {
                if (row_index == position){
                    holder.linearLayout.setBackgroundResource(R.drawable.static_rv_selected_bg);
                }else {
                    holder.linearLayout.setBackgroundResource(R.drawable.static_rv_bg);
                }
            }
        }


    }

    @Override
    public int getItemCount() {
        return items.size();
    }


    public static class StaticRvViewHolder extends RecyclerView.ViewHolder{
      TextView textView;
      ImageView imageView;
      LinearLayout linearLayout;

     public StaticRvViewHolder(@NonNull @NotNull View itemView) {
         super(itemView);;
         imageView = itemView.findViewById(R.id.image);
         textView = itemView.findViewById(R.id.tvText);
         linearLayout = itemView.findViewById(R.id.linearlayout);
     }
 }
}
