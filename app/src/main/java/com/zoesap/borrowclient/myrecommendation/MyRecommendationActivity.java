package com.zoesap.borrowclient.myrecommendation;

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
 * Created by maoqi on 2017/7/25.
 */

public class MyRecommendationActivity extends BaseActivity {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tb_toolbar)
    Toolbar tbToolbar;
    private MyRecommendationFragment mMyRecommendationFragment;
    private FragmentManager mFragmentManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_recommendation);
        ButterKnife.bind(this);
        initToolbar();

        mFragmentManager = getSupportFragmentManager();
        mMyRecommendationFragment = (MyRecommendationFragment) mFragmentManager.findFragmentById(R.id.fl_content);
        if (mMyRecommendationFragment == null) {
            mMyRecommendationFragment = MyRecommendationFragment.newInstance();
            mFragmentManager.beginTransaction().add(R.id.fl_content, mMyRecommendationFragment).commit();
        }

        new MyRecommendationPresenter(mMyRecommendationFragment, Injection.provideRepository(this));
    }
    
    private void initToolbar() {
            setSupportActionBar(tbToolbar);
            ActionBar actionBar = getSupportActionBar();
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowTitleEnabled(false);
            tvTitle.setText(R.string.my_recommendation);
        }

    @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            switch (item.getItemId()) {
                case android.R.id.home:
                    finish();
                    return true;
            }
            return super.onOptionsItemSelected(item);
        }

    public static Intent getStartIntent(Activity activity) {
        Intent intent = new Intent(activity, MyRecommendationActivity.class);
        return intent;
    }
}
