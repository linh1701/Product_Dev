package com.example.fastfood.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.fastfood.fragment.LoginTabFragment;
import com.example.fastfood.fragment.RegisterTabFragment;

import org.jetbrains.annotations.NotNull;

public class LoginAdapter extends FragmentStateAdapter {
    public LoginAdapter(@NonNull @NotNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @NotNull
    @Override
    public Fragment createFragment(int position) {
        Fragment fragment;
        if (position == 0){
            fragment = LoginTabFragment.newInstance();
        }else {
            fragment = RegisterTabFragment.newInstance();
        }
        return fragment;
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
