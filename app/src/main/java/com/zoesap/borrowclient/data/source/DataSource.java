package com.zoesap.borrowclient.data.source;

import com.zoesap.borrowclient.data.bean.ApplyInfoBean;
import com.zoesap.borrowclient.data.bean.BaseBeanWrapper;
import com.zoesap.borrowclient.data.bean.ChooseLoanTypeBean;
import com.zoesap.borrowclient.data.bean.LoanDetailBean;
import com.zoesap.borrowclient.data.bean.LoanListItemBean;
import com.zoesap.borrowclient.data.bean.LoanRecommendItemBean;
import com.zoesap.borrowclient.data.bean.LoginBean;
import com.zoesap.borrowclient.data.bean.MyRecommendBean;

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

    void login(String account, String password, LoadCallback<LoginBean> callback);

    void getMyRecommendBean(LoadCallback<MyRecommendBean> callback);

    void getApplySmsCode(String phoneNum, LoadCallback<BaseBeanWrapper> callback);

    void getApplyInfo(String loan_name,
                      String loan_mobile,
                      String smscode,
                      String loan_money,
                      String loan_use,
                      String id,
                      LoadCallback<ApplyInfoBean> callback);

    void getApplyLoanResult(String loan_income,
                            String loan_status,
                            String loan_house,
                            String id,
                            LoadCallback<BaseBeanWrapper> callback);
}
