package com.zoesap.borrowclient.myloan;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
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

public class MyLoanActivity extends BaseActivity {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tb_toolbar)
    Toolbar tbToolbar;
    private FragmentManager mFragmentManager;
    private MyLoanFragment mMyLoanFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_loan);
        ButterKnife.bind(this);
        initToolbar();

        mFragmentManager = getSupportFragmentManager();
        mMyLoanFragment = (MyLoanFragment) mFragmentManager.findFragmentById(R.id.fl_content);
        if (mMyLoanFragment == null) {
            mMyLoanFragment = MyLoanFragment.newInstance();
            mFragmentManager.beginTransaction().add(R.id.fl_content, mMyLoanFragment).commit();
        }
        new MyLoanPresenter(mMyLoanFragment, Injection.provideRepository(this));
    }

    private void initToolbar() {
        setSupportActionBar(tbToolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);
        tvTitle.setText(R.string.my_loan);
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
        Intent intent = new Intent(activity, MyLoanActivity.class);
        return intent;
    }
}
