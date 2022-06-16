package com.example.lab4.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import com.example.lab4.MainActivity;
import com.example.lab4.R;
import com.example.lab4.Requests.RegisterRequest;
import com.example.lab4.Responses.RegisterResponses;
import com.example.lab4.Retrofits.IRetrofitService;
import com.example.lab4.Retrofits.RetrofitBuider;

public class RegisterActivity extends AppCompatActivity {
    Button btnRester;
    EditText edEmail,edPass;
    IRetrofitService service;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        btnRester = findViewById(R.id.btnRegister);
        edEmail = findViewById(R.id.edEmail);
        edPass = findViewById(R.id.edRePass);
        service = RetrofitBuider.createService(IRetrofitService.class);
    }

    public void onRegister(View view){
        String username = edEmail.getText().toString();
        String password = edPass.getText().toString();
        RegisterRequest request = new RegisterRequest(username, password);
        service.register(request).enqueue(register);
    }

    Callback<RegisterResponses> register= new Callback<RegisterResponses>() {
        @Override
        public void onResponse(Call<RegisterResponses> call, Response<RegisterResponses> response) {
            if (response.body().getStatus() && response.body().getResult()) {
                RegisterResponses registerResponses = response.body();
                Log.d(">>> register", "onResponse" + registerResponses.getStatus());
                Toast.makeText(RegisterActivity.this,"Đăng nhập thất bại",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }else {
                Toast.makeText(RegisterActivity.this,"Đăng nhập thất bại",Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onFailure(Call<RegisterResponses> call, Throwable t) {
            Log.d(">>> register", "onFailure"+ t.getMessage());
        }
    };
}