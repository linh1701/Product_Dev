package com.example.lab4.text;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
public interface RequestInterface {
    @POST("learn-login-register/")
    Call<ServerResponse> operation(@Body ServerRequest request);
}
