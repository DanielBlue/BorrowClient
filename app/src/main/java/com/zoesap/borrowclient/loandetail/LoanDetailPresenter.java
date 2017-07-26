package com.zoesap.borrowclient.loandetail;

import com.zoesap.borrowclient.R;
import com.zoesap.borrowclient.data.bean.LoanDetailBean;
import com.zoesap.borrowclient.data.source.DataSource;
import com.zoesap.borrowclient.data.source.Repository;
import com.zoesap.borrowclient.loandetail.LoanDetailContract.Presenter;
import com.zoesap.borrowclient.util.NullUtils;

/**
 * Created by maoqi on 2017/7/20.
 */

public class LoanDetailPresenter implements Presenter {
    private final LoanDetailContract.View mLoanDetailView;
    private final Repository mRepository;
    private final String loanId;

    public LoanDetailPresenter(LoanDetailContract.View mLoanDetailView, Repository mRepository,String loanId) {
        this.mLoanDetailView = NullUtils.checkNotNull(mLoanDetailView);
        this.mRepository = NullUtils.checkNotNull(mRepository);
        this.loanId = NullUtils.checkNotNull(loanId);
        mLoanDetailView.setPresent(this);
    }

    @Override
    public void start() {
        loadLoanDetailBean();
    }

    private void loadLoanDetailBean() {
        mLoanDetailView.showLoadindDialog();
        mRepository.getLoanDetailBean(loanId, new DataSource.LoadCallback<LoanDetailBean.DataBean>() {
            @Override
            public void onSuccessful(LoanDetailBean.DataBean dataBean) {
                mLoanDetailView.updateView(dataBean);
                mLoanDetailView.loadingDialogDismiss();
            }

            @Override
            public void onFailure(Throwable t) {
                mLoanDetailView.toastInfo(R.string.net_error);
                mLoanDetailView.loadingDialogDismiss();
            }
        });
    }
}
