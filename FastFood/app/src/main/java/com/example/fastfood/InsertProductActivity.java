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
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.fastfood.Adapter.SpinnerAdapter;
import com.example.fastfood.model.Category;
import com.example.fastfood.Requests.ProductRequest;
import com.example.fastfood.Requests.UploadRequest;
import com.example.fastfood.Responses.ProductResponses;
import com.example.fastfood.Responses.UploadResponses;
import com.example.fastfood.Retrofits.IRetrofitService;
import com.example.fastfood.Retrofits.RetrofitBuider;
import com.example.fastfood.model.Product;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InsertProductActivity extends AppCompatActivity {
    Bitmap bitmapImg;
    IRetrofitService service;
    EditText edPrice, edname, edquantity, edcaterory_id;
    ImageView imageInsertProduct, ivCapture;
    Button btnAdd;
    Spinner spinnerProductId;
    int selectedCategoryId = -1;
    String path;
    public Integer productId;
    SpinnerAdapter adapter;

    ArrayList<Category> categories = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_product);
        edPrice = (EditText) findViewById(R.id.edPrice);
        edname = (EditText) findViewById(R.id.edAddName);
        edquantity = (EditText) findViewById(R.id.edQuantity);
        spinnerProductId = (Spinner) findViewById(R.id.spinnerCategory);
        imageInsertProduct = (ImageView) findViewById(R.id.image_addProduct);
        ivCapture = (ImageView) findViewById(R.id.ivCature);
        btnAdd = (Button) findViewById(R.id.btnInsertProduct);
        service = RetrofitBuider.createService(IRetrofitService.class);

        loadCategory();



        if (!checkCamera()) requestCamera();

        Intent intent = getIntent();
        productId = intent.getIntExtra("id", -1);

        if (productId > -1){
            service.getOneProduct(productId).enqueue(getOnProduct);
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

        imageInsertProduct.setOnClickListener(new View.OnClickListener() {
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
            imageInsertProduct.setImageBitmap(bitmapImg);
        } else if (requestCode == 2 && resultCode == RESULT_OK) {
            try {
                Uri uri = data.getData();
                bitmapImg = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                imageInsertProduct.setImageBitmap(bitmapImg);
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
        String name  = edname.getText().toString();
        Integer quantity = Integer.valueOf(edquantity.getText().toString());
//        Integer category_id = Integer.valueOf(edcaterory_id.getText().toString());
//        String category_id = selectedCategoryId;
        Float price = Float.valueOf(edPrice.getText().toString());

        if (productId > -1){
            ProductRequest request = new ProductRequest(productId, name, price, quantity,path,selectedCategoryId);
            service.updateProduct(request).enqueue(updateProduct);
        }else {
            ProductRequest request = new ProductRequest(name, price, quantity,path,selectedCategoryId);
            service.insertProduct(request).enqueue(insertProduct);
        }

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
                Intent intent = new Intent(InsertProductActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }

        @Override
        public void onFailure(Call<ProductResponses> call, Throwable t) {
            Log.d(">>> insert", "onFailure"+ t.getMessage());
        }
    };
    Callback<ProductResponses> updateProduct = new Callback<ProductResponses>() {
        @Override
        public void onResponse(Call<ProductResponses> call, Response<ProductResponses> response) {
            if (response.isSuccessful()) {
                ProductResponses result = response.body();
                Toast.makeText(InsertProductActivity.this, "Update successssfuly", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(InsertProductActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }

        @Override
        public void onFailure(Call<ProductResponses> call, Throwable t) {
            Log.d(">>> update", "onFailure"+ t.getMessage());
        }
    };

    Callback<Product> getOnProduct = new Callback<Product>() {
        @Override
        public void onResponse(Call<Product> call, Response<Product> response) {
            if (response.isSuccessful()) {
                Product result = response.body();
                edname.setText(result.getName());
                edPrice.setText(result.getPrice()+"");
                edquantity.setText(result.getQuantity()+"");
//                edcaterory_id.setText(result.getCategory_id()+"");
                spinnerProductId.setSelection(adapter.getPositon(result.getCategory_id()));
                Glide.with(InsertProductActivity.this).load(result.getImage()).into(imageInsertProduct);
                path = result.getImage();
            }
        }

        @Override
        public void onFailure(Call<Product> call, Throwable t) {
            Log.d(">>> insert", "onFailure"+ t.getMessage());
        }
    };

    public void loadCategory(){
        service.getCategory().enqueue(getCategory);
    }

    Callback<List<Category>> getCategory= new Callback<List<Category>>() {
        @Override
        public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
            if (response.isSuccessful()) {
                categories = new ArrayList(response.body());
                adapter = new SpinnerAdapter(InsertProductActivity.this, categories);

                spinnerProductId.setAdapter(adapter);
                spinnerProductId.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedCategoryId = ((Category) adapter.getItem(i)).getId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
            }
        }

        @Override
        public void onFailure(Call<List<Category>> call, Throwable t) {
            Log.d(">>> category", "onFailure"+ t.getMessage());
        }
    };

}