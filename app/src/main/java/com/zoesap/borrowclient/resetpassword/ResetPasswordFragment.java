package com.zoesap.borrowclient.resetpassword;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.Selection;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.zoesap.borrowclient.BaseFragment;
import com.zoesap.borrowclient.R;
import pers.maoqi.core.util.NullUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by maoqi on 2017/7/27.
 */

public class ResetPasswordFragment extends BaseFragment implements ResetPasswordContract.View {

    @BindView(R.id.et_phone_number)
    EditText etPhoneNumber;
    @BindView(R.id.btn_get_code)
    Button btnGetCode;
    @BindView(R.id.et_verification_code)
    EditText etVerificationCode;
    @BindView(R.id.et_new_password)
    EditText etNewPassword;
    @BindView(R.id.et_confirm_password)
    EditText etConfirmPassword;
    @BindView(R.id.btn_submit)
    Button btnSubmit;
    Unbinder unbinder;
    private ResetPasswordContract.Presenter mPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_reset_password, null);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void setPresent(@NonNull ResetPasswordContract.Presenter presenter) {
        mPresenter = NullUtils.checkNotNull(presenter);
    }

    public static ResetPasswordFragment newInstance() {
        return new ResetPasswordFragment();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mPresenter.stopCountDownTimer();
        unbinder.unbind();
    }

    @OnClick({R.id.btn_get_code, R.id.btn_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_get_code:
                String phoneNum = etPhoneNumber.getText().toString().trim();
                mPresenter.getSmsCode(phoneNum);
                break;
            case R.id.btn_submit:
                String phoneNumber = etPhoneNumber.getText().toString().trim();
                String code = etVerificationCode.getText().toString().trim();
                String newPassword = etNewPassword.getText().toString().trim();
                String confirmPassword = etConfirmPassword.getText().toString().trim();
                mPresenter.submit(phoneNumber,code,newPassword,confirmPassword);
                break;
        }
    }

    @Override
    public void updateCountDownButton(String millis) {
        btnGetCode.setText(millis + "秒");
        btnGetCode.setOnClickListener(null);
    }

    @Override
    public void countDownFinish() {
        btnGetCode.setText(getString(R.string.get_verification_code));
        btnGetCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNum = etPhoneNumber.getText().toString().trim();
                mPresenter.getSmsCode(phoneNum);
            }
        });
    }

    @Override
    public void activityFinish() {
        getActivity().finish();
    }

    @Override
    public void showPreInptAccount(String account) {
        etPhoneNumber.setText(account);
        Editable editable = etPhoneNumber.getText();
        Selection.setSelection(editable, editable.length());
    }
}
