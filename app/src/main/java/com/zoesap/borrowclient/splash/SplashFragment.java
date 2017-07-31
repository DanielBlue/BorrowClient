package com.zoesap.borrowclient.splash;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zoesap.borrowclient.BaseFragment;
import com.zoesap.borrowclient.R;
import com.zoesap.borrowclient.util.NullUtils;

/**
 * Created by maoqi on 2017/7/31.
 */

public class SplashFragment extends BaseFragment implements SplashContract.View {
    private SplashContract.Presenter mPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_splash,null);
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Override
    public void setPresent(@NonNull SplashContract.Presenter presenter) {
        mPresenter = NullUtils.checkNotNull(presenter);
    }

    public static SplashFragment newInstance() {
        return new SplashFragment();
    }

    @Override
    public Activity getParentActivity() {
        return getActivity();
    }
}
