package com.example.lab4.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.example.lab4.R;
import com.example.lab4.Requests.ForgotRequest;
import com.example.lab4.Responses.ForgotResponses;
import com.example.lab4.Retrofits.IRetrofitService;
import com.example.lab4.Retrofits.RetrofitBuider;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForgotPasswordActivity extends AppCompatActivity {
    EditText edEmail;
    Button btnForgot;
    IRetrofitService service;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        btnForgot = findViewById(R.id.btnForgot);
        edEmail = findViewById(R.id.edforgotEmail);
        service = RetrofitBuider.createService(IRetrofitService.class);
        btnForgot.setOnClickListener(view ->{
            onForgot();
        });
    }

    public void onForgot(){
        String username = edEmail.getText().toString();
        ForgotRequest request = new ForgotRequest(username);
        service.forgot(request).enqueue(forgot);
    }

    Callback<ForgotResponses> forgot= new Callback<ForgotResponses>() {
        @Override
        public void onResponse(Call<ForgotResponses> call, Response<ForgotResponses> response) {
            if (response.isSuccessful()) {
                ForgotResponses forgotResponses = response.body();
                Log.d(">>> forgot", "onResponse " + forgotResponses.getStatus());
            }
        }

        @Override
        public void onFailure(Call<ForgotResponses> call, Throwable t) {
            Log.d(">>> forgot", "onFailure"+ t.getMessage());
        }
    };
}