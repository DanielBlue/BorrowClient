package com.zoesap.borrowclient.splash;

import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.zoesap.borrowclient.BorrowApplication;
import com.zoesap.borrowclient.data.bean.LoginBean;
import com.zoesap.borrowclient.data.source.DataSource;
import com.zoesap.borrowclient.data.source.Repository;
import pers.maoqi.core.util.NullUtils;

/**
 * Created by maoqi on 2017/7/31.
 */

public class SplashPresenter implements SplashContract.Presenter {
    private final SplashContract.View mSplashView;
    private final Repository mRepository;

    public SplashPresenter(@NonNull SplashContract.View mSplashView, @NonNull Repository mRepository) {
        this.mSplashView = NullUtils.checkNotNull(mSplashView);
        this.mRepository = NullUtils.checkNotNull(mRepository);

        mSplashView.setPresent(this);
    }


    @Override
    public void start() {
        String account = mRepository.getAccountFromSp();
        String password = mRepository.getPasswordFromSp();
        if (!TextUtils.isEmpty(account) && !TextUtils.isEmpty(password)) {
            autoLogin(account, password);
        } else {
            mSplashView.getParentActivity().finish();
        }
    }

    private void autoLogin(String account, String password) {
        mRepository.login(account, password, new DataSource.LoadCallback<LoginBean>() {
            @Override
            public void onSuccessful(LoginBean loginBean) {
                if (loginBean.getCode() != 10000) {
                    mSplashView.toastInfo(loginBean.getInfo());
                } else {
                    BorrowApplication.getInstance().setmSignIn(true);
                }
                mSplashView.getParentActivity().finish();
            }

            @Override
            public void onFailure(Throwable t) {
                mSplashView.getParentActivity().finish();
            }
        });
    }
}
