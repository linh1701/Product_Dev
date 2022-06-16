package com.example.fastfood.Retrofits;
import com.example.fastfood.Requests.CategoryRequest;
import com.example.fastfood.Requests.ForgotRequest;
import com.example.fastfood.Requests.LoginRequest;
import com.example.fastfood.Requests.ProductRequest;
import com.example.fastfood.Requests.RegisterRequest;
import com.example.fastfood.Requests.UploadRequest;
import com.example.fastfood.Responses.CategoryResponses;
import com.example.fastfood.Responses.ForgotResponses;
import com.example.fastfood.Responses.LoginResponses;
import com.example.fastfood.Responses.ProductResponses;
import com.example.fastfood.Responses.RegisterResponses;
import com.example.fastfood.Responses.UploadResponses;
import com.example.fastfood.model.Category;
import com.example.fastfood.model.Product;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface IRetrofitService {
    @POST("/views/user_register.php")
    Call<RegisterResponses> register(@Body RegisterRequest body);

    @POST("/views/user_login.php")
    Call<LoginResponses> login(@Body LoginRequest body);

    @POST("/views/forgot-password.php")
    Call<ForgotResponses> forgot(@Body ForgotRequest body);

    @POST("/views/insert-product.php")
    Call<ProductResponses> insertProduct(@Body ProductRequest body);

    @POST("/views/update-product.php")
    Call<ProductResponses> updateProduct(@Body ProductRequest body);

    @POST("/views/insert-category.php")
    Call<CategoryResponses> insertCategories(@Body CategoryRequest body);

    @POST("/views/update-category.php")
    Call<CategoryResponses> updateCategories(@Body CategoryRequest body);

    @POST("/views/upload.php")
    Call<UploadResponses> upload(@Body UploadRequest body);
// viiew product
    @GET("/views/getAllProduct.php")
    Call<List<Product>> getAllProduct();

    @GET("/views/get-one-product.php")
    Call<Product> getOneProduct(@Query("id") Integer id);

    @GET("/views/delete-one-product.php")
    Call<Product> deleteOneProduct(@Query("id") Integer id);
    // Get categoies
    @GET("/views/get-categories.php")
    Call<List<Category>> getCategory();

    @GET("/views/get-one-categories.php")
    Call<Category> getOneCategory(@Query("id") Integer id);

    @GET("/views/delete-one-category.php")
    Call<Category> deleteOneCategory(@Query("id") Integer id);
}
