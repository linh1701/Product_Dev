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

import com.example.fastfood.LoginActivity;
import com.example.fastfood.MainActivity;
import com.example.fastfood.R;
import com.example.fastfood.Requests.RegisterRequest;
import com.example.fastfood.Responses.RegisterResponses;
import com.example.fastfood.Retrofits.IRetrofitService;
import com.example.fastfood.Retrofits.RetrofitBuider;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterTabFragment extends Fragment {
    IRetrofitService service;
    EditText edDkEmail, edDkPass, edConfirm;
    Button btnRegister;
    TextView tvLogin, tv17, tvTitle;
    float v =0;

    public static Fragment newInstance() {
        RegisterTabFragment fragment = new RegisterTabFragment();

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.register_tab_fragment, container, false);
        edDkEmail = root.findViewById(R.id.edDkEmail);
        edDkPass = root.findViewById(R.id.edDkPassWord);
        edConfirm = root.findViewById(R.id.edConfinrPassWord);
        btnRegister = root.findViewById(R.id.btnRegister);
        tvLogin = root.findViewById(R.id.tvLogin);
        tv17 = root.findViewById(R.id.textView17);
        tvTitle = root.findViewById(R.id.titleRegister);
        service = RetrofitBuider.createService(IRetrofitService.class);

        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRegister();
            }
        });

        edDkEmail.setTranslationX(800);
        edDkPass.setTranslationX(800);
        edConfirm.setTranslationX(800);
        btnRegister.setTranslationX(800);
        tvLogin.setTranslationX(800);
        tv17.setTranslationX(800);
        tvTitle.setTranslationX(800);

        edDkEmail.setAlpha(v);
        edDkPass.setAlpha(v);
        edConfirm.setAlpha(v);
        btnRegister.setAlpha(v);
        tvLogin.setAlpha(v);
        tv17.setAlpha(v);
        tvTitle.setAlpha(v);

        edDkEmail.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(300).start();
        edDkPass.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(500).start();
        edConfirm.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(500).start();
        btnRegister.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(700).start();
        tvLogin.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(500).start();
        tv17.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(500).start();
        tvTitle.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(500).start();
        return root;
    }

    public void onRegister(){
        String username = edDkEmail.getText().toString();
        String password = edDkPass.getText().toString();
        if (validate() >0){
            RegisterRequest request = new RegisterRequest(username, password);
            service.register(request).enqueue(register);
        }
    }

    Callback<RegisterResponses> register= new Callback<RegisterResponses>() {
        @Override
        public void onResponse(Call<RegisterResponses> call, Response<RegisterResponses> response) {
            if (response.body().getStatus() && response.body().getResult()) {
                RegisterResponses registerResponses = response.body();
                Log.d(">>> register", "onResponse" + registerResponses.getStatus());
                Toast.makeText(getActivity(),"Đăng kí thành công",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
                getActivity().finish();
            }else {
                Toast.makeText(getActivity(),"Đăng nhập thất bại",Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onFailure(Call<RegisterResponses> call, Throwable t) {
            Log.d(">>> register", "onFailure"+ t.getMessage());
        }
    };

    public int validate() {
        String password = edDkPass.getText().toString();
        String rePass = edConfirm.getText().toString();
        int check = 1;
        if (edDkEmail.getText().length() == 0 || edDkPass.getText().length() == 0 || edConfirm.getText().length() == 0) {
            Toast.makeText(requireContext(), "Bạn phải nhập đầy đủ thông tin", Toast.LENGTH_LONG).show();
            check = -1;
        } else {
            if (!password.equals(rePass)) {
                Toast.makeText(getActivity(), "Mật khẩu không trùng khớp", Toast.LENGTH_LONG).show();
                check = -1;
            }
        }
        return check;
    }
}
