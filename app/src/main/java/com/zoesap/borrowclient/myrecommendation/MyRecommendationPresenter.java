package com.zoesap.borrowclient.myrecommendation;

import android.support.annotation.NonNull;

import com.zoesap.borrowclient.R;
import com.zoesap.borrowclient.data.bean.MyRecommendBean;
import com.zoesap.borrowclient.data.source.DataSource;
import com.zoesap.borrowclient.data.source.Repository;
import pers.maoqi.core.util.NullUtils;

/**
 * Created by maoqi on 2017/7/25.
 */

public class MyRecommendationPresenter implements MyRecommendationContract.Presenter {
    private final MyRecommendationContract.View mMyRecommendationView;
    private final Repository mRepository;

    public MyRecommendationPresenter(@NonNull MyRecommendationContract.View mMyRecommendationView,
                                     @NonNull Repository mRepository) {
        this.mMyRecommendationView = NullUtils.checkNotNull(mMyRecommendationView);
        this.mRepository = NullUtils.checkNotNull(mRepository);

        mMyRecommendationView.setPresent(this);
    }


    @Override
    public void start() {
        loadData();
    }

    private void loadData() {
        mMyRecommendationView.showLoadindDialog();
        mRepository.getMyRecommendBean(new DataSource.LoadCallback<MyRecommendBean>() {
            @Override
            public void onSuccessful(MyRecommendBean myRecommendBean) {
                if (myRecommendBean.getCode() == 10000) {
                    mMyRecommendationView.updateRecommendCode(myRecommendBean.getData().getSalt());
//                    List<MyRecommendBean.DataBean.DatasBean> dataList = new ArrayList<MyRecommendBean.DataBean.DatasBean>();
//                    MyRecommendBean.DataBean.DatasBean bean = new MyRecommendBean.DataBean.DatasBean("快速贷款", "2017-03-01", "1000", "0");
//                    dataList.add(bean);
//                    bean = new MyRecommendBean.DataBean.DatasBean("快速贷款", "2017-03-01", "1000", "1");
//                    dataList.add(bean);
//                    bean = new MyRecommendBean.DataBean.DatasBean("快速贷款", "2017-03-01", "1000", "2");
//                    dataList.add(bean);
//                    bean = new MyRecommendBean.DataBean.DatasBean("快速贷款", "2017-03-01", "1000", "3");
//                    dataList.add(bean);
//                    bean = new MyRecommendBean.DataBean.DatasBean("快速贷款", "2017-03-01", "1000", "4");
//                    dataList.add(bean);
//
//                    myRecommendBean.getData().setDatas(dataList);

                    if (myRecommendBean.getData().getDatas() == null
                            || myRecommendBean.getData().getDatas().size() <= 0) {
                        mMyRecommendationView.showEmptyList();
                        mMyRecommendationView.updateRecommendNum(0);
                    } else {
                        mMyRecommendationView.loadRecommedList(myRecommendBean.getData().getDatas());
                        mMyRecommendationView.updateRecommendNum(myRecommendBean.getData().getDatas().size());
                    }
                } else {
                    mMyRecommendationView.toastInfo(myRecommendBean.getInfo());
                }
                mMyRecommendationView.loadingDialogDismiss();
            }

            @Override
            public void onFailure(Throwable t) {
                mMyRecommendationView.toastInfo(R.string.net_error);
                mMyRecommendationView.loadingDialogDismiss();
            }
        });
    }

    @Override
    public void refresh() {
        mRepository.getMyRecommendBean(new DataSource.LoadCallback<MyRecommendBean>() {
            @Override
            public void onSuccessful(MyRecommendBean myRecommendBean) {
                if (myRecommendBean.getCode() == 10000) {
                    mMyRecommendationView.updateRecommendCode(myRecommendBean.getData().getSalt());
                    if (myRecommendBean.getData().getDatas() == null
                            || myRecommendBean.getData().getDatas().size() <= 0) {
                        mMyRecommendationView.showEmptyList();
                        mMyRecommendationView.updateRecommendNum(0);
                    } else {
                        mMyRecommendationView.loadRecommedList(myRecommendBean.getData().getDatas());
                        mMyRecommendationView.updateRecommendNum(myRecommendBean.getData().getDatas().size());
                    }
                } else {
                    mMyRecommendationView.toastInfo(myRecommendBean.getInfo());
                }
                mMyRecommendationView.refreshProgressDismiss();
            }

            @Override
            public void onFailure(Throwable t) {
                mMyRecommendationView.toastInfo(R.string.net_error);
                mMyRecommendationView.refreshProgressDismiss();
            }
        });
    }
}
