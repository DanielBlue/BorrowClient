package com.zoesap.borrowclient.chooseloan;

import android.util.Log;

import com.zoesap.borrowclient.R;
import com.zoesap.borrowclient.data.bean.ChooseLoanTypeBean;
import com.zoesap.borrowclient.data.bean.LoanListItemBean;
import com.zoesap.borrowclient.data.source.DataSource;
import com.zoesap.borrowclient.data.source.Repository;
import pers.maoqi.core.util.NullUtils;

/**
 * Created by maoqi on 2017/7/19.
 */

public class ChooseLoanPresenter implements ChooseLoanContract.Presenter {
    private final ChooseLoanContract.View mChooseLoanView;
    private final Repository mRepository;
    private int mCurrentPage = 1;

    public ChooseLoanPresenter(ChooseLoanContract.View mChooseLoanView, Repository mRepository) {
        this.mChooseLoanView = NullUtils.checkNotNull(mChooseLoanView);
        this.mRepository = NullUtils.checkNotNull(mRepository);
        mChooseLoanView.setPresent(this);
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
                mChooseLoanView.initPopupWindow(dataBean);
            }

            @Override
            public void onFailure(Throwable t) {
                mChooseLoanView.toastInfo(R.string.net_error);
            }
        });
    }

    @Override
    public void loadLoanBeanList() {
        mChooseLoanView.showLoadindDialog();
        mRepository.getLoanList(0, 0, 0, 0, 1, new DataSource.LoadCallback<LoanListItemBean.DataBean>() {
            @Override
            public void onSuccessful(LoanListItemBean.DataBean dataBean) {
                if (dataBean.getList() != null && dataBean.getList().size() > 0) {
                    mChooseLoanView.showList();
                    mChooseLoanView.loadingDialogDismiss();
                    mChooseLoanView.initLoanList(dataBean);
                    if (dataBean.getPagetotal() <= 1) {
                        mChooseLoanView.setEnableLoadMore(false);
                    }
                } else {
                    mChooseLoanView.showNoData();
                }
            }

            @Override
            public void onFailure(Throwable t) {
                mChooseLoanView.toastInfo(R.string.net_error);
                mChooseLoanView.loadingDialogDismiss();
            }
        });
    }

    @Override
    public void refreshBeanList(int loantype,
                                int career,
                                int credit,
                                int house) {
        Log.e("ChooseLoanPresenter","refreshBeanList(ChooseLoanPresenter.java:76)"+loantype+career+credit+house);
        mChooseLoanView.setEnableLoadMore(false);
        mRepository.getLoanList(loantype, career, credit, house, 1,
                new DataSource.LoadCallback<LoanListItemBean.DataBean>() {
                    @Override
                    public void onSuccessful(LoanListItemBean.DataBean dataBean) {
                        if (dataBean.getList() != null && dataBean.getList().size() > 0) {
                            mChooseLoanView.showList();
                            mChooseLoanView.setNewData(dataBean);
                            mChooseLoanView.setRefreshing(false);
                            if (dataBean.getPagetotal() <= 1) {
                                mChooseLoanView.setEnableLoadMore(false);
                            }
                        }else {
                            mChooseLoanView.showNoData();
                        }
                    }

                    @Override
                    public void onFailure(Throwable t) {
                        mChooseLoanView.toastInfo(R.string.net_error);
                        mChooseLoanView.setRefreshing(false);
                    }
                });
    }

    @Override
    public void loadMoreBeanList(int loantype,
                                 int career,
                                 int credit,
                                 int house,
                                 final ChooseLoanContract.ListLoadMoreListener listener) {
        mChooseLoanView.setLoadMoreStatus(true);
        mRepository.getLoanList(loantype, career, credit, house, ++mCurrentPage, new DataSource.LoadCallback<LoanListItemBean.DataBean>() {
            @Override
            public void onSuccessful(LoanListItemBean.DataBean dataBean) {
                if (mCurrentPage >= dataBean.getPagetotal()) {
                    listener.loadMoreEnd(dataBean);
                } else {
                    listener.loadMoreComplete(dataBean);
                }
                mChooseLoanView.setLoadMoreStatus(false);
            }

            @Override
            public void onFailure(Throwable t) {
                mChooseLoanView.toastInfo(R.string.net_error);
                listener.loadMoreFail();
                mChooseLoanView.setLoadMoreStatus(false);
            }
        });
    }
}
