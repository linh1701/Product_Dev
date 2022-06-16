package com.example.fastfood;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import com.example.fastfood.Requests.ForgotRequest;
import com.example.fastfood.Responses.ForgotResponses;
import com.example.fastfood.Retrofits.IRetrofitService;
import com.example.fastfood.Retrofits.RetrofitBuider;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForgotPassActivity extends AppCompatActivity {
    EditText edEmail;
    Button btnForgot;
    IRetrofitService service;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_pass);
        btnForgot = findViewById(R.id.btnForgot);
        edEmail = findViewById(R.id.edForgotEmail);
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