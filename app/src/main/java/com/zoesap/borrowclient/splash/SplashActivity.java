package com.zoesap.borrowclient.splash;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;

import com.zoesap.borrowclient.BaseActivity;
import com.zoesap.borrowclient.R;
import com.zoesap.borrowclient.data.Injection;
import com.zoesap.borrowclient.home.HomeActivity;

/**
 * Created by maoqi on 2017/7/31.
 */

public class SplashActivity extends BaseActivity {

    private FragmentManager mManager;
    private SplashFragment mFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        mManager = getSupportFragmentManager();
        mFragment = (SplashFragment) mManager.findFragmentById(R.id.fl_content);
        if (mFragment == null) {
            mFragment = SplashFragment.newInstance();
            mManager.beginTransaction().add(R.id.fl_content, mFragment).commit();
        }

        new SplashPresenter(mFragment, Injection.provideRepository(this));
        startActivity(HomeActivity.getStartIntent(this));
    }
}
