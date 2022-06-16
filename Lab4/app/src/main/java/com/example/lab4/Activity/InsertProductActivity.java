package com.example.lab4.Activity;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.lab4.R;
import com.example.lab4.Requests.LoginRequest;
import com.example.lab4.Requests.ProductRequest;
import com.example.lab4.Requests.UploadRequest;
import com.example.lab4.Responses.ProductResponses;
import com.example.lab4.Responses.UploadResponses;
import com.example.lab4.Retrofits.IRetrofitService;
import com.example.lab4.Retrofits.RetrofitBuider;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import java.io.ByteArrayOutputStream;
import java.util.List;

import gun0912.tedbottompicker.TedBottomPicker;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class InsertProductActivity extends AppCompatActivity {
    IRetrofitService service;
    EditText edprice, edname, edquantity, edcaterory_id;
    ImageView imageInsertProduct;
    Button capture;
    Button btnLuu;
    Spinner spinnerProductId;
    String selectedLopId = null;
    String path;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_product);
        edprice = (EditText) findViewById(R.id.edPrice);
        edname = (EditText) findViewById(R.id.edAddName);
        edquantity = (EditText) findViewById(R.id.edQuantity);
        edcaterory_id = (EditText) findViewById(R.id.edAddCaterory);
        spinnerProductId = (Spinner) findViewById(R.id.spinerProdcutId);
        imageInsertProduct = (ImageView) findViewById(R.id.imageAddProduct);
        capture = (Button) findViewById(R.id.btnTakePicture);
        btnLuu = (Button) findViewById(R.id.btnLuu);
        service = RetrofitBuider.createService(IRetrofitService.class);
        if (!checkCamera()) requestCamera();;

        capture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onTakePictureClick();
            }
        });

        btnLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onInsertClick();
            }
        });
    }

    public boolean checkCamera(){
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)!= PackageManager.PERMISSION_DENIED){
            return false;
        }
        return true;
    }

    public void requestCamera(){
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.CAMERA}, 2000);
    }

    public void onTakePictureClick(){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        try {
            activityResultLaunch.launch(intent);
        } catch (Exception e) {
            Log.d(">>>>>>TAG", "onTakePictureClick: " + e.getMessage());
        }
    }

    private void onInsertClick(){
        String name  = edname.getText().toString();
        Integer quantity = Integer.valueOf(edquantity.getText().toString());
        Integer category_id = Integer.valueOf(edcaterory_id.getText().toString());
        Float price = Float.valueOf(edprice.getText().toString());
        ProductRequest request = new ProductRequest(name, price, quantity,path,category_id);
        service.insertProduct(request).enqueue(insertProduct);
    }




    ActivityResultLauncher<Intent> activityResultLaunch = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    Bundle bundle = result.getData().getExtras();
                    Bitmap bitmap = (Bitmap) bundle.get("data");
                    imageInsertProduct.setImageBitmap(bitmap);

                    //upload hình dạng base64 len sever
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.PNG,50,baos);
                    byte[] bytes = baos.toByteArray();

                    String base64 = Base64.encodeToString(bytes,0);
                    UploadRequest request = new UploadRequest(base64);
                    service.upload(request).enqueue(uploadImage);
                }
            });


    Callback<UploadResponses> uploadImage= new Callback<UploadResponses>() {
        @Override
        public void onResponse(Call<UploadResponses> call, Response<UploadResponses> response) {
            if (response.isSuccessful()) {
                UploadResponses uploadResponses = response.body();
                Log.d(">>>upload", uploadResponses.getPath());
                path = uploadResponses.getPath();
            }
        }

        @Override
        public void onFailure(Call<UploadResponses> call, Throwable t) {
            Log.d(">>> upload", "onFailure"+ t.getMessage());
        }
    };

    Callback<ProductResponses> insertProduct = new Callback<ProductResponses>() {
        @Override
        public void onResponse(Call<ProductResponses> call, Response<ProductResponses> response) {
            if (response.isSuccessful()) {
                ProductResponses result = response.body();
                finish();
            }
        }

        @Override
        public void onFailure(Call<ProductResponses> call, Throwable t) {
            Log.d(">>> insert", "onFailure"+ t.getMessage());
        }
    };

}