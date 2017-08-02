package com.zoesap.borrowclient.setting;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zoesap.borrowclient.BaseFragment;
import com.zoesap.borrowclient.BorrowApplication;
import com.zoesap.borrowclient.R;
import com.zoesap.borrowclient.aboutus.AboutUsActivity;
import com.zoesap.borrowclient.login.LoginActivity;
import pers.maoqi.core.util.NullUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by maoqi on 2017/7/25.
 */

public class SettingFragment extends BaseFragment implements SettingContract.View {
    @BindView(R.id.rl_about_company)
    RelativeLayout rlAboutCompany;
    @BindView(R.id.rl_exit)
    RelativeLayout rlExit;
    @BindView(R.id.rl_check_update)
    RelativeLayout rlCheckUpdate;
    @BindView(R.id.tv_version_code)
    TextView tvVersionCode;

    Unbinder unbinder;
    private SettingContract.Presenter mPresenter;
    private String[] items = {"退出当前账号", "关闭好借贷"};
    private String[] closeItems = {"关闭好借贷"};
    private AlertDialog mAlertDialog;
    private Activity mActivity;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_setting, null);
        mActivity = getActivity();
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    private void initDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        if (BorrowApplication.getInstance().ismSignIn()) {
            builder.setItems(items, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    switch (which) {
                        case 0:
                            mAlertDialog.dismiss();
                            BorrowApplication.getInstance().loginout();
                            mPresenter.clearAccountAndPassword();
                            startActivity(LoginActivity.getStartIntent(getActivity()));
                            getActivity().finish();
                            break;
                        case 1:
                            mAlertDialog.dismiss();
                            BorrowApplication.removeAllActivity();
                            System.exit(0);
                            break;
                    }
                }
            });
        } else {
            builder.setItems(closeItems, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    mAlertDialog.dismiss();
                    BorrowApplication.removeAllActivity();
                    System.exit(0);
                }
            });
        }
        mAlertDialog = builder.create();
    }

    @Override
    public void setPresent(@NonNull SettingContract.Presenter presenter) {
        mPresenter = NullUtils.checkNotNull(presenter);
    }

    public static SettingFragment newInstance() {
        return new SettingFragment();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.rl_about_company, R.id.rl_exit, R.id.rl_check_update})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_about_company:
                startActivity(AboutUsActivity.getStartIntent(getActivity()));
                break;
            case R.id.rl_exit:
                initDialog();
                if (mAlertDialog != null && !mAlertDialog.isShowing()) {
                    mAlertDialog.show();
                }
                break;
            case R.id.rl_check_update:

                break;
        }
    }

    @Override
    public Activity getParentActivity() {
        if (getActivity() != null)
            return getActivity();
        else if (mActivity != null)
            return mActivity;
        else
            return null;
    }

    @Override
    public void showVersionCode(String version) {
        tvVersionCode.setText(version);
    }
}
