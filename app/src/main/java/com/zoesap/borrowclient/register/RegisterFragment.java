package com.zoesap.borrowclient.register;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import pers.maoqi.core.CoreBaseFragment;
import com.zoesap.borrowclient.R;
import pers.maoqi.core.util.NullUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by maoqi on 2017/7/26.
 */

public class RegisterFragment extends CoreBaseFragment implements RegisterContract.View {
    @BindView(R.id.et_phone_number)
    EditText etPhoneNumber;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.et_confirm_password)
    EditText etConfirmPassword;
    @BindView(R.id.btn_get_code)
    Button btnGetCode;
    @BindView(R.id.et_verification_code)
    EditText etVerificationCode;
    @BindView(R.id.et_visit_code)
    EditText etVisitCode;
    @BindView(R.id.btn_submit)
    Button btnSubmit;
    Unbinder unbinder;
    private RegisterContract.Presenter mPresenter;

    @Override
    public void setPresent(@NonNull RegisterContract.Presenter presenter) {
        mPresenter = NullUtils.checkNotNull(presenter);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register, null);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    public static RegisterFragment getInstance() {
        return new RegisterFragment();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mPresenter.stopCountDownTimer();
        unbinder.unbind();
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
    public void clearInput() {
        etPhoneNumber.setText("");
        etPassword.setText("");
        etConfirmPassword.setText("");
        etVerificationCode.setText("");
    }

    @Override
    public void activityFinish() {
        getActivity().finish();
    }

    @OnClick({R.id.btn_get_code, R.id.btn_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_get_code:
                String phoneNum = etPhoneNumber.getText().toString().trim();
                mPresenter.getSmsCode(phoneNum);
                break;
            case R.id.btn_submit:
                String mobileNumber = etPhoneNumber.getText().toString().trim();
                String password = etPassword.getText().toString().trim();
                String verifyPassword = etConfirmPassword.getText().toString().trim();
                String getCode = etVerificationCode.getText().toString().trim();
                String visitCode = etVisitCode.getText().toString().trim();
                mPresenter.applySubmit(mobileNumber, password, verifyPassword, getCode, visitCode);
                break;
        }
    }
}
