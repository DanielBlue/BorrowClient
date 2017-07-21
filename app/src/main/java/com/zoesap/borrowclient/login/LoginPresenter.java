package com.zoesap.borrowclient.login;

import com.zoesap.borrowclient.BorrowApplication;
import com.zoesap.borrowclient.data.bean.LoginBean;
import com.zoesap.borrowclient.data.source.DataSource;
import com.zoesap.borrowclient.data.source.Repository;
import com.zoesap.borrowclient.util.NullUtils;

/**
 * Created by maoqi on 2017/7/20.
 */

public class LoginPresenter implements LoginContract.Presenter {
    private final LoginContract.View mLoginView;
    private final Repository mRepository;

    public LoginPresenter(LoginContract.View mLoginView, Repository mRepository) {
        this.mLoginView = NullUtils.checkNotNull(mLoginView);
        this.mRepository = NullUtils.checkNotNull(mRepository);
        mLoginView.setPresent(this);
    }

    @Override
    public void start() {
        loadAccount();
    }

    private void loadAccount() {
        mLoginView.showPreInptAccount(mRepository.getAccountFromSp());
    }

    @Override
    public void login(final String account, final String password) {
        mLoginView.showLoadindDialog();
        mRepository.login(account, password, new DataSource.LoadCallback<LoginBean>() {
            @Override
            public void onSuccessful(LoginBean loginBean) {
                if (loginBean.getCode() == 10000) {
                    BorrowApplication.getInstance().setmSignIn(true);
                    mRepository.saveAccountAndPassword2Sp(account, password);
                    mRepository.saveUid2Sp(loginBean.getData().getInfo().getUid());
                    mLoginView.finish();
                } else {
                    mLoginView.showToast(loginBean.getInfo());
                }
                mLoginView.loadingDialogDismiss();
            }

            @Override
            public void onFailure() {
                mLoginView.showNetError();
                mLoginView.loadingDialogDismiss();
            }
        });
    }
}
