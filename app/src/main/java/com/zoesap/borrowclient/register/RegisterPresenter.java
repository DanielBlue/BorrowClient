package com.zoesap.borrowclient.register;

import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.zoesap.borrowclient.R;
import com.zoesap.borrowclient.data.bean.BaseBeanWrapper;
import com.zoesap.borrowclient.data.bean.RegisterBean;
import com.zoesap.borrowclient.data.source.DataSource;
import com.zoesap.borrowclient.data.source.Repository;
import pers.maoqi.core.util.NullUtils;

/**
 * Created by maoqi on 2017/7/26.
 */

public class RegisterPresenter implements RegisterContract.Presenter {
    private final RegisterContract.View mRegisterView;
    private final Repository mRepository;
    private CountDownTimer mTimer;

    public RegisterPresenter(@NonNull RegisterContract.View mRegisterView, @NonNull Repository mRepository) {
        this.mRegisterView = NullUtils.checkNotNull(mRegisterView);
        this.mRepository = NullUtils.checkNotNull(mRepository);
        mRegisterView.setPresent(this);
    }

    @Override
    public void start() {

    }

    @Override
    public void getSmsCode(String phoneNum) {
        String telRegex = "[1][34578]\\d{9}";
        if (phoneNum.isEmpty() || "".equals(phoneNum)
                || !phoneNum.matches(telRegex)) {
            mRegisterView.toastInfo(R.string.phone_num_error);
        } else {
            mRegisterView.showLoadindDialog();
            mRepository.getApplySmsCode(phoneNum, new DataSource.LoadCallback<BaseBeanWrapper>() {
                @Override
                public void onSuccessful(BaseBeanWrapper baseBeanWrapper) {
                    if (baseBeanWrapper.getCode() == 10000) {
                        countDown();
                    }
                    mRegisterView.toastInfo(baseBeanWrapper.getInfo());
                    mRegisterView.loadingDialogDismiss();
                }

                @Override
                public void onFailure(Throwable t) {
                    mRegisterView.toastInfo(R.string.net_error);
                    mRegisterView.loadingDialogDismiss();
                }
            });
        }


    }

    private void countDown() {
        if (mTimer == null) {
            mTimer = new CountDownTimer(120000, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    mRegisterView.updateCountDownButton(String.valueOf(millisUntilFinished / 1000));
                }

                @Override
                public void onFinish() {
                    mRegisterView.countDownFinish();
                }
            };
        }
        mTimer.start();
    }

    @Override
    public void stopCountDownTimer() {
        if (mTimer != null) {
            mTimer.cancel();
        }
    }

    @Override
    public void applySubmit(final String mobileNumber, final String password, String verifyPassword, String getCode, String visitCode) {
        String telRegex = "[1][3578]\\d{9}";
        if (TextUtils.isEmpty(mobileNumber) || !mobileNumber.matches(telRegex)) {
            mRegisterView.toastInfo(R.string.phone_num_error);
            return;
        }
        String passRegex = "^([a-zA-Z0-9]{6,32})$";
        if (password.isEmpty() || "".equals(password)
                || !password.matches(passRegex)) {
            mRegisterView.toastInfo(R.string.password_error);
            return;
        }
        if (!password.equals(verifyPassword)) {
            mRegisterView.toastInfo(R.string.verify_password_error);
            return;
        }
        mRegisterView.showLoadindDialog();
        mRepository.getRegisterResult(mobileNumber, password, verifyPassword, getCode, visitCode,
                new DataSource.LoadCallback<RegisterBean>() {
                    @Override
                    public void onSuccessful(RegisterBean registerBean) {
                        if (registerBean.getCode() == 10000) {
                            mRepository.saveAccountAndPassword2Sp(mobileNumber, password);
                            mRegisterView.activityFinish();
                        }
//                        else {
//                            mRegisterView.clearInput();
//                        }
                        mRegisterView.toastInfo(registerBean.getInfo());
                        mRegisterView.loadingDialogDismiss();
                    }

                    @Override
                    public void onFailure(Throwable t) {
                        mRegisterView.toastInfo(R.string.net_error);
                        mRegisterView.loadingDialogDismiss();
                    }
                });


    }

//    private void autoLogin(String mobileNumber, final String password) {
//        mRepository.login(mobileNumber, password, new DataSource.LoadCallback<LoginBean>() {
//            @Override
//            public void onSuccessful(LoginBean loginBean) {
//                if (loginBean.getCode()==10000){
//                    BorrowApplication.getInstance().setmSignIn(true);
//                    mRegisterView.activityFinish();
//                }
//            }
//
//            @Override
//            public void onFailure(Throwable t) {
//                mRegisterView.activityFinish();
//            }
//        });
//    }
}
