package com.example.bv_asm_networking;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.bv_asm_networking.Adapter.ProductAdapter;
import com.example.bv_asm_networking.Retrofits.IRetrofitService;
import com.example.bv_asm_networking.Retrofits.RetrofitBuider;
import com.example.bv_asm_networking.model.Product;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    IRetrofitService service;
    RecyclerView recyclerView;
    ProductAdapter adapter;
    FloatingActionButton fabInsert;
    List<Product> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fabInsert = findViewById(R.id.fabInsertProduct);
        recyclerView = findViewById(R.id.rvProduct);
        adapter = new ProductAdapter(this,list);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        service = RetrofitBuider.createService(IRetrofitService.class);

        fabInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, InsertProductActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        loadProduct();
    }

    public void loadProduct(){
        service.getAllProduct().enqueue(getAllProduct);
    }

    Callback<List<Product>> getAllProduct= new Callback<List<Product>>() {
        @Override
        public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
            if (response.isSuccessful()) {
                List<Product> productsResponses = response.body();
                adapter.updateData(productsResponses);
            }
        }

        @Override
        public void onFailure(Call<List<Product>> call, Throwable t) {
            Log.d(">>> register", "onFailure"+ t.getMessage());
        }
    };
}