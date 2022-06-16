package com.example.fastfood;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Handler;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.fastfood.Adapter.StaticRvAdapter;
import com.example.fastfood.DRVinterface.LoadMore;
import com.example.fastfood.model.DynamicrvModel;
import com.example.fastfood.model.StaticRvModel;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class DashBoardActivity extends AppCompatActivity {
private RecyclerView recyclerView;
private StaticRvAdapter staticRvAdapter;
List<DynamicrvModel> items = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);

//        ArrayList<StaticRvModel> item = new ArrayList<>();
//        item.add(new StaticRvModel(R.drawable.pizza_1,"pizza"));
//        item.add(new StaticRvModel(R.drawable.fast_food,"bánh mì"));
//        item.add(new StaticRvModel(R.drawable.soft_drink,"nước ngọt"));
//        item.add(new StaticRvModel(R.drawable.french_fries,"Khoai chiên"));
//        item.add(new StaticRvModel(R.drawable.hamburger,"hamburger"));
//
//        recyclerView = findViewById(R.id.rv_01);
//        staticRvAdapter = new StaticRvAdapter(item);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
//        recyclerView.setAdapter(staticRvAdapter);
//
//        items.add(new DynamicrvModel("Hamburger"));
//        items.add(new DynamicrvModel("Hamburger"));
//        items.add(new DynamicrvModel("Hamburger"));
//        items.add(new DynamicrvModel("Hamburger"));
//        items.add(new DynamicrvModel("Hamburger"));
//        items.add(new DynamicrvModel("Hamburger"));
//        items.add(new DynamicrvModel("Hamburger"));
//        items.add(new DynamicrvModel("Hamburger"));
//        items.add(new DynamicrvModel("Hamburger"));
//        items.add(new DynamicrvModel("Hamburger"));
//        items.add(new DynamicrvModel("Hamburger"));
//        items.add(new DynamicrvModel("Hamburger"));
//        items.add(new DynamicrvModel("Hamburger"));
//        items.add(new DynamicrvModel("Hamburger"));
//        items.add(new DynamicrvModel("Hamburger"));
//
//        RecyclerView drv = findViewById(R.id.rv_02);
//        drv.setLayoutManager(new LinearLayoutManager(this));
//        dybnamicRVAdapter = new DybnamicRVAdapter(drv,this, items);
//        drv.setAdapter(dybnamicRVAdapter);
//
//        dybnamicRVAdapter.setLoadMore(new LoadMore() {
//            @Override
//            public void onLoadMore() {
//                if (items.size()<= 10){
//                    item.add(null);
//                    dybnamicRVAdapter.notifyItemInserted(items.size()-1);
//                    new Handler().postDelayed(new Runnable() {
//                        @Override
//                        public void run() {
//                            items.remove(items.size()-1);
//                            dybnamicRVAdapter.notifyItemRemoved(items.size());
//                            int index = items.size();
//                            int end = index+10;
//                            for (int i = index; i<end; i++){
//                                String name = UUID.randomUUID().toString();
//                                DynamicrvModel item = new DynamicrvModel(name);
//                                items.add(item);
//                            }
//                            dybnamicRVAdapter.notifyDataSetChanged();
//                            dybnamicRVAdapter.setLoded();
//                        }
//                    }, 4000);
//                }
//                else
//                    Toast.makeText(DashBoardActivity.this,"Data completed", Toast.LENGTH_SHORT).show();
//            }
//        });
    }
}