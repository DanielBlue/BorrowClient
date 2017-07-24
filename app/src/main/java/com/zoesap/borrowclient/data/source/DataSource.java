package com.zoesap.borrowclient.data.source;

import com.zoesap.borrowclient.data.bean.ChooseLoanTypeBean;
import com.zoesap.borrowclient.data.bean.LoanDetailBean;
import com.zoesap.borrowclient.data.bean.LoanListItemBean;
import com.zoesap.borrowclient.data.bean.LoanRecommendItemBean;
import com.zoesap.borrowclient.data.bean.LoginBean;

import java.util.List;

/**
 * Created by maoqi on 2017/7/18.
 */

public interface DataSource {

    interface LoadCallback<T> {
        void onSuccessful(T t);
        void onFailure(Throwable t);
    }

    void getRecommendedLoanItem(LoadCallback<List<LoanRecommendItemBean.DataBean.ListBean>> callback);

    void getChooseLoanTypeBeanList(LoadCallback<ChooseLoanTypeBean.DataBean> callback);

    void getLoanList(int loantype,
                     int career,
                     int credit,
                     int house,
                     int page,
                     LoadCallback<LoanListItemBean.DataBean> callback);

    void getLoanDetailBean(String loanId, LoadCallback<LoanDetailBean.DataBean> callback);

    void login(String account,String password,LoadCallback<LoginBean> callback);
}
