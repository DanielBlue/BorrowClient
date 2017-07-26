package com.zoesap.borrowclient.login;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.InputType;
import android.text.Selection;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.zoesap.borrowclient.BaseFragment;
import com.zoesap.borrowclient.BorrowApplication;
import com.zoesap.borrowclient.R;
import com.zoesap.borrowclient.login.LoginContract.View;
import com.zoesap.borrowclient.util.NullUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by maoqi on 2017/7/20.
 */

public class LoginFragment extends BaseFragment implements View {
    @BindView(R.id.et_username)
    EditText etUsername;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.tb_toggle)
    ToggleButton tbToggle;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.resetPassWrod)
    TextView resetPassWrod;
    Unbinder unbinder;
    private LoginContract.Presenter mPresenter;
    private boolean isFirstLoad = true;

    @Nullable
    @Override
    public android.view.View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        android.view.View view = inflater.inflate(R.layout.fragment_login, null);
        unbinder = ButterKnife.bind(this, view);
        tbToggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    etPassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                } else {
                    etPassword.setInputType(InputType.TYPE_CLASS_TEXT
                            | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }

                Editable editable = etPassword.getText();
                Selection.setSelection(editable, editable.length());
            }
        });
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (isFirstLoad) {
            mPresenter.start();
            isFirstLoad = false;
        }
    }

    @Override
    public void setPresent(@NonNull LoginContract.Presenter presenter) {
        mPresenter = NullUtils.checkNotNull(presenter);
    }

    public static LoginFragment newInstance() {
        return new LoginFragment();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.btn_login, R.id.resetPassWrod})
    public void onViewClicked(android.view.View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                String telRegex = "[1][3578]\\d{9}";
                String phone_number = etUsername.getText().toString().trim();
                String phone_pass = etPassword.getText().toString().trim();

                if (TextUtils.isEmpty(phone_number) || TextUtils.isEmpty(phone_pass)) {
                    Toast.makeText(BorrowApplication.getInstance().getAppcontext(),
                            "手机号和密码不能为空!", Toast.LENGTH_LONG).show();
                } else if (!phone_number.matches(telRegex)) {
                    Toast.makeText(BorrowApplication.getInstance().getAppcontext(),
                            "手机号非法", Toast.LENGTH_LONG).show();
                } else {
                    mPresenter.login(phone_number,phone_pass);
                }
                break;
            case R.id.resetPassWrod:

                break;
        }
    }

    @Override
    public void showPreInptAccount(String account) {
        if (!TextUtils.isEmpty(account)){
            etUsername.setText(account);
            Editable editable = etUsername.getText();
            Selection.setSelection(editable, editable.length());
        }
    }

    @Override
    public void showToast(String info) {
        Toast.makeText(getActivity(), info, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void finish() {
        getActivity().finish();
    }
}
