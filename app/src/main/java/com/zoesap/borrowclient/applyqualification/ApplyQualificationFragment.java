package com.zoesap.borrowclient.applyqualification;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.zoesap.borrowclient.BaseFragment;
import com.zoesap.borrowclient.R;
import com.zoesap.borrowclient.adapter.ApplyLoanPopupListAdapter;
import com.zoesap.borrowclient.adapter.SpacesItemDecoration;
import com.zoesap.borrowclient.applyloan.ApplyLoanActivity;
import com.zoesap.borrowclient.util.DensityUtils;
import com.zoesap.borrowclient.util.NullUtils;

import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by maoqi on 2017/7/25.
 */

public class ApplyQualificationFragment extends BaseFragment implements ApplyQualificationContract.View {
    @BindView(R.id.tv_income)
    TextView tvIncome;
    @BindView(R.id.tv_job)
    TextView tvJob;
    @BindView(R.id.tv_house)
    TextView tvHouse;
    Unbinder unbinder;
    @BindView(R.id.rl_content)
    RelativeLayout rlContent;
    private ApplyQualificationContract.Presenter mPresenter;
    private PopupWindow mIncomePopupWindow;
    private String[] incomeArray = {"1000~3000元", "3000~5000元", "5000~10000元", "10000~50000元", "50000元以上"};
    private String[] jobArray = {"个体工商户", "上班族", "无固定职业", "企业主", "学生", "其他"};
    private String[] houseArray = {"有房产", "无房产"};
    private String mCurrentIncome;
    private String mCurrentJob;
    private PopupWindow mJobPopupWindow;
    private String mCurrentHouse;
    private PopupWindow mHousePopupWindow;
    private ApplyLoanActivity mActivity;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_apply_qualification, null);
        unbinder = ButterKnife.bind(this, view);
        mActivity = (ApplyLoanActivity) getActivity();
        initPopupWindow();
        return view;
    }

    private void initPopupWindow() {
        initIncomePopupWindow();
        initJobPopupWindow();
        initHousePopupWindow();
    }

    private void initIncomePopupWindow() {
        View view = View.inflate(getActivity(), R.layout.view_popup_apply_loan, null);
        RecyclerView rv_list = (RecyclerView) view.findViewById(R.id.rv_list);
        LinearLayout ll_cancel = (LinearLayout) view.findViewById(R.id.ll_cancel);
        ApplyLoanPopupListAdapter adapter =
                new ApplyLoanPopupListAdapter(R.layout.item_popup_apply_loan, Arrays.asList(incomeArray));
        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {

            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.tv_text:
                        mCurrentIncome = incomeArray[position];
                        if (mCurrentIncome != null && !TextUtils.isEmpty(mCurrentIncome)) {
                            tvIncome.setText(mCurrentIncome);
                        }
                        if (mIncomePopupWindow.isShowing()) {
                            mIncomePopupWindow.dismiss();
                        }
                        break;
                }
            }
        });
        rv_list.addItemDecoration(new SpacesItemDecoration(DensityUtils.dp2px(getActivity(), 1), 0));
        rv_list.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv_list.setAdapter(adapter);
        ll_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mIncomePopupWindow.isShowing()) {
                    mIncomePopupWindow.dismiss();
                }
            }
        });

        mIncomePopupWindow = new PopupWindow(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        mIncomePopupWindow.setContentView(view);
        mIncomePopupWindow.setFocusable(true);
        mIncomePopupWindow.setAnimationStyle(R.style.AnimBottomUpDown);
    }

    private void initJobPopupWindow() {
        View view = View.inflate(getActivity(), R.layout.view_popup_apply_loan, null);
        RecyclerView rv_list = (RecyclerView) view.findViewById(R.id.rv_list);
        LinearLayout ll_cancel = (LinearLayout) view.findViewById(R.id.ll_cancel);
        ApplyLoanPopupListAdapter adapter =
                new ApplyLoanPopupListAdapter(R.layout.item_popup_apply_loan, Arrays.asList(jobArray));
        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {

            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.tv_text:
                        mCurrentJob = jobArray[position];
                        if (mCurrentJob != null && !TextUtils.isEmpty(mCurrentJob)) {
                            tvJob.setText(mCurrentJob);
                        }
                        if (mJobPopupWindow.isShowing()) {
                            mJobPopupWindow.dismiss();
                        }
                        break;
                }
            }
        });
        rv_list.addItemDecoration(new SpacesItemDecoration(DensityUtils.dp2px(getActivity(), 1), 0));
        rv_list.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv_list.setAdapter(adapter);
        ll_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mJobPopupWindow.isShowing()) {
                    mJobPopupWindow.dismiss();
                }
            }
        });

        mJobPopupWindow = new PopupWindow(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        mJobPopupWindow.setContentView(view);
        mJobPopupWindow.setFocusable(true);
        mJobPopupWindow.setAnimationStyle(R.style.AnimBottomUpDown);
    }

    private void initHousePopupWindow() {
        View view = View.inflate(getActivity(), R.layout.view_popup_apply_loan, null);
        RecyclerView rv_list = (RecyclerView) view.findViewById(R.id.rv_list);
        LinearLayout ll_cancel = (LinearLayout) view.findViewById(R.id.ll_cancel);
        ApplyLoanPopupListAdapter adapter =
                new ApplyLoanPopupListAdapter(R.layout.item_popup_apply_loan, Arrays.asList(houseArray));
        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {

            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.tv_text:
                        mCurrentHouse = String.valueOf(position + 1);
                        if (mCurrentHouse != null && !TextUtils.isEmpty(mCurrentHouse)) {
                            tvHouse.setText(houseArray[position]);
                        }
                        if (mHousePopupWindow.isShowing()) {
                            mHousePopupWindow.dismiss();
                        }
                        break;
                }
            }
        });
        rv_list.addItemDecoration(new SpacesItemDecoration(DensityUtils.dp2px(getActivity(), 1), 0));
        rv_list.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv_list.setAdapter(adapter);
        ll_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mHousePopupWindow.isShowing()) {
                    mHousePopupWindow.dismiss();
                }
            }
        });

        mHousePopupWindow = new PopupWindow(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        mHousePopupWindow.setContentView(view);
        mHousePopupWindow.setFocusable(true);
        mHousePopupWindow.setAnimationStyle(R.style.AnimBottomUpDown);
    }

    @Override
    public void setPresent(@NonNull ApplyQualificationContract.Presenter presenter) {
        mPresenter = NullUtils.checkNotNull(presenter);
    }

    public static ApplyQualificationFragment newInstance() {
        return new ApplyQualificationFragment();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.rl_apply, R.id.tv_income, R.id.tv_job, R.id.tv_house})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_apply:
                mPresenter.submit2Server(mCurrentIncome, mCurrentJob, mCurrentHouse, mActivity.applyInfoId);
                break;
            case R.id.tv_income:
                if (!mIncomePopupWindow.isShowing()) {
                    mIncomePopupWindow.showAtLocation(rlContent, Gravity.BOTTOM, 0, 0);
                }
                break;
            case R.id.tv_job:
                if (!mJobPopupWindow.isShowing()) {
                    mJobPopupWindow.showAtLocation(rlContent, Gravity.BOTTOM, 0, 0);
                }
                break;
            case R.id.tv_house:
                if (!mHousePopupWindow.isShowing()) {
                    mHousePopupWindow.showAtLocation(rlContent, Gravity.BOTTOM, 0, 0);
                }
                break;
        }
    }

    @Override
    public Context getContext() {
        return getActivity();
    }

    @Override
    public void change2NextPage() {
        mActivity.changeFragment(2);
    }
}
