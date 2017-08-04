package com.zoesap.borrowclient.setting;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.FileCallBack;
import com.zhy.http.okhttp.callback.StringCallback;
import com.zoesap.borrowclient.BorrowApplication;
import com.zoesap.borrowclient.Constants;
import com.zoesap.borrowclient.R;
import com.zoesap.borrowclient.aboutus.AboutUsActivity;
import com.zoesap.borrowclient.login.LoginActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import okhttp3.Call;
import pers.maoqi.core.CoreBaseFragment;
import pers.maoqi.core.util.AppUtils;
import pers.maoqi.core.util.LogUtils;
import pers.maoqi.core.util.NullUtils;

/**
 * Created by maoqi on 2017/7/25.
 */

public class SettingFragment extends CoreBaseFragment implements SettingContract.View {
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
    private ProgressDialog updateDialog;
    private AlertDialog alertDialog;

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
                checkUpdate();
                break;
        }
    }

    private void checkUpdate() {
        initUpdateProgressDialog();
        OkHttpUtils.post()
                .url(Constants.BaseUrl + "Article/edition")
                .addParams("edition", "hjdedition")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        toastInfo(R.string.net_error);
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        if (!TextUtils.isEmpty(response)) {
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                double version = jsonObject.getJSONObject("data").getDouble("hjdedition");
                                double app_version = Double.parseDouble(AppUtils.getAppVersion(getActivity()));
                                if (app_version < version) {
                                    showUpdateDialog(jsonObject.getJSONObject("data").getString("url"));
                                } else {
                                    toastInfo(R.string.latest_version);
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
    }

    private void showUpdateDialog(final String url) {
        alertDialog = new AlertDialog.Builder(getActivity())
                .setTitle(R.string.version_update)
                .setMessage(R.string.update_info)
                .setCancelable(false)
                .setPositiveButton(R.string.update, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(final DialogInterface dialog, int which) {
                        if (alertDialog.isShowing()) {
                            alertDialog.dismiss();
                        }
                        if (!updateDialog.isShowing()) {
                            updateDialog.show();
                        }
                        LogUtils.d("HomeActivity", "onClick(HomeActivity.java:133)"
                                + Environment.getExternalStorageDirectory()
                                + File.separator + "Download" + File.separator + "borrow.apk");
                        OkHttpUtils.get()
                                .url(url)
                                .build()
                                .execute(new FileCallBack(Environment.getExternalStorageDirectory()
                                        + File.separator + "Download", "borrow.apk") {
                                    @Override
                                    public void onError(Call call, Exception e, int id) {
                                        if (updateDialog.isShowing()) {
                                            updateDialog.dismiss();
                                        }
                                        toastInfo(R.string.net_error);
                                    }

                                    @Override
                                    public void onResponse(File response, int id) {
                                        if (updateDialog.isShowing()) {
                                            updateDialog.dismiss();
                                        }
                                        installApk(response);
                                    }

                                    @Override
                                    public void inProgress(float progress, long total, int id) {
                                        super.inProgress(progress, total, id);
                                        updateDialog.setProgress((int) (progress * 100));
                                        LogUtils.d("HomeActivity", String.valueOf(progress));
                                    }
                                });
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (alertDialog.isShowing()) {
                            alertDialog.dismiss();
                        }
                    }
                }).show();
    }

    private void initUpdateProgressDialog() {
        updateDialog = new ProgressDialog(getActivity());
        updateDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        updateDialog.setTitle(R.string.downloading);
        updateDialog.setMax(100);
        updateDialog.setCanceledOnTouchOutside(false);
        updateDialog.setCancelable(false);
    }

    private void installApk(File apkFile) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.setDataAndType(Uri.fromFile(apkFile), "application/vnd.android.package-archive");
        startActivity(intent);
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
