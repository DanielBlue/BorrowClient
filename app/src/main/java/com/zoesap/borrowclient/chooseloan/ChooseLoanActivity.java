package com.zoesap.borrowclient.chooseloan;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.zoesap.borrowclient.BaseActivity;
import com.zoesap.borrowclient.R;
import com.zoesap.borrowclient.data.Injection;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by maoqi on 2017/7/19.
 */

public class ChooseLoanActivity extends BaseActivity {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tb_toolbar)
    Toolbar tbToolbar;
    @BindView(R.id.fl_content)
    FrameLayout flContent;
    private ChooseLoanFragment mChooseLoanFragment;
    private FragmentManager mFragmentManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_loan);
        ButterKnife.bind(this);
        initToolbar();

        mFragmentManager = getSupportFragmentManager();
        mChooseLoanFragment = (ChooseLoanFragment) mFragmentManager.findFragmentById(R.id.fl_content);
        if (mChooseLoanFragment==null){
            mChooseLoanFragment = ChooseLoanFragment.newInstance();
            mFragmentManager.beginTransaction().add(R.id.fl_content,mChooseLoanFragment).commit();
        }
        new ChooseLoanPresenter(mChooseLoanFragment, Injection.provideRepository(this));
    }

    private void initToolbar() {
        setSupportActionBar(tbToolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);
        tvTitle.setText(R.string.all_loan);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_choose_loan,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.user:
                // TODO: 2017/7/19 menu click

                return true;
            case android.R.id.home:
                finish();
                return true;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public static Intent getStartIntent(Activity activity){
        Intent intent = new Intent(activity,ChooseLoanActivity.class);
        return intent;
    }
}
