package com.example.fastfood.DRVinterface;

import com.example.fastfood.Adapter.DynamicRVAdapter;
import com.example.fastfood.model.DynamicrvModel;

import java.util.ArrayList;

public interface UpdateRecyclerView {
    public void callback(int positon, ArrayList<DynamicrvModel> items);
}
