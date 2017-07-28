package com.zoesap.borrowclient.resetpassword;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.zoesap.borrowclient.BaseActivity;
import com.zoesap.borrowclient.R;
import com.zoesap.borrowclient.data.Injection;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by maoqi on 2017/7/27.
 */

public class ResetPasswordActivity extends BaseActivity {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tb_toolbar)
    Toolbar tbToolbar;
    private FragmentManager mFragmentManager;
    private ResetPasswordFragment mResetPasswordFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);
        ButterKnife.bind(this);
        initToolbar();
        mFragmentManager = getSupportFragmentManager();
        mResetPasswordFragment = (ResetPasswordFragment) mFragmentManager.findFragmentById(R.id.fl_content);
        if (mResetPasswordFragment == null) {
            mResetPasswordFragment = ResetPasswordFragment.newInstance();
            mFragmentManager.beginTransaction().add(R.id.fl_content, mResetPasswordFragment).commit();
        }

        new ResetPasswordPresenter(mResetPasswordFragment, Injection.provideRepository(this));

    }

    private void initToolbar() {
        setSupportActionBar(tbToolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);
        tvTitle.setText(R.string.reset_password);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public static Intent getStartIntent(Activity activity) {
        Intent intent = new Intent(activity, ResetPasswordActivity.class);
        return intent;
    }
}
