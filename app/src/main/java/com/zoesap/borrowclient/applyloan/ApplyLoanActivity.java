package com.zoesap.borrowclient.applyloan;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.zoesap.borrowclient.R;
import com.zoesap.borrowclient.applyinfo.ApplyInfoFragment;
import com.zoesap.borrowclient.applyinfo.ApplyInfoPresenter;
import com.zoesap.borrowclient.applyqualification.ApplyQualificationFragment;
import com.zoesap.borrowclient.applyqualification.ApplyQualificationPresenter;
import com.zoesap.borrowclient.applyresult.ApplyResultFragment;
import com.zoesap.borrowclient.applyresult.ApplyResultPresenter;
import com.zoesap.borrowclient.data.Injection;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by maoqi on 2017/7/25.
 */

public class ApplyLoanActivity extends AppCompatActivity {
    public final String TAG_APPLY_INFO = "tag_apply_info";
    public final String TAG_APPLY_QUALIFICATION = "tag_apply_qualification";
    public final String TAG_APPLY_RESULT = "tag_apply_result";
    public static final String LOAN_ID = "loan_id";
    public static final String LOAN_MONEY = "loan_money";

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tb_toolbar)
    Toolbar tbToolbar;
    private ApplyInfoFragment mApplyInfoFragment;
    private FragmentManager mFragmentManager;
    private ApplyQualificationFragment mApplyQualificationFragment;
    private ApplyResultFragment mApplyResultFragment;
    private FragmentTransaction mFragmentTransaction;
    public String loanId;
    public String loanMoney;
    public String applyInfoId;
    private AlertDialog mAlertDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apply_loan);
        ButterKnife.bind(this);
        initToolbar();
        initDialog();
        loanId = getIntent().getExtras().getString(LOAN_ID);
        loanMoney = getIntent().getExtras().getString(LOAN_MONEY);

        mFragmentManager = getSupportFragmentManager();
        mApplyInfoFragment = (ApplyInfoFragment) mFragmentManager
                .findFragmentByTag(TAG_APPLY_INFO);

        if (mApplyInfoFragment == null) {
            mApplyInfoFragment = ApplyInfoFragment.newInstance();
        }
        mFragmentManager.beginTransaction()
                .add(R.id.fl_content, mApplyInfoFragment, TAG_APPLY_INFO).commit();
        new ApplyInfoPresenter(mApplyInfoFragment,Injection.provideRepository(this));
    }

    private void initDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                .setMessage(R.string.apply_cancel_alert)
                .setCancelable(false)
                .setPositiveButton(R.string.back, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        finish();
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

    public void changeFragment(int dex) {
        switch (dex) {
            case 1:
                tvTitle.setText(R.string.apply_qualification);
                mApplyQualificationFragment = (ApplyQualificationFragment) mFragmentManager
                        .findFragmentByTag(TAG_APPLY_QUALIFICATION);
                if (mApplyQualificationFragment == null) {
                    mApplyQualificationFragment = ApplyQualificationFragment.newInstance();
                    mFragmentTransaction = mFragmentManager.beginTransaction();
                    mFragmentTransaction.add(R.id.fl_content,
                            mApplyQualificationFragment,
                            TAG_APPLY_QUALIFICATION);
                }
                mFragmentTransaction.hide(mApplyInfoFragment)
                        .show(mApplyQualificationFragment).commit();
                new ApplyQualificationPresenter(mApplyQualificationFragment,
                        Injection.provideRepository(this));
                break;
            case 2:
                tvTitle.setText(R.string.apply_result);
                mApplyResultFragment = (ApplyResultFragment) mFragmentManager
                        .findFragmentByTag(TAG_APPLY_RESULT);
                if (mApplyResultFragment == null) {
                    mApplyResultFragment = ApplyResultFragment.newInstance();
                    mFragmentTransaction = mFragmentManager.beginTransaction();
                    mFragmentTransaction.add(R.id.fl_content,
                            mApplyResultFragment,
                            TAG_APPLY_RESULT);
                }
                mFragmentTransaction.hide(mApplyQualificationFragment)
                        .show(mApplyResultFragment).commit();
                new ApplyResultPresenter(mApplyResultFragment, Injection.provideRepository(this));
                break;

        }
    }

    private void initToolbar() {
        setSupportActionBar(tbToolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);
        tvTitle.setText(R.string.apply_info);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                if (mApplyResultFragment!=null&&mApplyResultFragment.isVisible()) {
                    finish();
                } else {
                    if (!mAlertDialog.isShowing()) {
                        mAlertDialog.show();
                    }
                }
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (mApplyResultFragment!=null&&mApplyResultFragment.isVisible()) {
            finish();
        } else {
            if (!mAlertDialog.isShowing()) {
                mAlertDialog.show();
            }
        }
    }

    public static Intent getStartIntent(Activity activity, String loanId, String loanMoney) {
        Intent intent = new Intent(activity, ApplyLoanActivity.class);
        intent.putExtra(LOAN_ID, loanId);
        intent.putExtra(LOAN_MONEY, loanMoney);
        return intent;
    }
}
