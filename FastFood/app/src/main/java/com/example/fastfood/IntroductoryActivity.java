package com.example.fastfood;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.widget.ImageView;

import com.airbnb.lottie.LottieAnimationView;
import com.example.fastfood.Tab.OnBoardingFragment1;
import com.example.fastfood.Tab.OnBoardingFragment2;
import com.example.fastfood.Tab.OnBoardingFragment3;

public class IntroductoryActivity extends AppCompatActivity {
    ImageView logo, appName, spLashImg;
    LottieAnimationView lottieAnimationView;

    private static final int NUM_PAGES = 3;
    private ViewPager viewPager;
    private ScreeSlidePagerAdapter pagerAdapter;

    Animation anim;
     private static int SPLASH_TIME_OUT =5000;
     SharedPreferences mSharedPref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introductory);
        logo = findViewById(R.id.logo);
        appName = findViewById(R.id.app_name);
        spLashImg = findViewById(R.id.img);
        lottieAnimationView = findViewById(R.id.lottie);

        viewPager = findViewById(R.id.pager);
        pagerAdapter = new ScreeSlidePagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);

        spLashImg.animate().translationY(-3000).setDuration(1000).setStartDelay(4000);
        logo.animate().translationY(2000).setDuration(1000).setStartDelay(4000);
        appName.animate().translationY(2000).setDuration(1000).setStartDelay(4000);
        lottieAnimationView.animate().translationY(2000).setDuration(1000).setStartDelay(4000);

//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                mSharedPref = getSharedPreferences("SharedPref",MODE_PRIVATE);
//                boolean isFirstTime = mSharedPref.getBoolean("firstTime",true);
//
//                if (isFirstTime){
//                    SharedPreferences.Editor editor = mSharedPref.edit();
//                    editor.putBoolean("firstTime", false);
//                    editor.commit();
//                } else {
//                    Intent intent = new Intent(IntroductoryActivity.this, LoginActivity.class);
//                    startActivity(intent);
//                    finish();
//                }
//            }
//        },SPLASH_TIME_OUT);
    }


    private class ScreeSlidePagerAdapter extends FragmentStatePagerAdapter {

        public ScreeSlidePagerAdapter(@NonNull FragmentManager fm) {
            super(fm);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0:
                    OnBoardingFragment1 tab1 = new OnBoardingFragment1();
                    return tab1;
                case 1:
                    OnBoardingFragment2 tab2 = new OnBoardingFragment2();
                    return tab2;
                case 2:
                    OnBoardingFragment3 tab3 = new OnBoardingFragment3();
                    return tab3;
            }
            return null;
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }
    }
}