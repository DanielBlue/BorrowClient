package com.zoesap.borrowclient.home;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.FileCallBack;
import com.zhy.http.okhttp.callback.StringCallback;
import com.zoesap.borrowclient.BaseActivity;
import com.zoesap.borrowclient.BorrowApplication;
import com.zoesap.borrowclient.Constants;
import com.zoesap.borrowclient.R;
import com.zoesap.borrowclient.data.Injection;
import com.zoesap.borrowclient.data.source.Repository;
import com.zoesap.borrowclient.loan.LoanFragment;
import com.zoesap.borrowclient.loan.LoanPresenter;
import com.zoesap.borrowclient.self.SelfFragment;
import com.zoesap.borrowclient.self.SelfPresenter;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;

import butterknife.BindView;
import okhttp3.Call;
import pers.maoqi.core.util.AppUtils;
import pers.maoqi.core.util.LogUtils;

public class HomeActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener {

    @BindView(R.id.fl_content)
    FrameLayout flContent;
    @BindView(R.id.rb_home)
    RadioButton rbHome;
    @BindView(R.id.rb_borrow)
    RadioButton rbBorrow;
    @BindView(R.id.rb_self)
    RadioButton rbSelf;
    @BindView(R.id.rg_group)
    RadioGroup rgGroup;

    private FragmentManager mFragmentManager;
    private HomeFragment mHomeFragment;
    private LoanFragment mLoanFragment;
    private SelfFragment mSelfFragment;
    private Fragment[] mFragments;
    private int mCurrentIndex = 0;
    private FragmentTransaction mFragmentTransaction;
    private Repository mRepository;
    private long firstTime;
    private AlertDialog alertDialog;
    private ProgressDialog updateDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initFragments();
        initUpdateProgressDialog();
        rgGroup = (RadioGroup) findViewById(R.id.rg_group);
        rgGroup.setOnCheckedChangeListener(this);
        checkUpdate();
    }

    private void initUpdateProgressDialog() {
        updateDialog = new ProgressDialog(HomeActivity.this);
        updateDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        updateDialog.setTitle(R.string.downloading);
        updateDialog.setMax(100);
        updateDialog.setCanceledOnTouchOutside(false);
        updateDialog.setCancelable(false);
    }

    private void checkUpdate() {
        OkHttpUtils.post()
                .url(Constants.BaseUrl + "Article/edition")
                .addParams("edition", "hjdedition")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        if (!TextUtils.isEmpty(response)) {
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                double version = jsonObject.getJSONObject("data").getDouble("hjdedition");
                                double app_version = Double.parseDouble(AppUtils.getAppVersion(HomeActivity.this));
                                if (app_version < version) {
                                    showUpdateDialog(jsonObject.getJSONObject("data").getString("url"));
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
    }

    private void showUpdateDialog(final String url) {
        alertDialog = new AlertDialog.Builder(this)
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
                                        Toast.makeText(HomeActivity.this, R.string.net_error, Toast.LENGTH_SHORT);
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

    private void installApk(File apkFile) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.setDataAndType(Uri.fromFile(apkFile), "application/vnd.android.package-archive");
        startActivity(intent);
    }

    private void initFragments() {
        mHomeFragment = new HomeFragment();
        mLoanFragment = new LoanFragment();
        mSelfFragment = new SelfFragment();
        mFragments = new Fragment[]{mHomeFragment, mLoanFragment, mSelfFragment};

        mFragmentManager = getSupportFragmentManager();
        mFragmentTransaction = mFragmentManager.beginTransaction();
        mFragmentTransaction
                .add(R.id.fl_content, mHomeFragment, String.valueOf(0))
                .commit();
        mRepository = Injection.provideRepository(this);
        new HomePresenter(mHomeFragment, mRepository);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Fragment fragment = mFragmentManager.findFragmentByTag(String.valueOf(mCurrentIndex));
        if (!(fragment != null && fragment.isAdded() && fragment.isVisible())) {
            mFragmentManager.beginTransaction().add(R.id.fl_content, fragment, String.valueOf(mCurrentIndex)).commit();
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
        switch (checkedId) {
            case R.id.rb_home:
                showFragment(0);
                break;
            case R.id.rb_borrow:
                showFragment(1);
                break;
            case R.id.rb_self:
//                if (!BorrowApplication.getInstance().isSignIn()) {
//                    startActivity(new Intent(this, LoginActivity.class));
//                    group.check(preRbIdRes);
//                    anima();
//                    return;
//                }
                showFragment(2);
        }
    }

    private void showFragment(int index) {
        if (index == mCurrentIndex) {
            return;
        }

        mFragmentTransaction = mFragmentManager.beginTransaction();
        mFragmentTransaction.hide(mFragmentManager.findFragmentByTag(String.valueOf(mCurrentIndex)));

        if (index != 1) {
            if (mFragments[index].isAdded()) {
                mFragmentTransaction.show(mFragments[index]).commit();
            } else {
                mFragmentTransaction.add(R.id.fl_content, mFragments[index], String.valueOf(index)).commit();
                switch (index) {
                    case 0:
                        new HomePresenter((HomeFragment) mFragments[index], mRepository);
                        break;
//                case 1:
//                    new LoanPresenter((LoanFragment) mFragments[index], mRepository);
//                    break;
                    case 2:
                        new SelfPresenter((SelfFragment) mFragments[index], mRepository);
                        break;
                    default:
                        break;
                }
            }
        } else {
            mLoanFragment = new LoanFragment();
            if (mFragmentManager.findFragmentByTag("1")!=null&&mFragmentManager.findFragmentByTag("1").isAdded()) {
                mFragmentTransaction.remove(mFragmentManager.findFragmentByTag("1"));
            }
            mFragmentTransaction.add(R.id.fl_content, mLoanFragment, String.valueOf(index)).commit();
            new LoanPresenter(mLoanFragment, mRepository);
        }
        mCurrentIndex = index;
    }

    public static Intent getStartIntent(Activity activity) {
        Intent intent = new Intent(activity, HomeActivity.class);
        return intent;
    }

    @Override
    public void onBackPressed() {
        if (firstTime + 2000 > System.currentTimeMillis()) {
            BorrowApplication.removeAllActivity();
        } else {
            Toast.makeText(this, R.string.click_twice_exit, Toast.LENGTH_SHORT).show();
        }
        firstTime = System.currentTimeMillis();
    }
}
