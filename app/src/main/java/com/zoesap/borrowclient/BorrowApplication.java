package com.zoesap.borrowclient;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import com.tencent.bugly.crashreport.CrashReport;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * Created by maoqi on 2017/7/20.
 */

public class BorrowApplication extends Application {
    //全局的context
    private static Context appcontext;
    //维护一个线程池
    public static ExecutorService fixedThreadPool = Executors.newFixedThreadPool(5);
    private boolean mSignIn = false;// 登录标志
    private static BorrowApplication mBorrowApplication = null;
    private static List<Activity> activityList = new ArrayList<>();

    public static BorrowApplication getInstance() {
        synchronized (BorrowApplication.class) {
            if (mBorrowApplication == null) {
                mBorrowApplication = new BorrowApplication();
            }
        }
        return mBorrowApplication;
    }

    public static void addActivity(Activity activity) {
        activityList.add(activity);
    }

    public static void removeActivity(Activity activity) {
        activityList.remove(activity);
    }

    public static void removeAllActivity() {
        for (Activity a : activityList) {
            a.finish();
        }
    }

    public static void destoryActivity(String activityName) {
        for (Activity a : activityList) {
            if (a.getClass().getSimpleName().equals(activityName)) {
                a.finish();
            }
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        appcontext = getApplicationContext();
        CrashReport.initCrashReport(getApplicationContext(), "61a121b2c4", false);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
//                .addInterceptor(new LoggerInterceptor("OkhttpUtils"))
                .connectTimeout(5000L, TimeUnit.MILLISECONDS)
                .readTimeout(5000L, TimeUnit.MILLISECONDS)
                //其他配置
                .build();

        OkHttpUtils.initClient(okHttpClient);
    }

    public static Context getAppcontext() {
        if (appcontext == null) {
            appcontext = getInstance().getApplicationContext();
        }
        return appcontext;
    }

    public boolean ismSignIn() {
        return mSignIn;
    }

    public void setmSignIn(boolean mSignIn) {
        this.mSignIn = mSignIn;
    }

    public void loginout() {
        mSignIn = false;
    }

}
