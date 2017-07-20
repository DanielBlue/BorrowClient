package com.zoesap.borrowclient.data.source.remote;

import android.content.Context;

import com.zoesap.borrowclient.Constants;
import com.zoesap.borrowclient.data.API;
import com.zoesap.borrowclient.data.bean.ChooseLoanTypeBean;
import com.zoesap.borrowclient.data.bean.LoanDetailBean;
import com.zoesap.borrowclient.data.bean.LoanListItemBean;
import com.zoesap.borrowclient.data.bean.LoanRecommendItemBean;
import com.zoesap.borrowclient.data.bean.LoginBean;
import com.zoesap.borrowclient.data.source.DataSource;

import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by maoqi on 2017/7/18.
 */

public class RemoteDataSource implements DataSource {
    private static RemoteDataSource INSTANCE;
    private Retrofit retrofit;

    private RemoteDataSource(Context context) {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.BaseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(setClient(context))
                    .build();
        }
    }

    private OkHttpClient setClient(Context context) {
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new SaveCookiesInterceptor(context))
                .addInterceptor(new AddCookiesInterceptor(context))
                .build();
        return client;
    }

    public static RemoteDataSource getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new RemoteDataSource(context);
        }
        return INSTANCE;
    }


    @Override
    public void getRecommendedLoanItem(final LoadCallback<List<LoanRecommendItemBean.DataBean.ListBean>> callback) {
        API.LoanRecommendListService service = retrofit.create(API.LoanRecommendListService.class);
        Call<LoanRecommendItemBean> call = service.getLoanRecommendList();
        call.enqueue(new Callback<LoanRecommendItemBean>() {
            @Override
            public void onResponse(Call<LoanRecommendItemBean> call, Response<LoanRecommendItemBean> response) {
                callback.onSuccessful(response.body().getData().getList());
            }

            @Override
            public void onFailure(Call<LoanRecommendItemBean> call, Throwable t) {
                callback.onFailure();
            }
        });
    }

    @Override
    public void getChooseLoanTypeBeanList(final LoadCallback<ChooseLoanTypeBean.DataBean> callback) {
        API.LoanTypeService service = retrofit.create(API.LoanTypeService.class);
        Call<ChooseLoanTypeBean> call = service.getLoanTypeList();
        call.enqueue(new Callback<ChooseLoanTypeBean>() {
            @Override
            public void onResponse(Call<ChooseLoanTypeBean> call, Response<ChooseLoanTypeBean> response) {
                callback.onSuccessful(response.body().getData());
            }

            @Override
            public void onFailure(Call<ChooseLoanTypeBean> call, Throwable t) {
                callback.onFailure();
            }
        });
    }

    @Override
    public void getLoanList(int loantype, int career, int credit, int house, int page,
                            final LoadCallback<LoanListItemBean.DataBean> callback) {
        API.LoanListService service = retrofit.create(API.LoanListService.class);
        Call<LoanListItemBean> call = service.getLoanList(loantype, career, credit, house, page);
        call.enqueue(new Callback<LoanListItemBean>() {
            @Override
            public void onResponse(Call<LoanListItemBean> call, Response<LoanListItemBean> response) {
                callback.onSuccessful(response.body().getData());
            }

            @Override
            public void onFailure(Call<LoanListItemBean> call, Throwable t) {
                callback.onFailure();
            }
        });

    }

    @Override
    public void getLoanDetailBean(String loanId, final LoadCallback<LoanDetailBean.DataBean> callback) {
        API.LoanDetailService service = retrofit.create(API.LoanDetailService.class);
        Call<LoanDetailBean> call = service.getLoanDetailBeanList(loanId);
        call.enqueue(new Callback<LoanDetailBean>() {
            @Override
            public void onResponse(Call<LoanDetailBean> call, Response<LoanDetailBean> response) {
                callback.onSuccessful(response.body().getData());
            }

            @Override
            public void onFailure(Call<LoanDetailBean> call, Throwable t) {
                callback.onFailure();
            }
        });
    }

    @Override
    public void login(String account, String password, final LoadCallback<LoginBean> callback) {
        API.LoginService service = retrofit.create(API.LoginService.class);
        Call<LoginBean> call = service.login(account, password);
        call.enqueue(new Callback<LoginBean>() {
            @Override
            public void onResponse(Call<LoginBean> call, Response<LoginBean> response) {
                callback.onSuccessful(response.body());
            }

            @Override
            public void onFailure(Call<LoginBean> call, Throwable t) {
                callback.onFailure();
            }
        });
    }

}
