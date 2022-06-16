package com.example.fastfood.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.fastfood.Adapter.DynamicRVAdapter;
import com.example.fastfood.Adapter.StaticRvAdapter;
import com.example.fastfood.DRVinterface.LoadMore;
import com.example.fastfood.DRVinterface.UpdateRecyclerView;
import com.example.fastfood.R;
import com.example.fastfood.model.DynamicrvModel;
import com.example.fastfood.model.StaticRvModel;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class DashBoardFragment extends Fragment implements UpdateRecyclerView {
    private RecyclerView recyclerView, recyclerView2;
    private StaticRvAdapter staticRvAdapter;
    ArrayList<DynamicrvModel> items = new ArrayList<>();
    DynamicRVAdapter dybnamicRVAdapter;

    public static Fragment newInstance() {
        DashBoardFragment fragment = new DashBoardFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.dashboard_fragment, container, false);

        ArrayList<StaticRvModel> item = new ArrayList<>();
        item.add(new StaticRvModel(R.drawable.pizza_1,"pizza"));
        item.add(new StaticRvModel(R.drawable.fast_food,"bánh mì"));
        item.add(new StaticRvModel(R.drawable.soft_drink,"nước ngọt"));
        item.add(new StaticRvModel(R.drawable.french_fries,"Khoai chiên"));
        item.add(new StaticRvModel(R.drawable.hamburger,"hamburger"));

        recyclerView = root.findViewById(R.id.rv_01);
        staticRvAdapter = new StaticRvAdapter(item, getActivity(), this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(staticRvAdapter);



        items = new ArrayList<>();
        recyclerView2 = root.findViewById(R.id.rv_02);
        dybnamicRVAdapter = new DynamicRVAdapter(items);
        recyclerView2.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        recyclerView2.setAdapter(dybnamicRVAdapter);


        return root;

    }

    @Override
    public void callback(int positon, ArrayList<DynamicrvModel> items) {
        dybnamicRVAdapter = new DynamicRVAdapter(items);
        dybnamicRVAdapter.notifyDataSetChanged();
        recyclerView2.setAdapter(dybnamicRVAdapter);
    }
}
