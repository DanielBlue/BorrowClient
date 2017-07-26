package com.zoesap.borrowclient.myloan;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.zoesap.borrowclient.BaseFragment;
import com.zoesap.borrowclient.R;
import com.zoesap.borrowclient.adapter.MyLoanListAdapter;
import com.zoesap.borrowclient.adapter.SpacesItemDecoration;
import com.zoesap.borrowclient.data.bean.MyLoanBean;
import com.zoesap.borrowclient.loandetail.LoanDetailActivity;
import com.zoesap.borrowclient.util.DensityUtils;
import com.zoesap.borrowclient.util.NullUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by maoqi on 2017/7/21.
 */

public class MyLoanFragment extends BaseFragment implements MyLoanContract.View {

    @BindView(R.id.rv_list)
    RecyclerView rvList;
    @BindView(R.id.srl_refresh)
    SwipeRefreshLayout srlRefresh;
    Unbinder unbinder;
    private MyLoanListAdapter mAdapter;
    private List<MultiItemEntity> dataList = new ArrayList<>();
    private MyLoanContract.Presenter mPresenter;
    private boolean isFirst = true;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_loan, container, false);
        unbinder = ButterKnife.bind(this, view);
        srlRefresh.setColorSchemeColors(getResources().getColor(R.color.colorPrimary));
        srlRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.refreshMyLoanList();
            }
        });
        mAdapter = new MyLoanListAdapter(dataList);
        mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.tv_item_cancel:
                        cancelMyLoanRequest(((MyLoanBean.DataBean.ListBean) dataList.get(position)).getId());
                        break;
                    case R.id.rl_content:
                        startActivity(LoanDetailActivity.getStartIntent(getActivity(),
                                ((MyLoanBean.DataBean.RecommendBean) dataList.get(position)).getId()));
                        break;
                }
            }
        });
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

            }
        });
        rvList.addItemDecoration(new SpacesItemDecoration(DensityUtils.dp2px(getActivity(), 5), 0));
        rvList.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAdapter.bindToRecyclerView(rvList);
        return view;
    }

    private void cancelMyLoanRequest(String id) {
        mPresenter.cancelMyLoanRequest(id);
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
        mPresenter = NullUtils.checkNotNull(presenter);
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
