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
    EditText edPrice, edname, edquantity, edcaterory_id;
    Button btnLuu;
    public Integer productId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_product);

        edPrice = (EditText) findViewById(R.id.edPrice);
        edname = (EditText) findViewById(R.id.edAddName);
        edquantity = (EditText) findViewById(R.id.edQuantity);
        btnLuu = (Button) findViewById(R.id.btnLuu);
        service = RetrofitBuider.createService(IRetrofitService.class);

        Intent intent = getIntent();
        productId = intent.getIntExtra("id", -1);

        if (productId > -1){
            service.getOneProduct(productId).enqueue(getOnProduct);
            btnLuu.setText("Update");
        }else {
            btnLuu.setText("Insert");
        }

        btnLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onInsertClick();
            }
        });
    }

    public void onInsertClick(){
        String name  = edname.getText().toString();
        Integer quantity = Integer.valueOf(edquantity.getText().toString());
        Float price = Float.valueOf(edPrice.getText().toString());

        if (productId > -1){
            ProductRequest request = new ProductRequest(productId, name, price, quantity);
            service.updateProduct(request).enqueue(updateProduct);
        }else {
            ProductRequest request = new ProductRequest(name, price, quantity);
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
    Callback<ProductResponses> updateProduct = new Callback<ProductResponses>() {
        @Override
        public void onResponse(Call<ProductResponses> call, Response<ProductResponses> response) {
            if (response.isSuccessful()) {
                ProductResponses result = response.body();
                Toast.makeText(InsertProductActivity.this, "Update successssfuly", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(InsertProductActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }

        @Override
        public void onFailure(Call<ProductResponses> call, Throwable t) {
            Log.d(">>> update", "onFailure"+ t.getMessage());
        }
    };

    Callback<Product> getOnProduct = new Callback<Product>() {
        @Override
        public void onResponse(Call<Product> call, Response<Product> response) {
            if (response.isSuccessful()) {
                Product result = response.body();
                edname.setText(result.getName());
                edPrice.setText(result.getPrice()+"");
                edquantity.setText(result.getQuantity()+"");
////                edcaterory_id.setText(result.getCategory_id()+"");
//                spinnerProductId.setSelection(adapter.getPositon(result.getCategory_id()));
//                Glide.with(InsertProductActivity.this).load(result.getImage()).into(imageInsertProduct);
//                path = result.getImage();
            }
        }

        @Override
        public void onFailure(Call<Product> call, Throwable t) {
            Log.d(">>> insert", "onFailure"+ t.getMessage());
        }
    };


}