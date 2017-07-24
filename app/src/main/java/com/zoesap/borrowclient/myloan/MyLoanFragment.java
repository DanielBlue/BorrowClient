package com.zoesap.borrowclient.myloan;

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
import android.widget.Toast;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.zoesap.borrowclient.R;
import com.zoesap.borrowclient.adapter.MyLoanListAdapter;
import com.zoesap.borrowclient.adapter.SpacesItemDecoration;
import com.zoesap.borrowclient.util.DensityUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by maoqi on 2017/7/21.
 */

public class MyLoanFragment extends Fragment implements MyLoanContract.View {

    @BindView(R.id.rv_list)
    RecyclerView rvList;
    Unbinder unbinder;
    @BindView(R.id.srl_refresh)
    SwipeRefreshLayout srlRefresh;
    private MyLoanListAdapter mAdapter;
    private List<MultiItemEntity> dataList = new ArrayList<>();
    private MyLoanContract.Presenter mPresenter;
    private ProgressDialog mProgressDialog;
    private boolean isFirst = true;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_loan, null);
        unbinder = ButterKnife.bind(this, view);
        srlRefresh.setColorSchemeColors(getResources().getColor(R.color.colorPrimary));
        srlRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.refreshMyLoanList();
            }
        });
        mAdapter = new MyLoanListAdapter(getActivity(), dataList);
        rvList.addItemDecoration(new SpacesItemDecoration(DensityUtils.dp2px(getActivity(), 1), 0));
        rvList.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvList.setAdapter(mAdapter);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (isFirst) {
            mPresenter.start();
            isFirst = false;
        }
    }

    @Override
    public void setPresent(@NonNull MyLoanContract.Presenter presenter) {
        mPresenter = presenter;
    }


    @Override
    public void showNetError() {
        Toast.makeText(getActivity(), R.string.net_error, Toast.LENGTH_SHORT).show();
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

    public static MyLoanFragment newInstance() {
        return new MyLoanFragment();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void loadList(List<MultiItemEntity> data) {
        if (dataList.size() > 0) {
            dataList.clear();
        }
        dataList.addAll(data);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void refreshProgressDismiss() {
        if (srlRefresh.isRefreshing()) {
            srlRefresh.setRefreshing(false);
        }
    }
}
