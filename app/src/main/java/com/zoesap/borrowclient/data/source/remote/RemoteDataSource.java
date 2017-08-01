package com.zoesap.borrowclient.data.source.remote;

import android.content.Context;
import android.util.Log;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.zoesap.borrowclient.BuildConfig;
import com.zoesap.borrowclient.Constants;
import com.zoesap.borrowclient.data.API;
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
import com.zoesap.borrowclient.data.source.DataSource;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by maoqi on 2017/7/18.
 */

public class RemoteDataSource implements DataSource {
    private static RemoteDataSource INSTANCE;
    private Retrofit retrofit;
    private Context context;

    private RemoteDataSource(Context context) {
        this.context = context;
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.BaseUrl)
//                    .addConverterFactory(GsonConverterFactory.create())
                    .addConverterFactory(CustomConverterFactory.create())
                    .client(setClient(context))
                    .build();
        }
    }

    private OkHttpClient setClient(Context context) {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .addInterceptor(new AddCookiesInterceptor(context))
                .addInterceptor(new SaveCookiesInterceptor(context));

        if (BuildConfig.DEBUG) {
            builder.addInterceptor(loggingInterceptor);
        }

        return builder.build();
    }

    public static RemoteDataSource getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new RemoteDataSource(context);
        }
        return INSTANCE;
    }

    public void loadMyLoanList(String token, final LoadCallback<List<MultiItemEntity>> callback) {
        API.MyLoanService myLoanService = retrofit.create(API.MyLoanService.class);
        Call<MyLoanBean> call = myLoanService.loadMyLoanList(token);
        call.enqueue(new Callback<MyLoanBean>() {
            @Override
            public void onResponse(Call<MyLoanBean> call, Response<MyLoanBean> response) {
                MyLoanBean.DataBean dataBean = response.body().getData();
                List<MultiItemEntity> dataList = new ArrayList<MultiItemEntity>();
                if (dataBean.getList() == null || dataBean.getList().size() <= 0) {
                    MyLoanBean.DataBean.EmptyBean bean = new MyLoanBean.DataBean.EmptyBean();
                    dataList.add(bean);
                } else {
                    dataList.addAll(dataBean.getList());
                }
                dataList.add(new MyLoanBean.DataBean.RecommendHeaderBean());
                dataList.addAll(dataBean.getRecommend());
                callback.onSuccessful(dataList);
            }

            @Override
            public void onFailure(Call<MyLoanBean> call, Throwable t) {
                callback.onFailure(t);
            }
        });
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
                callback.onFailure(t);
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
                callback.onFailure(t);
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
                callback.onFailure(t);
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
                callback.onFailure(t);
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
                callback.onFailure(t);
            }
        });
    }

    @Override
    public void getMyRecommendBean(final LoadCallback<MyRecommendBean> callback) {
        API.MyRecommendService myRecommendService = retrofit.create(API.MyRecommendService.class);
        Call<MyRecommendBean> call = myRecommendService.loadMyRecommendBean();
        call.enqueue(new Callback<MyRecommendBean>() {
            @Override
            public void onResponse(Call<MyRecommendBean> call, Response<MyRecommendBean> response) {
                callback.onSuccessful(response.body());
            }

            @Override
            public void onFailure(Call<MyRecommendBean> call, Throwable t) {
                callback.onFailure(t);
            }
        });
    }

    @Override
    public void getApplySmsCode(String phoneNum, final LoadCallback<BaseBeanWrapper> callback) {
        API.ApplyInfoService applyInfoService = retrofit.create(API.ApplyInfoService.class);
        Call<BaseBeanWrapper> call = applyInfoService.getSmsCode(phoneNum);
        call.enqueue(new Callback<BaseBeanWrapper>() {
            @Override
            public void onResponse(Call<BaseBeanWrapper> call, Response<BaseBeanWrapper> response) {
                callback.onSuccessful(response.body());
            }

            @Override
            public void onFailure(Call<BaseBeanWrapper> call, Throwable t) {
                callback.onFailure(t);
            }
        });
    }

    @Override
    public void getApplyInfo(String loan_name, String loan_mobile, String smscode,
                             String loan_money, String loan_use, String id,
                             final LoadCallback<ApplyInfoBean> callback) {
        API.ApplyInfoService applyInfoService = retrofit.create(API.ApplyInfoService.class);
        Call<ApplyInfoBean> call = applyInfoService
                .getApplyInfoBean(loan_name, loan_mobile, smscode, loan_money, loan_use, id);
        call.enqueue(new Callback<ApplyInfoBean>() {
            @Override
            public void onResponse(Call<ApplyInfoBean> call, Response<ApplyInfoBean> response) {
                callback.onSuccessful(response.body());
            }

            @Override
            public void onFailure(Call<ApplyInfoBean> call, Throwable t) {
                callback.onFailure(t);
            }
        });
    }

    @Override
    public void getApplyLoanResult(String loan_income, String loan_status, String loan_house, String id, final LoadCallback<ApplyQualificationBean> callback) {
        Log.e("RemoteDataSource", "getApplyLoanResult(RemoteDataSource.java:246)"
                + "loan_income :" + loan_income
                + "loan_status :" + loan_status
                + "loan_house :" + loan_house
                + "id :" + id);
        API.ApplyQualificationService applyQualificationService = retrofit.create(API.ApplyQualificationService.class);
        Call<ApplyQualificationBean> call = applyQualificationService.getApplyResult(loan_income, loan_status, loan_house, id);
        call.enqueue(new Callback<ApplyQualificationBean>() {
            @Override
            public void onResponse(Call<ApplyQualificationBean> call, Response<ApplyQualificationBean> response) {
                callback.onSuccessful(response.body());
            }

            @Override
            public void onFailure(Call<ApplyQualificationBean> call, Throwable t) {
                callback.onFailure(t);
            }
        });
    }

    @Override
    public void getRegisterResult(String mobileNumber, String password, String verifyPassword, String getCode, String visitCode, final LoadCallback<RegisterBean> callback) {
        API.RegisterService registerService = retrofit.create(API.RegisterService.class);
        Call<RegisterBean> call = registerService.register(mobileNumber, mobileNumber, getCode, password, verifyPassword, visitCode);
        call.enqueue(new Callback<RegisterBean>() {
            @Override
            public void onResponse(Call<RegisterBean> call, Response<RegisterBean> response) {
                callback.onSuccessful(response.body());
            }

            @Override
            public void onFailure(Call<RegisterBean> call, Throwable t) {
                callback.onFailure(t);
            }
        });
    }

    @Override
    public void getResetPasswordResult(String mobile, String resms, String password, String checkpwd, final LoadCallback<ResetPasswordBean> callback) {
        final API.ResetPasswordService resetPasswordService = retrofit.create(API.ResetPasswordService.class);
        Call<ResetPasswordBean> call = resetPasswordService.resetPassword(mobile, resms, password, checkpwd);
        call.enqueue(new Callback<ResetPasswordBean>() {
            @Override
            public void onResponse(Call<ResetPasswordBean> call, Response<ResetPasswordBean> response) {
                callback.onSuccessful(response.body());
            }

            @Override
            public void onFailure(Call<ResetPasswordBean> call, Throwable t) {
                callback.onFailure(t);
            }
        });
    }

    public void cancelMyLoanRequest(String id, String token, final LoadCallback<BaseBeanWrapper> callback) {
        API.MyLoanService myLoanService = retrofit.create(API.MyLoanService.class);
        Call<BaseBeanWrapper> call = myLoanService.cancelRequest(id, token);
        call.enqueue(new Callback<BaseBeanWrapper>() {
            @Override
            public void onResponse(Call<BaseBeanWrapper> call, Response<BaseBeanWrapper> response) {
                callback.onSuccessful(response.body());
            }

            @Override
            public void onFailure(Call<BaseBeanWrapper> call, Throwable t) {
                callback.onFailure(t);
            }
        });
    }

}
