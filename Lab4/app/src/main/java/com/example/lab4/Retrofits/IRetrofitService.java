package com.example.lab4.Retrofits;
import com.example.lab4.Requests.ForgotRequest;
import com.example.lab4.Requests.LoginRequest;
import com.example.lab4.Requests.ProductRequest;
import com.example.lab4.Requests.RegisterRequest;
import com.example.lab4.Requests.UploadRequest;
import com.example.lab4.Responses.ForgotResponses;
import com.example.lab4.Responses.LoginResponses;
import com.example.lab4.Responses.ProductResponses;
import com.example.lab4.Responses.RegisterResponses;
import com.example.lab4.Responses.UploadResponses;
import com.example.lab4.model.Product;
import com.example.lab4.text.ServerRequest;
import com.example.lab4.text.ServerResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.GET;

public interface IRetrofitService {
    @POST("/views/user_register.php")
    Call<RegisterResponses> register(@Body RegisterRequest body);

    @POST("/views/user_login.php")
    Call<LoginResponses> login(@Body LoginRequest body);

    @POST("/views/forgot-password.php")
    Call<ForgotResponses> forgot(@Body ForgotRequest body);

    @POST("/views/insert-product.php")
    Call<ProductResponses> insertProduct(@Body ProductRequest body);

    @POST("/views/upload.php")
    Call<UploadResponses> upload(@Body UploadRequest body);

    @GET("/views/getAllProduct.php")
    Call<List<Product>> getAllProduct();
}
