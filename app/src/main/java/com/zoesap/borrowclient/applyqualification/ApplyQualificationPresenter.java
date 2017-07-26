package com.zoesap.borrowclient.applyqualification;

import android.support.annotation.NonNull;

import com.zoesap.borrowclient.R;
import com.zoesap.borrowclient.data.bean.ApplyQualificationBean;
import com.zoesap.borrowclient.data.source.DataSource;
import com.zoesap.borrowclient.data.source.Repository;
import com.zoesap.borrowclient.util.NullUtils;

/**
 * Created by maoqi on 2017/7/25.
 */

public class ApplyQualificationPresenter implements ApplyQualificationContract.Presenter {
    private final ApplyQualificationContract.View mApplyResultView;
    private final Repository mRepository;

    public ApplyQualificationPresenter(@NonNull ApplyQualificationFragment mView, @NonNull Repository repository) {
        mApplyResultView = NullUtils.checkNotNull(mView);
        mRepository = NullUtils.checkNotNull(repository);
        mView.setPresent(this);
    }

    @Override
    public void start() {

    }

    @Override
    public void submit2Server(String mCurrentIncome, String mCurrentJob, String mCurrentHouse, String applyInfoId) {
        mApplyResultView.showLoadindDialog();
        mRepository.getApplyLoanResult(mCurrentIncome, mCurrentJob, mCurrentHouse, applyInfoId,
                new DataSource.LoadCallback<ApplyQualificationBean>() {
                    @Override
                    public void onSuccessful(ApplyQualificationBean baseBeanWrapper) {
                        if (baseBeanWrapper.getCode()==10000) {
                            mApplyResultView.toastInfo(mApplyResultView.getContext().getString(R.string.apply_successful));
                            mApplyResultView.change2NextPage();
                        }else {
                            mApplyResultView.toastInfo(mApplyResultView.getContext().getString(R.string.apply_failed));
                        }
                        mApplyResultView.loadingDialogDismiss();
                    }

                    @Override
                    public void onFailure(Throwable t) {
                        mApplyResultView.toastInfo(R.string.net_error);
                        mApplyResultView.loadingDialogDismiss();
                    }
                });
    }
}
