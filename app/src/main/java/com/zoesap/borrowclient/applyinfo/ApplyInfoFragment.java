package com.zoesap.borrowclient.applyinfo;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.zoesap.borrowclient.BaseFragment;
import com.zoesap.borrowclient.R;
import com.zoesap.borrowclient.applyloan.ApplyLoanActivity;
import com.zoesap.borrowclient.util.NullUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by maoqi on 2017/7/25.
 */

public class ApplyInfoFragment extends BaseFragment implements ApplyInfoContract.View {
    @BindView(R.id.et_apply_name)
    EditText etApplyName;
    @BindView(R.id.et_apply_phone_number)
    EditText etApplyPhoneNumber;
    @BindView(R.id.et_apply_money)
    EditText etApplyMoney;
    @BindView(R.id.et_apply_use)
    EditText etApplyUse;
    @BindView(R.id.et_apply_smscode)
    EditText etApplySmscode;
    @BindView(R.id.bt_apply_get_code)
    Button btApplyGetCode;
    Unbinder unbinder;
    private ApplyInfoContract.Presenter mPresenter;
    private String loanId;
    private String loanMoney;
    private ApplyLoanActivity mActivity;

    @Override
    public void setPresent(@NonNull ApplyInfoContract.Presenter presenter) {
        mPresenter = NullUtils.checkNotNull(presenter);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_apply_info, null);
        unbinder = ButterKnife.bind(this, view);
        mActivity = (ApplyLoanActivity) getActivity();
        loanId = mActivity.loanId;
        loanMoney = mActivity.loanMoney;
        etApplyMoney.setText(loanMoney);
        return view;
    }

    public static ApplyInfoFragment newInstance() {
        return new ApplyInfoFragment();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.rl_apply, R.id.bt_apply_get_code})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_apply:
                String name = etApplyName.getText().toString().trim();
                String phonenum = etApplyPhoneNumber.getText().toString().trim();
                String smscode = etApplySmscode.getText().toString().trim();
                String money = etApplyMoney.getText().toString().trim();
                String use = etApplyUse.getText().toString().trim();
                mPresenter.applySubmit(name, phonenum, smscode, money, use, loanId);
                break;
            case R.id.bt_apply_get_code:
                String phoneNum = etApplyPhoneNumber.getText().toString().trim();
                mPresenter.getSmsCode(phoneNum);
                break;
        }
    }

    @Override
    public void updateCountDownButton(String millis) {
        btApplyGetCode.setText(millis);
        btApplyGetCode.setOnClickListener(null);
    }

    @Override
    public void countDownFinish() {
        btApplyGetCode.setText(getString(R.string.get_verification_code));
        btApplyGetCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNum = etApplyPhoneNumber.getText().toString().trim();
                mPresenter.getSmsCode(phoneNum);
            }
        });
    }

    @Override
    public void setApplyInfoId(int id) {
        mActivity.applyInfoId = String.valueOf(id);
    }

    @Override
    public void change2NextPage() {
        mActivity.changeFragment(1);
    }

    @Override
    public Context getContext() {
        return getActivity();
    }
}
