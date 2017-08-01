package com.zoesap.borrowclient;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

/**
 * Created by Administrator on 2017/5/2.
 */

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BorrowApplication.addActivity(this);
        Log.d("BaseActivity", "onCreate: " + getClass().getSimpleName());
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("BaseActivity", "onResume: " + getClass().getSimpleName());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("BaseActivity", "onDestroy: " + getClass().getSimpleName());
        BorrowApplication.removeActivity(this);
    }

//    public void anima() {
//        int version = Integer.valueOf(android.os.Build.VERSION.SDK);
//        if (version >= 5) {
//            overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
//        }
//    }
}
