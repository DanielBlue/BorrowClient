package com.zoesap.borrowclient.myrecommendation;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.zoesap.borrowclient.R;
import com.zoesap.borrowclient.adapter.MyRecommendListAdapter;
import com.zoesap.borrowclient.adapter.SpacesItemDecoration;
import com.zoesap.borrowclient.data.bean.MyRecommendBean;
import com.zoesap.borrowclient.util.DensityUtils;
import com.zoesap.borrowclient.util.NullUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by maoqi on 2017/7/25.
 */

public class MyRecommendationFragment extends Fragment implements MyRecommendationContract.View {
    @BindView(R.id.tv_my_recommend_code)
    TextView tvMyRecommendCode;
    @BindView(R.id.tv_recommd_num)
    TextView tvRecommdNum;
    @BindView(R.id.rv_list)
    RecyclerView rvList;
    @BindView(R.id.iv_recommd_empty)
    ImageView ivRecommdEmpty;
    Unbinder unbinder;
    @BindView(R.id.srl_refresh)
    SwipeRefreshLayout srlRefresh;
    private MyRecommendationContract.Presenter mPresenter;
    private ProgressDialog mProgressDialog;
    private MyRecommendListAdapter mAdapter;
    private List<MyRecommendBean.DataBean.DatasBean> dataList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_recommendation, null);
        unbinder = ButterKnife.bind(this, view);
        srlRefresh.setColorSchemeColors(getResources().getColor(R.color.colorPrimary));
        srlRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.refresh();
            }
        });
        mAdapter = new MyRecommendListAdapter(R.layout.item_my_recommend_list, dataList);
        rvList.addItemDecoration(new SpacesItemDecoration(DensityUtils.dp2px(getActivity(), 10), 0));
        rvList.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvList.setAdapter(mAdapter);
        return view;
    }

    @Override
    public void refreshProgressDismiss() {
        if (srlRefresh.isRefreshing()) {
            srlRefresh.setRefreshing(false);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Override
    public void setPresent(@NonNull MyRecommendationContract.Presenter presenter) {
        mPresenter = NullUtils.checkNotNull(presenter);
    }

    @Override
    public void toastInfo(String info) {
        Toast.makeText(getActivity(), info, Toast.LENGTH_SHORT);
    }

    @Override
    public void showLoadindDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(getActivity());
            mProgressDialog.setCanceledOnTouchOutside(false);
        }
        if (!mProgressDialog.isShowing()) {
            mProgressDialog.show();
        }
    }

    @Override
    public void loadingDialogDismiss() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }

    public static MyRecommendationFragment newInstance() {
        return new MyRecommendationFragment();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void updateRecommendCode(String code) {
        tvMyRecommendCode.setText(code);
    }

    @Override
    public void showEmptyList() {
        if (rvList.getVisibility() == View.VISIBLE) {
            rvList.setVisibility(View.GONE);
        }
        if (ivRecommdEmpty.getVisibility() == View.GONE) {
            ivRecommdEmpty.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void loadRecommedList(List<MyRecommendBean.DataBean.DatasBean> data) {
        if (rvList.getVisibility() == View.GONE) {
            rvList.setVisibility(View.VISIBLE);
        }
        if (ivRecommdEmpty.getVisibility() == View.VISIBLE) {
            ivRecommdEmpty.setVisibility(View.GONE);
        }
        if (dataList.size() > 0) {
            dataList.clear();
        }

        dataList.addAll(data);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void updateRecommendNum(int num) {
        tvRecommdNum.setText(String.valueOf(num));
    }
}
