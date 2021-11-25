package com.example.baseprojectandroid.src.page.splash_activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.example.baseprojectandroid.R;
import com.example.baseprojectandroid.src.page.bottom_navigator_bar.BottomNavigatorBarActivity;
import com.example.baseprojectandroid.src.page.login_activity.LoginActivity;
import com.example.baseprojectandroid.utils.SharePrefs;


public class SplashActivity extends AppCompatActivity {
    private Handler mHandler;
    private Runnable mRunable;
    private SharePrefs mSharePrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        mSharePrefs = new SharePrefs(SplashActivity.this);
        mHandler = new Handler();
        mRunable = new Runnable() {
            @Override
            public void run() {
              if(mSharePrefs.getIsLogin()){
                  startActivity(new Intent(getApplicationContext(), BottomNavigatorBarActivity.class));
              }else {
                  startActivity(new Intent(getApplicationContext(), LoginActivity.class));
              }
              overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_right);
              finish();
            }
        };
        mHandler.postDelayed(mRunable,3000);
    }
}