package com.example.fastfood.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.fastfood.Adapter.DynamicRVAdapter;
import com.example.fastfood.Adapter.ItemClickListener;
import com.example.fastfood.Adapter.ProductAdapter;
import com.example.fastfood.Adapter.StaticRvAdapter;
import com.example.fastfood.InsertProductActivity;
import com.example.fastfood.MainActivity;
import com.example.fastfood.Responses.ProductResponses;
import com.example.fastfood.model.Product;
import com.example.fastfood.R;
import com.example.fastfood.Retrofits.IRetrofitService;
import com.example.fastfood.Retrofits.RetrofitBuider;
import com.example.fastfood.model.Product;
import com.example.fastfood.model.StaticRvModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
public class ProductFragment extends Fragment {
    IRetrofitService service;
    RecyclerView recyclerView;
    ProductAdapter adapter;
    FloatingActionButton fabInsert;
    List<Product> list = new ArrayList<>();


    public ProductFragment() {
        // Required empty public constructor
    }

    public static ProductFragment newInstance() {
        ProductFragment fragment = new ProductFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_product, container, false);
        fabInsert = root.findViewById(R.id.fabInsertProduct);
        recyclerView = root.findViewById(R.id.rvProduct);
        adapter = new ProductAdapter(getActivity(),list);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        service = RetrofitBuider.createService(IRetrofitService.class);

        fabInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), InsertProductActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });


        adapter.setOnItemEditClickstener(new ItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Product product = (Product) adapter.getItem(position);
                Intent intent = new Intent(getActivity(), InsertProductActivity.class);
                intent.putExtra("id", product.getId());
                startActivity(intent);
            }
        });

        adapter.setOnItemDeleteClickstener(new ItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Product product = (Product) adapter.getItem(position);
                service.deleteOneProduct(product.getId()).enqueue(deleteProduct);
            }
        });


        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        loadProduct();
    }

    public void loadProduct(){
        service.getAllProduct().enqueue(getAllProduct);
    }

    Callback<List<Product>> getAllProduct= new Callback<List<Product>>() {
        @Override
        public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
            if (response.isSuccessful()) {
                List<Product> productsResponses = response.body();
                adapter.updateData(productsResponses);
            }
        }

        @Override
        public void onFailure(Call<List<Product>> call, Throwable t) {
            Log.d(">>> register", "onFailure"+ t.getMessage());
        }
    };

    Callback<Product> deleteProduct = new Callback<Product>() {
        @Override
        public void onResponse(Call<Product> call, Response<Product> response) {
            if (response.isSuccessful()) {
                Product result = response.body();
                Toast.makeText(getActivity(), "Delete successssfuly", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        }

        @Override
        public void onFailure(Call<Product> call, Throwable t) {
            Log.d(">>> delete", "onFailure"+ t.getMessage());
        }
    };
}