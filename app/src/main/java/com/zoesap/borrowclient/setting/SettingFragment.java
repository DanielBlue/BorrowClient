package com.zoesap.borrowclient.setting;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.zoesap.borrowclient.R;
import com.zoesap.borrowclient.util.NullUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by maoqi on 2017/7/25.
 */

public class SettingFragment extends Fragment implements SettingContract.View {
    @BindView(R.id.rl_about_company)
    RelativeLayout rlAboutCompany;
    @BindView(R.id.rl_exit)
    RelativeLayout rlExit;
    Unbinder unbinder;
    private SettingContract.Presenter mPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_setting, null);
        unbinder = ButterKnife.bind(this, super.onCreateView(inflater, container, savedInstanceState));
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void setPresent(@NonNull SettingContract.Presenter presenter) {
        mPresenter = NullUtils.checkNotNull(presenter);
    }

    @Override
    public void toastInfo(String info) {

    }

    @Override
    public void showLoadindDialog() {

    }

    @Override
    public void loadingDialogDismiss() {

    }

    public static SettingFragment newInstance() {
        return new SettingFragment();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.rl_about_company, R.id.rl_exit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_about_company:

                break;
            case R.id.rl_exit:

                break;
        }
    }
}
