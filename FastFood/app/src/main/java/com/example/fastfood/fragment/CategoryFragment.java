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
import android.widget.Toast;

import com.example.fastfood.Adapter.CategoryAdapter;
import com.example.fastfood.Adapter.ItemClickListener;
import com.example.fastfood.Adapter.ProductAdapter;
import com.example.fastfood.InsertCategoryActivity;
import com.example.fastfood.InsertProductActivity;;
import com.example.fastfood.MainActivity;
import com.example.fastfood.R;
import com.example.fastfood.Retrofits.IRetrofitService;
import com.example.fastfood.Retrofits.RetrofitBuider;
import com.example.fastfood.model.Category;
import com.example.fastfood.model.Product;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryFragment extends Fragment {

    IRetrofitService service;
    RecyclerView recyclerView;
    CategoryAdapter adapter;
    FloatingActionButton fab;
    List<Category> list = new ArrayList<>();


    public CategoryFragment() {
        // Required empty public constructor
    }

    public static CategoryFragment newInstance() {
        CategoryFragment fragment = new CategoryFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_category, container, false);
        fab = root.findViewById(R.id.fabCategory);
        recyclerView = root.findViewById(R.id.rvCategory);
        adapter = new CategoryAdapter(getActivity(),list);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        service = RetrofitBuider.createService(IRetrofitService.class);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), InsertCategoryActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });

        adapter.setOnItemEditClickstener(new ItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Category category = (Category) adapter.getItem(position);
                Intent intent = new Intent(getActivity(), InsertCategoryActivity.class);
                intent.putExtra("id", category.getId());
                startActivity(intent);
            }
        });

        adapter.setOnItemDeleteClickstener(new ItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Category category = (Category) adapter.getItem(position);
                service.deleteOneCategory(category.getId()).enqueue(deleteCategory);
            }
        });
        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        loadCategories();
    }

    public void loadCategories(){
        service.getCategory().enqueue(getCategory);
    }

    Callback<List<Category>> getCategory= new Callback<List<Category>>() {
        @Override
        public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
            if (response.isSuccessful()) {
                List<Category> categories = response.body();
                adapter.updateData(categories);
            }
        }

        @Override
        public void onFailure(Call<List<Category>> call, Throwable t) {
            Log.d(">>> category", "onFailure"+ t.getMessage());
        }
    };

    Callback<Category> deleteCategory = new Callback<Category>() {
        @Override
        public void onResponse(Call<Category> call, Response<Category> response) {
            if (response.isSuccessful()) {
                Category result = response.body();
                Toast.makeText(getActivity(), "Delete successssfuly", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        }

        @Override
        public void onFailure(Call<Category> call, Throwable t) {
            Log.d(">>> delete", "onFailure"+ t.getMessage());
        }
    };
}