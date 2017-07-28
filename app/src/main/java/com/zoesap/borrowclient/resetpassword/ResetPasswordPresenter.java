package com.zoesap.borrowclient.resetpassword;

import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.zoesap.borrowclient.R;
import com.zoesap.borrowclient.data.bean.BaseBeanWrapper;
import com.zoesap.borrowclient.data.bean.ResetPasswordBean;
import com.zoesap.borrowclient.data.source.DataSource;
import com.zoesap.borrowclient.data.source.Repository;
import com.zoesap.borrowclient.util.NullUtils;

/**
 * Created by maoqi on 2017/7/27.
 */

public class ResetPasswordPresenter implements ResetPasswordContract.Presenter {
    private final ResetPasswordContract.View mResetPasswordView;
    private final Repository mRepository;
    private CountDownTimer mTimer;

    public ResetPasswordPresenter(@NonNull ResetPasswordContract.View mResetPasswordView, @NonNull Repository mRepository) {
        this.mResetPasswordView = NullUtils.checkNotNull(mResetPasswordView);
        this.mRepository = NullUtils.checkNotNull(mRepository);
        mResetPasswordView.setPresent(this);
    }

    @Override
    public void start() {
        loadAccount();
    }

    private void loadAccount() {
        String account = mRepository.getAccountFromSp();
        if (!TextUtils.isEmpty(account)) {
            mResetPasswordView.showPreInptAccount(account);
        }
    }

    @Override
    public void stopCountDownTimer() {
        if (mTimer != null) {
            mTimer.cancel();
        }
    }

    @Override
    public void getSmsCode(String phoneNum) {
        String telRegex = "[1][34578]\\d{9}";
        if (phoneNum.isEmpty() || "".equals(phoneNum)
                || !phoneNum.matches(telRegex)) {
            mResetPasswordView.toastInfo(R.string.phone_num_error);
        } else {
            mResetPasswordView.showLoadindDialog();
            mRepository.getApplySmsCode(phoneNum, new DataSource.LoadCallback<BaseBeanWrapper>() {
                @Override
                public void onSuccessful(BaseBeanWrapper baseBeanWrapper) {
                    if (baseBeanWrapper.getCode() == 10000) {
                        countDown();
                    }
                    mResetPasswordView.toastInfo(baseBeanWrapper.getInfo());
                    mResetPasswordView.loadingDialogDismiss();
                }

                @Override
                public void onFailure(Throwable t) {
                    mResetPasswordView.toastInfo(R.string.net_error);
                    mResetPasswordView.loadingDialogDismiss();
                }
            });
        }
    }

    @Override
    public void submit(final String phoneNumber, String code, final String newPassword, String confirmPassword) {
        String telRegex = "[1][3578]\\d{9}";
        if (TextUtils.isEmpty(phoneNumber) || !phoneNumber.matches(telRegex)) {
            mResetPasswordView.toastInfo(R.string.phone_num_error);
            return;
        }
        if (TextUtils.isEmpty(newPassword)) {
            mResetPasswordView.toastInfo(R.string.password_empty);
            return;
        }
        String passRegex = "^([a-zA-Z0-9]{6,32})$";
        if (!newPassword.matches(passRegex)) {
            mResetPasswordView.toastInfo(R.string.password_error);
            return;
        }
        if (!newPassword.equals(confirmPassword)) {
            mResetPasswordView.toastInfo(R.string.verify_password_error);
            return;
        }
        mResetPasswordView.showLoadindDialog();
        mRepository.getResetPasswordResult(phoneNumber, code, newPassword, confirmPassword,
                new DataSource.LoadCallback<ResetPasswordBean>() {
                    @Override
                    public void onSuccessful(ResetPasswordBean resetPasswordBean) {
                        if (resetPasswordBean.getCode()==10000){
                            mRepository.saveAccountAndPassword2Sp(phoneNumber,newPassword);
                            mResetPasswordView.activityFinish();
                        }
                        mResetPasswordView.toastInfo(resetPasswordBean.getInfo());
                        mResetPasswordView.loadingDialogDismiss();
                    }

                    @Override
                    public void onFailure(Throwable t) {
                        mResetPasswordView.toastInfo(R.string.net_error);
                        mResetPasswordView.loadingDialogDismiss();
                    }
                });

    }

    private void countDown() {
        if (mTimer == null) {
            mTimer = new CountDownTimer(120000, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    mResetPasswordView.updateCountDownButton(String.valueOf(millisUntilFinished / 1000));
                }

                @Override
                public void onFinish() {
                    mResetPasswordView.countDownFinish();
                }
            };
        }
        mTimer.start();
    }
}
