package pers.maoqi.core;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import pers.maoqi.core.util.LogUtils;

/**
 * Created by maoqi on 2017/8/2.
 */

public class CoreBaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtils.d("BaseActivity", "onCreate: " + getClass().getSimpleName());
    }

    @Override
    protected void onResume() {
        super.onResume();
        LogUtils.d("BaseActivity", "onResume: " + getClass().getSimpleName());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LogUtils.d("BaseActivity", "onDestroy: " + getClass().getSimpleName());
    }
}
