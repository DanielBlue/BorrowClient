package com.zoesap.borrowclient.login;

import android.net.ParseException;
import android.text.TextUtils;

import com.google.gson.JsonParseException;
import com.zoesap.borrowclient.BorrowApplication;
import com.zoesap.borrowclient.R;
import com.zoesap.borrowclient.data.bean.LoginBean;
import com.zoesap.borrowclient.data.source.DataSource;
import com.zoesap.borrowclient.data.source.Repository;
import com.zoesap.borrowclient.util.NullUtils;

import org.json.JSONException;

import java.net.ConnectException;
import java.net.UnknownHostException;

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
        if (BorrowApplication.getInstance().ismSignIn()) {
            mLoginView.activityFinish();
        } else {
            loadAccount();
        }
    }

    private void loadAccount() {
        String account = mRepository.getAccountFromSp();
        if (!TextUtils.isEmpty(account)) {
            mLoginView.showPreInptAccount(account);
        }
    }

    @Override
    public void login(final String account, final String password) {
        String telRegex = "[1][3578]\\d{9}";
        if (TextUtils.isEmpty(account) || TextUtils.isEmpty(password)) {
            mLoginView.toastInfo(R.string.phone_num_or_password_empty);
            return;
        }
        if (!account.matches(telRegex)) {
            mLoginView.toastInfo(R.string.phone_num_error);
            return;
        }

        mLoginView.showLoadindDialog();
        mRepository.login(account, password, new DataSource.LoadCallback<LoginBean>() {
            @Override
            public void onSuccessful(LoginBean loginBean) {
                if (loginBean.getCode() == 10000) {
                    BorrowApplication.getInstance().setmSignIn(true);
                    mRepository.saveAccountAndPassword2Sp(account, password);
                    mRepository.saveUid2Sp(loginBean.getData().getInfo().getUid());
                    mLoginView.activityFinish();
                } else {
                    mLoginView.showToast(loginBean.getInfo());
                }
                mLoginView.loadingDialogDismiss();
            }

            @Override
            public void onFailure(Throwable t) {
                if (t instanceof JsonParseException
                        || t instanceof JSONException
                        || t instanceof ParseException) {
                mLoginView.toastInfo(R.string.data_errror);
                } else if (t instanceof UnknownHostException
                        || t instanceof ConnectException) {
                    mLoginView.toastInfo(R.string.net_error);
                } else {
                    mLoginView.toastInfo(R.string.unknow_error);
                }
                mLoginView.loadingDialogDismiss();
            }
        });
    }
}
