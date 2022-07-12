package com.example.bv_asm_networking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.bv_asm_networking.Requests.ProductRequest;
import com.example.bv_asm_networking.Responses.ProductResponses;
import com.example.bv_asm_networking.Retrofits.IRetrofitService;
import com.example.bv_asm_networking.Retrofits.RetrofitBuider;
import com.example.bv_asm_networking.model.Product;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InsertProductActivity extends AppCompatActivity {
    IRetrofitService service;
    EditText edPrice, edname, edquantity;
    Button btnLuu;
    public Integer productId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_product);

        edPrice = (EditText) findViewById(R.id.edPrice);
        edname = (EditText) findViewById(R.id.edAddName);
        btnLuu = (Button) findViewById(R.id.btnLuu);
        service = RetrofitBuider.createService(IRetrofitService.class);

        btnLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onInsertClick();
            }
        });
    }

    public void onInsertClick(){
        String name  = edname.getText().toString();
        String price =edPrice.getText().toString();

        if (name.isEmpty() || price.isEmpty()){
            Toast.makeText(InsertProductActivity.this,"Bạn phải nhập đầy đủ thông tin",Toast.LENGTH_SHORT).show();
        }else {
            ProductRequest request = new ProductRequest(name, Float.valueOf(price));
            service.insertProduct(request).enqueue(insertProduct);
        }

    }

    Callback<ProductResponses> insertProduct = new Callback<ProductResponses>() {
        @Override
        public void onResponse(Call<ProductResponses> call, Response<ProductResponses> response) {
            if (response.isSuccessful()) {
                ProductResponses result = response.body();
                Intent intent = new Intent(InsertProductActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }

        @Override
        public void onFailure(Call<ProductResponses> call, Throwable t) {
            Log.d(">>> insert", "onFailure"+ t.getMessage());
        }
    };
;



}