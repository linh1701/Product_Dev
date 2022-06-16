package com.example.lab4.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lab4.MainActivity;
import com.example.lab4.R;
import com.example.lab4.Requests.LoginRequest;
import com.example.lab4.Requests.RegisterRequest;
import com.example.lab4.Responses.LoginResponses;
import com.example.lab4.Responses.RegisterResponses;
import com.example.lab4.Retrofits.IRetrofitService;
import com.example.lab4.Retrofits.RetrofitBuider;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    Button btnLogin, btnRegister;
    EditText edEmail,edPass;
    IRetrofitService service;
    TextView tv_forot;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btnLogin = findViewById(R.id.btnLogin);
        btnRegister = findViewById(R.id.btnLgRegister);
        edEmail = findViewById(R.id.edLgEmail);
        edPass = findViewById(R.id.edLgPass);
        tv_forot = findViewById(R.id.tv_fotrot);
        service = RetrofitBuider.createService(IRetrofitService.class);

        tv_forot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, ForgotPasswordActivity.class);
                startActivity(intent);
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    public void onLogin(View view){
        String username = edEmail.getText().toString();
        String password = edPass.getText().toString();
        LoginRequest request = new LoginRequest(username, password);
        service.login(request).enqueue(login);
    }

    Callback<LoginResponses> login= new Callback<LoginResponses>() {
        @Override
        public void onResponse(Call<LoginResponses> call, Response<LoginResponses> response) {
            if (response.body().getStatus() && response.body().getResult()) {
                LoginResponses loginResponses = response.body();
                Log.d(">>> login", "onResponse " + loginResponses.getStatus());
                Toast.makeText(LoginActivity.this,"Đăng nhập thành công",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
            }else {
                Toast.makeText(LoginActivity.this,"Đăng nhập thất bại",Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onFailure(Call<LoginResponses> call, Throwable t) {
            Log.d(">>> login", "onFailure"+ t.getMessage());
        }
    };
}