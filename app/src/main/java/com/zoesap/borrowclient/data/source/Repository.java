package com.zoesap.borrowclient.data.source;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.zoesap.borrowclient.data.bean.ApplyInfoBean;
import com.zoesap.borrowclient.data.bean.ApplyQualificationBean;
import com.zoesap.borrowclient.data.bean.BaseBeanWrapper;
import com.zoesap.borrowclient.data.bean.ChooseLoanTypeBean;
import com.zoesap.borrowclient.data.bean.LoanDetailBean;
import com.zoesap.borrowclient.data.bean.LoanListItemBean;
import com.zoesap.borrowclient.data.bean.LoanRecommendItemBean;
import com.zoesap.borrowclient.data.bean.LoginBean;
import com.zoesap.borrowclient.data.bean.MyRecommendBean;
import com.zoesap.borrowclient.data.bean.RegisterBean;
import com.zoesap.borrowclient.data.bean.ResetPasswordBean;
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

    public void loadMyLoanList(LoadCallback<List<MultiItemEntity>> callback) {
        String token = getUidFromSp();
        mRemoteDataSource.loadMyLoanList(token,callback);
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

    @Override
    public void getMyRecommendBean(LoadCallback<MyRecommendBean> callback) {
        mRemoteDataSource.getMyRecommendBean(callback);
    }

    @Override
    public void getApplySmsCode(String phoneNum, LoadCallback<BaseBeanWrapper> callback) {
        mRemoteDataSource.getApplySmsCode(phoneNum,callback);
    }

    @Override
    public void getApplyInfo(String loan_name, String loan_mobile, String smscode,
                             String loan_money, String loan_use, String id,
                             LoadCallback<ApplyInfoBean> callback) {
        mRemoteDataSource.getApplyInfo(loan_name,loan_mobile,smscode,loan_money,loan_use,id,callback);
    }

    @Override
    public void getApplyLoanResult(String loan_income, String loan_status, String loan_house,
                                   String id,LoadCallback<ApplyQualificationBean> callback) {
        mRemoteDataSource.getApplyLoanResult(loan_income, loan_status, loan_house, id,callback);
    }

    @Override
    public void getRegisterResult(String mobileNumber, String password, String verifyPassword, String getCode, String visitCode, LoadCallback<RegisterBean> callback) {
        mRemoteDataSource.getRegisterResult(mobileNumber, password, verifyPassword, getCode, visitCode,callback);
    }

    @Override
    public void getResetPasswordResult(String mobile, String resms, String password, String checkpwd, LoadCallback<ResetPasswordBean> callback) {
        mRemoteDataSource.getResetPasswordResult(mobile, resms, password, checkpwd, callback);
    }

    public void cancelMyLoanRequest(String id, LoadCallback<BaseBeanWrapper> callback) {
        String token = getUidFromSp();
        mRemoteDataSource.cancelMyLoanRequest(id,token,callback);
    }


    public String getAccountFromSp(){
        return mHelper.getString(LoginContract.Constants.KEY_ACCOUNT, "");
    }

    public String getPasswordFromSp(){
        return mHelper.getString(LoginContract.Constants.KEY_PASSWORD,"");
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

    public void savePassword2Sp(String password) {
        mHelper.putString(LoginContract.Constants.KEY_PASSWORD,password);
    }
}
