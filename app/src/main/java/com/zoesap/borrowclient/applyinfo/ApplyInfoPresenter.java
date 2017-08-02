package com.zoesap.borrowclient.applyinfo;

import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.zoesap.borrowclient.R;
import com.zoesap.borrowclient.data.bean.ApplyInfoBean;
import com.zoesap.borrowclient.data.bean.BaseBeanWrapper;
import com.zoesap.borrowclient.data.source.DataSource;
import com.zoesap.borrowclient.data.source.Repository;
import pers.maoqi.core.util.NullUtils;

/**
 * Created by maoqi on 2017/7/25.
 */

public class ApplyInfoPresenter implements ApplyInfoContract.Presenter {
    private final ApplyInfoContract.View mApplyInfoView;
    private final Repository mRepository;
    private CountDownTimer mTimer;

    public ApplyInfoPresenter(@NonNull ApplyInfoFragment mApplyInfoView, @NonNull Repository mRepository) {
        this.mApplyInfoView = NullUtils.checkNotNull(mApplyInfoView);
        this.mRepository = NullUtils.checkNotNull(mRepository);
        mApplyInfoView.setPresent(this);
    }

    @Override
    public void start() {

    }

    @Override
    public void getSmsCode(String phoneNum) {
        String telRegex = "[1][34578]\\d{9}";
        if (phoneNum.isEmpty() || "".equals(phoneNum)
                || !phoneNum.matches(telRegex)) {
            mApplyInfoView.toastInfo(mApplyInfoView.getContext().getString(R.string.phone_num_error));
        } else {
            mApplyInfoView.showLoadindDialog();
            mRepository.getApplySmsCode(phoneNum, new DataSource.LoadCallback<BaseBeanWrapper>() {
                @Override
                public void onSuccessful(BaseBeanWrapper baseBeanWrapper) {
                    if (baseBeanWrapper.getCode() == 10000) {
                        countDown();
                    }
                    mApplyInfoView.toastInfo(baseBeanWrapper.getInfo());
                    mApplyInfoView.loadingDialogDismiss();
                }

                @Override
                public void onFailure(Throwable t) {
                    mApplyInfoView.toastInfo(R.string.net_error);
                    mApplyInfoView.loadingDialogDismiss();
                }
            });
        }
    }

    @Override
    public void applySubmit(String name, String phonenum, String smscode, String money, String use, String loanId) {
        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(phonenum)
                || TextUtils.isEmpty(smscode) || TextUtils.isEmpty(money)
                || TextUtils.isEmpty(use) || TextUtils.isEmpty(loanId)) {
            mApplyInfoView.toastInfo(mApplyInfoView.getContext().getString(R.string.input_empty));
        } else {
            mApplyInfoView.showLoadindDialog();
            mRepository.getApplyInfo(name, phonenum, smscode, money, use, loanId,
                    new DataSource.LoadCallback<ApplyInfoBean>() {
                        @Override
                        public void onSuccessful(ApplyInfoBean applyInfoBeanBaseBeanWrapper) {
                            if (applyInfoBeanBaseBeanWrapper.getCode() == 10000) {
                                mApplyInfoView.setApplyInfoId(applyInfoBeanBaseBeanWrapper.getData().getId());
                                mApplyInfoView.change2NextPage();
                            } else {
                                mApplyInfoView.toastInfo(applyInfoBeanBaseBeanWrapper.getInfo());
                            }
                            mApplyInfoView.loadingDialogDismiss();
                        }

                        @Override
                        public void onFailure(Throwable t) {
                            mApplyInfoView.toastInfo(R.string.net_error);
                            mApplyInfoView.loadingDialogDismiss();
                        }
                    });
        }
    }

    @Override
    public void stopCountDownTimer() {
        if (mTimer != null) {
            mTimer.cancel();
        }
    }

    private void countDown() {
        mTimer = new CountDownTimer(120000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mApplyInfoView.updateCountDownButton(String.valueOf(millisUntilFinished / 1000));
            }

            @Override
            public void onFinish() {
                mApplyInfoView.countDownFinish();
            }
        };
        mTimer.start();
    }


}
