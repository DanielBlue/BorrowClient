package com.zoesap.borrowclient.splash;

import android.support.annotation.NonNull;

import com.zoesap.borrowclient.BorrowApplication;
import com.zoesap.borrowclient.R;
import com.zoesap.borrowclient.data.bean.LoginBean;
import com.zoesap.borrowclient.data.source.DataSource;
import com.zoesap.borrowclient.data.source.Repository;
import com.zoesap.borrowclient.util.NullUtils;

/**
 * Created by maoqi on 2017/7/31.
 */

public class SplashPresenter implements SplashContract.Presenter{
    private final SplashContract.View mSplashView;
    private final Repository mRepository;

    public SplashPresenter(@NonNull SplashContract.View mSplashView,@NonNull Repository mRepository) {
        this.mSplashView = NullUtils.checkNotNull(mSplashView);
        this.mRepository = NullUtils.checkNotNull(mRepository);

        mSplashView.setPresent(this);
    }


    @Override
    public void start() {
        autoLogin();
    }

    private void autoLogin() {
        String account = mRepository.getAccountFromSp();
        String password = mRepository.getPasswordFromSp();
        mRepository.login(account, password, new DataSource.LoadCallback<LoginBean>() {
            @Override
            public void onSuccessful(LoginBean loginBean) {
                if (loginBean.getCode() != 10000) {
                    mSplashView.toastInfo(R.string.login_failed);
                    mSplashView.getParentActivity().finish();
                } else {
                    BorrowApplication.getInstance().setmSignIn(true);
                }
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }
}
