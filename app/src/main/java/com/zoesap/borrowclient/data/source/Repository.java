package com.zoesap.borrowclient.data.source;

import com.zoesap.borrowclient.data.bean.ChooseLoanTypeBean;
import com.zoesap.borrowclient.data.bean.LoanDetailBean;
import com.zoesap.borrowclient.data.bean.LoanListItemBean;
import com.zoesap.borrowclient.data.bean.LoanRecommendItemBean;
import com.zoesap.borrowclient.data.bean.LoginBean;
import com.zoesap.borrowclient.data.source.remote.RemoteDataSource;
import com.zoesap.borrowclient.data.source.sp.SpHelper;
import com.zoesap.borrowclient.login.LoginContract;

import java.util.List;

/**
 * Created by maoqi on 2017/7/18.
 */

public class Repository implements DataSource {
    private static Repository INSTANCE = null;
    private final RemoteDataSource mRemoteDataSource;
    private final SpHelper mHelper;

    private Repository(RemoteDataSource mRemoteDataSource,SpHelper mHelper) {
        this.mRemoteDataSource = mRemoteDataSource;
        this.mHelper = mHelper;
    }

    public static Repository getInstance(RemoteDataSource mRemoteDataSource,SpHelper mHelper) {
        if (INSTANCE == null) {
            INSTANCE = new Repository(mRemoteDataSource,mHelper);
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }


    @Override
    public void getRecommendedLoanItem(LoadCallback<List<LoanRecommendItemBean.DataBean.ListBean>> callback) {
        mRemoteDataSource.getRecommendedLoanItem(callback);
    }

    @Override
    public void getChooseLoanTypeBeanList(LoadCallback<ChooseLoanTypeBean.DataBean> callback) {
        mRemoteDataSource.getChooseLoanTypeBeanList(callback);
    }

    @Override
    public void getLoanList(int loantype,
                            int career,
                            int credit,
                            int house,
                            int page,
                            LoadCallback<LoanListItemBean.DataBean> callback) {
        mRemoteDataSource.getLoanList(loantype, career, credit, house, page, callback);
    }

    @Override
    public void getLoanDetailBean(String loanId, LoadCallback<LoanDetailBean.DataBean> callback) {
        mRemoteDataSource.getLoanDetailBean(loanId,callback);
    }

    @Override
    public void login(String account, String password, LoadCallback<LoginBean> callback) {
        mRemoteDataSource.login(account,password,callback);
    }

    public String getAccountFromSp(){
        return mHelper.getString(LoginContract.Constants.KEY_ACCOUNT, "");
    }

    public void saveAccountAndPassword2Sp(String account, String password) {
        mHelper.putString(LoginContract.Constants.KEY_ACCOUNT,account);
        mHelper.putString(LoginContract.Constants.KEY_PASSWORD,password);
    }

    public String getUidFromSp(){
        return mHelper.getString(LoginContract.Constants.KEY_UID, "");
    }

    public void saveUid2Sp(String uid) {
        mHelper.putString(LoginContract.Constants.KEY_UID,uid);
    }
}
