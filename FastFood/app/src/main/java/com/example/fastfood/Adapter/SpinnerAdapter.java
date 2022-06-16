package com.example.fastfood.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.fastfood.R;
import com.example.fastfood.model.Category;

import java.util.List;

public class SpinnerAdapter extends BaseAdapter {
    private List<Category> data;
    private Context context;

    public SpinnerAdapter(Context _context, List<Category> _data){
        context = _context;
        data = _data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public int getPositon(int categoryId){
        int index = -1;
        for (int i = 0; i< data.size(); i++){
            if (data.get(i).getId().equals(categoryId)){
                index = i;
                break;
            }
        }
        return index;
    }
    @Override
    public View getView(int _i, View _view, ViewGroup _viewGroup) {
        View view = _view;
        if (_view == null){
            view = View.inflate(_viewGroup.getContext(),
                    R.layout.spinner_item_layout, null);
        }

        TextView Name = (TextView) view.findViewById(R.id.textViewLopItem);

        Category category = (Category) getItem(_i);
        Name.setText(category.getName());
        Glide.with(context).load(category.getImage()).into((ImageView) view.findViewById(R.id.ivCategory));

        return view;
    }
}
