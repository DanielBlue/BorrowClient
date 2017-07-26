package com.zoesap.borrowclient.loandetail;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.zoesap.borrowclient.R;
import com.zoesap.borrowclient.data.Injection;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoanDetailActivity extends AppCompatActivity {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tb_toolbar)
    Toolbar tbToolbar;
    @BindView(R.id.fl_content)
    FrameLayout flContent;
    private FragmentManager mFragmentManager;
    private LoanDetailFragment mLoanDetailFragment;
    public String loanId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loan_detail);
        ButterKnife.bind(this);
        initToolbar();
        loanId = getIntent().getExtras().getString("loan_id");
        Log.e("LoanDetailActivity","onCreate(LoanDetailActivity.java:39)"+ loanId);
        mFragmentManager = getSupportFragmentManager();
        mLoanDetailFragment = (LoanDetailFragment) mFragmentManager.findFragmentById(R.id.fl_content);
        if (mLoanDetailFragment == null) {
            mLoanDetailFragment = LoanDetailFragment.newInstance();
            mFragmentManager.beginTransaction().add(R.id.fl_content, mLoanDetailFragment).commit();
        }

        new LoanDetailPresenter(mLoanDetailFragment, Injection.provideRepository(this), loanId);
    }

    private void initToolbar() {
        setSupportActionBar(tbToolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);
        tvTitle.setText(R.string.loan_detail);
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

    public static Intent getStartIntent(Activity activity, String loanId) {
        Intent intent = new Intent(activity, LoanDetailActivity.class);
        intent.putExtra("loan_id", loanId);
        return intent;
    }
}
