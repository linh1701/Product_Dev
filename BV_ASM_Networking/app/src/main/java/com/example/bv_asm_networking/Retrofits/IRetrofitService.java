package com.example.bv_asm_networking.Retrofits;
import com.example.bv_asm_networking.Requests.ProductRequest;
import com.example.bv_asm_networking.Responses.ProductResponses;
import com.example.bv_asm_networking.model.Product;
import com.example.fastfood.Requests.UploadRequest;
import com.example.fastfood.Responses.UploadResponses;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface IRetrofitService {

    @POST("/views/insert-product.php")
    Call<ProductResponses> insertProduct(@Body ProductRequest body);

    @POST("/views/update-product.php")
    Call<ProductResponses> updateProduct(@Body ProductRequest body);


    @POST("/views/upload.php")
    Call<UploadResponses> upload(@Body UploadRequest body);
// viiew product
    @GET("/views/getAllProduct.php")
    Call<List<Product>> getAllProduct();

    @GET("/views/get-one-product.php")
    Call<Product> getOneProduct(@Query("id") Integer id);

    @GET("/views/delete-one-product.php")
    Call<Product> deleteOneProduct(@Query("id") Integer id);

}
