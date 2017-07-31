package com.zoesap.borrowclient.home;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.zoesap.borrowclient.BaseFragment;
import com.zoesap.borrowclient.BorrowApplication;
import com.zoesap.borrowclient.R;
import com.zoesap.borrowclient.adapter.AdapterContract;
import com.zoesap.borrowclient.adapter.LoanRecommendListAdapter;
import com.zoesap.borrowclient.adapter.SpacesItemDecoration;
import com.zoesap.borrowclient.applyloan.ApplyLoanActivity;
import com.zoesap.borrowclient.data.bean.LoanRecommendItemBean;
import com.zoesap.borrowclient.loandetail.LoanDetailActivity;
import com.zoesap.borrowclient.login.LoginActivity;
import com.zoesap.borrowclient.util.DensityUtils;
import com.zoesap.borrowclient.util.NullUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by maoqi on 2017/7/18.
 */

public class HomeFragment extends BaseFragment implements HomeContract.View {
    @BindView(R.id.et_loan_num)
    EditText etLoanNum;
    @BindView(R.id.btn_loan_apply)
    Button btnLoanApply;
    @BindView(R.id.rl_house_loan)
    RelativeLayout rlHouseLoan;
    @BindView(R.id.rl_house)
    RelativeLayout rlHouse;
    @BindView(R.id.rl_job)
    RelativeLayout rlJob;
    @BindView(R.id.imv_mineworkfor)
    ImageView imvMineworkfor;
    @BindView(R.id.rl_credit)
    RelativeLayout rlCredit;
    @BindView(R.id.iv_more_loan)
    ImageView ivMoreLoan;
    @BindView(R.id.rv_list)
    RecyclerView rvList;
    @BindView(R.id.rl_loan_fast)
    RelativeLayout rlLoanFast;
    Unbinder unbinder;

    private HomeContract.Presenter mPresenter;
    private LoanRecommendListAdapter mAdapter;
    private List<LoanRecommendItemBean.DataBean.ListBean> data = new ArrayList<>();
    private boolean isFirstLoad = true;
    private AlertDialog mAlertDialog;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_loan, null);
        unbinder = ButterKnife.bind(this, view);
        initDialog();
        rvList.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvList.addItemDecoration(new SpacesItemDecoration(DensityUtils.dp2px(getActivity(), 3), 0));
        mAdapter = new LoanRecommendListAdapter(data, getActivity());
        mAdapter.setListItemClickListener(new AdapterContract.ListItemClickListener() {
            @Override
            public void onItemClickListener(int position) {
                startActivity(LoanDetailActivity.getStartIntent(getActivity(), data.get(position).getId()));
            }
        });
        rvList.setAdapter(mAdapter);
        return view;
    }

    private void initDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity())
                .setMessage(R.string.to_login)
                .setCancelable(false)
                .setPositiveButton(R.string.login, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startActivity(LoginActivity.getStartIntent(getActivity()));
                        dialog.dismiss();
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        mAlertDialog = builder.create();
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
//        if (isFirstLoad) {
//            isFirstLoad = false;
//        }
    }

    @Override
    public void setPresent(@NonNull HomeContract.Presenter presenter) {
        mPresenter = NullUtils.checkNotNull(presenter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void refreshList(List<LoanRecommendItemBean.DataBean.ListBean> list) {
        data.clear();
        data.addAll(list);
        mAdapter.notifyDataSetChanged();
    }

    @OnClick({R.id.rl_house_loan, R.id.rl_loan_fast, R.id.rl_house, R.id.rl_job, R.id.rl_credit, R.id.btn_loan_apply})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_house_loan:
            case R.id.rl_house:
            case R.id.rl_job:
            case R.id.rl_credit:
            case R.id.rl_loan_fast:
                ((HomeActivity) getActivity()).rgGroup.check(R.id.rb_borrow);
                break;
            case R.id.btn_loan_apply:
                if (BorrowApplication.getInstance().ismSignIn()) {
                    startActivity(ApplyLoanActivity.getStartIntent(getActivity(), "0", etLoanNum.getText().toString().trim()));
                } else {
                    if (mAlertDialog != null && !mAlertDialog.isShowing()) {
                        mAlertDialog.show();
                    }
                }
                break;
        }
    }
}
