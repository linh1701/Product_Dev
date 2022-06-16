package com.example.fastfood.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.fastfood.DashBoardActivity;
import com.example.fastfood.ForgotPassActivity;
import com.example.fastfood.MainActivity;
import com.example.fastfood.R;
import com.example.fastfood.Requests.LoginRequest;
import com.example.fastfood.Responses.LoginResponses;
import com.example.fastfood.Retrofits.IRetrofitService;
import com.example.fastfood.Retrofits.RetrofitBuider;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginTabFragment extends Fragment {
    EditText edEmail, edPass;
    Button btnLogin;
    TextView forgotPass, tvTitile, tvAA;
    TextView tvRegister;
    IRetrofitService service;
    float v =0;

    public static Fragment newInstance() {
        LoginTabFragment fragment = new LoginTabFragment();

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.login_tab_fragment, container, false);
        edEmail = root.findViewById(R.id.edLoginEmail);
        edPass = root.findViewById(R.id.edPassWord);
        forgotPass = root.findViewById(R.id.forgetPass);
        btnLogin = root.findViewById(R.id.btnLogin);
        tvRegister = root.findViewById(R.id.tvRegister);
        tvTitile = root.findViewById(R.id.titleLogin);
        tvAA = root.findViewById(R.id.textView16);
        service = RetrofitBuider.createService(IRetrofitService.class);

        edEmail.setText("linh1234");
        edPass.setText("123");

        forgotPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ForgotPassActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onLogin();
            }
        });


        edEmail.setTranslationX(800);
        edPass.setTranslationX(800);
        forgotPass.setTranslationX(800);
        btnLogin.setTranslationX(800);
        tvRegister.setTranslationX(800);
        tvTitile.setTranslationX(800);
        tvAA.setTranslationX(800);


        edEmail.setAlpha(v);
        edPass.setAlpha(v);
        forgotPass.setAlpha(v);
        btnLogin.setAlpha(v);
        tvRegister.setAlpha(v);
        tvTitile.setAlpha(v);
        tvAA.setAlpha(v);

        edEmail.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(300).start();
        edPass.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(500).start();
        forgotPass.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(500).start();
        btnLogin.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(700).start();
        tvRegister.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(500).start();
        tvTitile.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(500).start();
        tvAA.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(300).start();

        return root;
    }

    public void onLogin(){
        String username = edEmail.getText().toString();
        String password = edPass.getText().toString();

        if (username.isEmpty() || password.isEmpty()){
            Toast.makeText(getActivity(),"Bạn phải nhập đầy đủ thông tin",Toast.LENGTH_SHORT).show();
        }else {
            LoginRequest request = new LoginRequest(username, password);
            service.login(request).enqueue(login);
        }
    }

    Callback<LoginResponses> login= new Callback<LoginResponses>() {
        @Override
        public void onResponse(Call<LoginResponses> call, Response<LoginResponses> response) {
            if (response.body().getStatus() && response.body().getResult()) {
                LoginResponses loginResponses = response.body();
                Log.d(">>> login", "onResponse " + loginResponses.getStatus());
                Toast.makeText(getActivity(),"Đăng nhập thành công",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
            }else {
                Toast.makeText(getActivity(),"Đăng nhập thất bại",Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onFailure(Call<LoginResponses> call, Throwable t) {
            Log.d(">>> login", "onFailure"+ t.getMessage());
        }
    };
}
