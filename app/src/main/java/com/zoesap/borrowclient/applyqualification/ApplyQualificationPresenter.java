package com.zoesap.borrowclient.applyqualification;

import android.support.annotation.NonNull;

import com.zoesap.borrowclient.R;
import com.zoesap.borrowclient.data.bean.BaseBeanWrapper;
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
    public void submit2Server(String mCurrentIncome, String mCurrentJob, String mCurrentHouse, String loanId) {
        mApplyResultView.showLoadindDialog();
        mRepository.getApplyLoanResult(mCurrentIncome, mCurrentJob, mCurrentHouse, loanId,
                new DataSource.LoadCallback<BaseBeanWrapper>() {
                    @Override
                    public void onSuccessful(BaseBeanWrapper baseBeanWrapper) {
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
                        mApplyResultView.toastInfo(t.getMessage());
                        mApplyResultView.loadingDialogDismiss();
                    }
                });
    }
}
