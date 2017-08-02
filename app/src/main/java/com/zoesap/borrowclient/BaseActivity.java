package com.zoesap.borrowclient;

import android.os.Bundle;
import android.support.annotation.Nullable;

import pers.maoqi.core.CoreBaseActivity;

/**
 * Created by Administrator on 2017/5/2.
 */

public abstract class BaseActivity extends CoreBaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BorrowApplication.addActivity(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        BorrowApplication.removeActivity(this);
    }

//    public void anima() {
//        int version = Integer.valueOf(android.os.Build.VERSION.SDK);
//        if (version >= 5) {
//            overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
//        }
//    }
}
