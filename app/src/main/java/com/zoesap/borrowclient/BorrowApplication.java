package com.zoesap.borrowclient;

import android.app.Application;
import android.content.Context;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by maoqi on 2017/7/20.
 */

public class BorrowApplication extends Application {
    //全局的context
    private Context appcontext;
    //维护一个线程池
    public static ExecutorService fixedThreadPool = Executors.newFixedThreadPool(5);
    private boolean mSignIn = false;// 登录标志
    private static BorrowApplication mBorrowApplication = null;

    public static BorrowApplication getInstance() {
        synchronized (BorrowApplication.class) {
            if (mBorrowApplication == null) {
                mBorrowApplication = new BorrowApplication();
            }
        }
        return mBorrowApplication;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        appcontext = this.getApplicationContext();
    }

    public Context getAppcontext() {
        if (appcontext == null) {
            appcontext = getApplicationContext();
        }
        return appcontext;
    }

    public boolean ismSignIn() {
        return mSignIn;
    }

    public void setmSignIn(boolean mSignIn) {
        this.mSignIn = mSignIn;
    }
}
