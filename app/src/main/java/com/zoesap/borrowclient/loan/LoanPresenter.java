package com.zoesap.borrowclient.loan;

import android.util.Log;

import com.zoesap.borrowclient.data.bean.ChooseLoanTypeBean;
import com.zoesap.borrowclient.data.bean.LoanListItemBean;
import com.zoesap.borrowclient.data.source.DataSource;
import com.zoesap.borrowclient.data.source.Repository;
import com.zoesap.borrowclient.util.NullUtils;

/**
 * Created by maoqi on 2017/7/18.
 */

public class LoanPresenter implements LoanContract.Presenter {
    private final LoanContract.View mLoanView;
    private final Repository mRepository;
    private int mCurrentPage = 1;

    public LoanPresenter(LoanContract.View mLoanView, Repository mRepository) {
        this.mLoanView = NullUtils.checkNotNull(mLoanView);
        this.mRepository = NullUtils.checkNotNull(mRepository);
        mLoanView.setPresent(this);
    }

    @Override
    public void start() {
        loadChooseLoanBeanList();
        loadLoanBeanList();
    }

    @Override
    public void loadChooseLoanBeanList() {
        mRepository.getChooseLoanTypeBeanList(new DataSource.LoadCallback<ChooseLoanTypeBean.DataBean>() {
            @Override
            public void onSuccessful(ChooseLoanTypeBean.DataBean dataBean) {
                mLoanView.initPopupWindow(dataBean);
            }

            @Override
            public void onFailure(Throwable t) {
                mLoanView.toastInfo(t.getMessage());
            }
        });
    }

    @Override
    public void loadLoanBeanList() {
        mLoanView.showLoadindDialog();
        mRepository.getLoanList(0, 0, 0, 0, 1, new DataSource.LoadCallback<LoanListItemBean.DataBean>() {
            @Override
            public void onSuccessful(LoanListItemBean.DataBean dataBean) {
                if (dataBean.getList() != null && dataBean.getList().size() > 0) {
                    mLoanView.showList();
                    mLoanView.loadingDialogDismiss();
                    mLoanView.initLoanList(dataBean);
                    if (dataBean.getPagetotal() <= 1) {
                        mLoanView.setEnableLoadMore(false);
                    }
                } else {
                    mLoanView.showNoData();
                }
            }

            @Override
            public void onFailure(Throwable t) {
                mLoanView.toastInfo(t.getMessage());
                mLoanView.loadingDialogDismiss();
            }
        });
    }

    @Override
    public void refreshBeanList(int loantype,
                                int career,
                                int credit,
                                int house) {
        Log.e("ChooseLoanPresenter", "refreshBeanList(ChooseLoanPresenter.java:76)" + loantype + career + credit + house);
        mLoanView.setEnableLoadMore(false);
        mRepository.getLoanList(loantype, career, credit, house, 1,
                new DataSource.LoadCallback<LoanListItemBean.DataBean>() {
                    @Override
                    public void onSuccessful(LoanListItemBean.DataBean dataBean) {
                        if (dataBean.getList() != null && dataBean.getList().size() > 0) {
                            mLoanView.showList();
                            mLoanView.setNewData(dataBean);
                            mLoanView.setRefreshing(false);
                            if (dataBean.getPagetotal() <= 1) {
                                mLoanView.setEnableLoadMore(false);
                            }
                        } else {
                            mLoanView.showNoData();
                        }
                    }

                    @Override
                    public void onFailure(Throwable t) {
                        mLoanView.toastInfo(t.getMessage());
                        mLoanView.setRefreshing(false);
                    }
                });
    }

    @Override
    public void loadMoreBeanList(int loantype,
                                 int career,
                                 int credit,
                                 int house,
                                 final LoanContract.ListLoadMoreListener listener) {
        mLoanView.setLoadMoreStatus(true);
        mRepository.getLoanList(loantype, career, credit, house, ++mCurrentPage, new DataSource.LoadCallback<LoanListItemBean.DataBean>() {
            @Override
            public void onSuccessful(LoanListItemBean.DataBean dataBean) {
                if (mCurrentPage >= dataBean.getPagetotal()) {
                    listener.loadMoreEnd(dataBean);
                } else {
                    listener.loadMoreComplete(dataBean);
                }
                mLoanView.setLoadMoreStatus(false);
            }

            @Override
            public void onFailure(Throwable t) {
                mLoanView.toastInfo(t.getMessage());
                listener.loadMoreFail();
                mLoanView.setLoadMoreStatus(false);
            }
        });
    }
}
