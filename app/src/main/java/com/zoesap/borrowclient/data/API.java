package com.zoesap.borrowclient.data;

import com.zoesap.borrowclient.data.bean.ApplyInfoBean;
import com.zoesap.borrowclient.data.bean.ApplyQualificationBean;
import com.zoesap.borrowclient.data.bean.BaseBeanWrapper;
import com.zoesap.borrowclient.data.bean.ChooseLoanTypeBean;
import com.zoesap.borrowclient.data.bean.LoanDetailBean;
import com.zoesap.borrowclient.data.bean.LoanListItemBean;
import com.zoesap.borrowclient.data.bean.LoanRecommendItemBean;
import com.zoesap.borrowclient.data.bean.LoginBean;
import com.zoesap.borrowclient.data.bean.MyLoanBean;
import com.zoesap.borrowclient.data.bean.MyRecommendBean;
import com.zoesap.borrowclient.data.bean.RegisterBean;
import com.zoesap.borrowclient.data.bean.ResetPasswordBean;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by maoqi on 2017/7/18.
 */

public interface API {
    interface LoanRecommendListService {
        @GET("search/index")
        Call<LoanRecommendItemBean> getLoanRecommendList();
    }

    interface LoanTypeService {
        @POST("search/screen")
        Call<ChooseLoanTypeBean> getLoanTypeList();
    }

    interface LoanListService {
        @FormUrlEncoded
        @POST("search/loanlist")
        Call<LoanListItemBean> getLoanList(@Field("loantype") int loantype,
                                           @Field("career") int career,
                                           @Field("credit") int credit,
                                           @Field("house") int house,
                                           @Field("page") int page);
    }

    interface LoanDetailService {
        @FormUrlEncoded
        @POST("search/loanshow")
        Call<LoanDetailBean> getLoanDetailBeanList(@Field("loan_id") String loanId);
    }

    interface LoginService {
        @FormUrlEncoded
        @POST("user/dologin")
        Call<LoginBean> login(@Field("account") String account, @Field("password") String password);
    }

    interface MyLoanService {
        @FormUrlEncoded
        @POST("centre/info")
        Call<MyLoanBean> loadMyLoanList(@Field("uid") String token);

        @FormUrlEncoded
        @POST("centre/cancelloan")
        Call<BaseBeanWrapper> cancelRequest(@Field("id") String id, @Field("uid") String token);
    }

    interface MyRecommendService {
        @POST("centre/recommend")
        Call<MyRecommendBean> loadMyRecommendBean();
    }

    interface ApplyInfoService {
        @FormUrlEncoded
        @POST("User/publicsms")
        Call<BaseBeanWrapper> getSmsCode(@Field("mobile") String mobile);

        @FormUrlEncoded
        @POST("search/applyOne")
        Call<ApplyInfoBean> getApplyInfoBean(@Field("loan_name") String loan_name,
                                             @Field("loan_mobile") String loan_mobile,
                                             @Field("smscode") String smscode,
                                             @Field("loan_money") String loan_money,
                                             @Field("loan_use") String loan_use,
                                             @Field("id") String id);
    }

    interface ApplyQualificationService {
        @FormUrlEncoded
        @POST("search/apply")
        Call<ApplyQualificationBean> getApplyResult(@Field("loan_income") String loan_income,
                                                                     @Field("loan_status") String loan_status,
                                                                     @Field("loan_house") String loan_house,
                                                                     @Field("id") String id);
    }

    interface RegisterService{
        @FormUrlEncoded
        @POST("user/regmobile")
        Call<RegisterBean> register(@Field("mobile") String mobile,
                                    @Field("name") String name,
                                    @Field("smscode") String smscode,
                                    @Field("password") String password,
                                    @Field("password_confirm") String password_confirm,
                                    @Field("incode") String incode);
    }

    interface ResetPasswordService {
        @FormUrlEncoded
        @POST("user/setpwd")
        Call<ResetPasswordBean> resetPassword(@Field("mobile") String mobile,
                                              @Field("resms") String resms,
                                              @Field("password") String password,
                                              @Field("checkpwd") String checkpwd);
    }
}
