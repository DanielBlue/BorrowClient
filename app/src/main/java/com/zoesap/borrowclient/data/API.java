package com.zoesap.borrowclient.data;

import com.zoesap.borrowclient.data.bean.ChooseLoanTypeBean;
import com.zoesap.borrowclient.data.bean.LoanDetailBean;
import com.zoesap.borrowclient.data.bean.LoanListItemBean;
import com.zoesap.borrowclient.data.bean.LoanRecommendItemBean;
import com.zoesap.borrowclient.data.bean.LoginBean;

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

    interface LoanDetailService{
        @FormUrlEncoded
        @POST("search/loanshow")
        Call<LoanDetailBean> getLoanDetailBeanList(@Field("loan_id") String loanId);
    }

    interface LoginService{
        @FormUrlEncoded
        @POST("user/dologin")
        Call<LoginBean> login(@Field("account") String account,@Field("password") String password);
    }
}
