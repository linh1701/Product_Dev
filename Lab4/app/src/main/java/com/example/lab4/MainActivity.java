package com.example.lab4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.lab4.Activity.InsertProductActivity;
import com.example.lab4.Activity.LoginActivity;
import com.example.lab4.Activity.RegisterActivity;
import com.example.lab4.Adapter.RvProductAdapter;
import com.example.lab4.Responses.RegisterResponses;
import com.example.lab4.Retrofits.IRetrofitService;
import com.example.lab4.Retrofits.RetrofitBuider;
import com.example.lab4.model.Product;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    IRetrofitService service;
    RecyclerView recyclerView;
    RvProductAdapter adapter;
    Button btnInsert;
    List<Product> list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView =findViewById(R.id.rv_prodcut);
        btnInsert = findViewById(R.id.addProduct);
        adapter = new RvProductAdapter(this,list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        service = RetrofitBuider.createService(IRetrofitService.class);

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, InsertProductActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
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