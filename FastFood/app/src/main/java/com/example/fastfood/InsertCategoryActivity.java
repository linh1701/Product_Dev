package com.example.fastfood;

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
import android.net.Uri;
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

import com.bumptech.glide.Glide;
import com.example.fastfood.Adapter.SpinnerAdapter;
import com.example.fastfood.Requests.CategoryRequest;
import com.example.fastfood.Requests.ProductRequest;
import com.example.fastfood.Requests.UploadRequest;
import com.example.fastfood.Responses.CategoryResponses;
import com.example.fastfood.Responses.ProductResponses;
import com.example.fastfood.Responses.UploadResponses;
import com.example.fastfood.Retrofits.IRetrofitService;
import com.example.fastfood.Retrofits.RetrofitBuider;
import com.example.fastfood.model.Category;
import com.example.fastfood.model.Product;

import java.io.ByteArrayOutputStream;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InsertCategoryActivity extends AppCompatActivity {
    Bitmap bitmapImg;
    IRetrofitService service;
    EditText edName;
    ImageView imageInsertCategory, ivCapture;
    Button btnAdd;
    String path;
    public Integer categoryId;
    SpinnerAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_category);
        edName = (EditText) findViewById(R.id.edCateName);
        imageInsertCategory = (ImageView) findViewById(R.id.image_addCategory);
        ivCapture = (ImageView) findViewById(R.id.ivCatureCategory);
        btnAdd = (Button) findViewById(R.id.btnInsertCategory);
        service = RetrofitBuider.createService(IRetrofitService.class);

        Intent intent = getIntent();
        categoryId = intent.getIntExtra("id", -1);

        if (categoryId > -1){
            service.getOneCategory(categoryId).enqueue(getOnCategories);
            btnAdd.setText("Update");
        }else {
            btnAdd.setText("Insert");
        }

        ivCapture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onTakePictureClick();
            }
        });

        imageInsertCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Choose picture"), 2);
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (btnAdd.equals("Insert")){
//
//                }
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            Bundle bundle = data.getExtras();
            bitmapImg = (Bitmap) bundle.get("data");
            imageInsertCategory.setImageBitmap(bitmapImg);
        } else if (requestCode == 2 && resultCode == RESULT_OK) {
            try {
                Uri uri = data.getData();
                bitmapImg = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                imageInsertCategory.setImageBitmap(bitmapImg);
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bitmapImg.compress(Bitmap.CompressFormat.PNG,50,baos);
                byte[] bytes = baos.toByteArray();

                String base64 = Base64.encodeToString(bytes,0);
                UploadRequest request = new UploadRequest(base64);
                service.upload(request).enqueue(uploadImage);
            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), "Lỗi", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void onInsertClick(){
        String name  = edName.getText().toString();
        if (categoryId > -1){
            CategoryRequest request = new CategoryRequest(categoryId, name,path);
            service.updateCategories(request).enqueue(updateCategories);
        }else {
            CategoryRequest request = new CategoryRequest(categoryId, name,path);
            service.insertCategories(request).enqueue(insertCategories);
        }

    }




    ActivityResultLauncher<Intent> activityResultLaunch = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    Bundle bundle = result.getData().getExtras();
                    Bitmap bitmap = (Bitmap) bundle.get("data");
                    imageInsertCategory.setImageBitmap(bitmap);

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

    Callback<CategoryResponses> insertCategories = new Callback<CategoryResponses>() {
        @Override
        public void onResponse(Call<CategoryResponses> call, Response<CategoryResponses> response) {
            if (response.isSuccessful()) {
                CategoryResponses result = response.body();
                Toast.makeText(InsertCategoryActivity.this, "Insert successssfuly", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(InsertCategoryActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }

        @Override
        public void onFailure(Call<CategoryResponses> call, Throwable t) {
            Log.d(">>> insert", "onFailure"+ t.getMessage());
        }
    };

    Callback<CategoryResponses> updateCategories = new Callback<CategoryResponses>() {
        @Override
        public void onResponse(Call<CategoryResponses> call, Response<CategoryResponses> response) {
            if (response.isSuccessful()) {
                CategoryResponses result = response.body();
                Toast.makeText(InsertCategoryActivity.this, "Update successssfuly", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(InsertCategoryActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }

        @Override
        public void onFailure(Call<CategoryResponses> call, Throwable t) {
            Log.d(">>> update", "onFailure"+ t.getMessage());
        }
    };

    Callback<Category> getOnCategories = new Callback<Category>() {
        @Override
        public void onResponse(Call<Category> call, Response<Category> response) {
            if (response.isSuccessful()) {
                Category result = response.body();
                edName.setText(result.getName());
                Glide.with(InsertCategoryActivity.this).load(result.getImage()).into(imageInsertCategory);
                path = result.getImage();
            }
        }

        @Override
        public void onFailure(Call<Category> call, Throwable t) {
            Log.d(">>> insert", "onFailure"+ t.getMessage());
        }
    };
}