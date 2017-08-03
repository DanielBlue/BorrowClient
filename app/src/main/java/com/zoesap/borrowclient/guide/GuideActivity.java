package com.zoesap.borrowclient.guide;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.zoesap.borrowclient.BaseActivity;
import com.zoesap.borrowclient.Constants;
import com.zoesap.borrowclient.R;
import com.zoesap.borrowclient.adapter.GuidePagerAdapter;
import com.zoesap.borrowclient.home.HomeActivity;
import com.zoesap.borrowclient.splash.SplashActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pers.maoqi.core.sp.SpHelper;

/**
 * Created by maoqi on 2017/8/3.
 */

public class GuideActivity extends BaseActivity {
    @BindView(R.id.vp_pager)
    ViewPager vpPager;
    @BindView(R.id.iv_enter_home)
    ImageView ivEnterHome;
    @BindView(R.id.iv_dot_1)
    ImageView ivDot1;
    @BindView(R.id.iv_dot_2)
    ImageView ivDot2;
    @BindView(R.id.iv_dot_3)
    ImageView ivDot3;
    @BindView(R.id.ll_dots)
    LinearLayout llDots;

    private int[] images = new int[]{R.drawable.guide_01_1136, R.drawable.guide_02_1136,
            R.drawable.guide_03_1136};
    private List<ImageView> imageViews;
    private SpHelper spHelper;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        ButterKnife.bind(this);
        spHelper = SpHelper.getInstance(this);
        if (spHelper.getBoolean(Constants.FIRST_LAUCH, true)) {
            guide();
        } else {
            enterHome();
        }
    }

    private void enterHome() {
        startActivity(SplashActivity.getStartIntent(this));
    }

    private void guide() {
        initData();
        GuidePagerAdapter mAdapter = new GuidePagerAdapter(imageViews);
        vpPager.setAdapter(mAdapter);
        vpPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        if (ivEnterHome.getVisibility() == View.VISIBLE) {
                            ivEnterHome.setVisibility(View.GONE);
                        }
                        ivDot1.setImageResource(R.drawable.circle_dot_blue);
                        ivDot2.setImageResource(R.drawable.circle_dot_gray);
                        ivDot3.setImageResource(R.drawable.circle_dot_gray);
                        break;
                    case 1:
                        if (ivEnterHome.getVisibility() == View.VISIBLE) {
                            ivEnterHome.setVisibility(View.GONE);
                        }
                        ivDot1.setImageResource(R.drawable.circle_dot_gray);
                        ivDot2.setImageResource(R.drawable.circle_dot_blue);
                        ivDot3.setImageResource(R.drawable.circle_dot_gray);
                        break;
                    case 2:
                        if (ivEnterHome.getVisibility() == View.GONE) {
                            ivEnterHome.setVisibility(View.VISIBLE);
                        }
                        ivDot1.setImageResource(R.drawable.circle_dot_gray);
                        ivDot2.setImageResource(R.drawable.circle_dot_gray);
                        ivDot3.setImageResource(R.drawable.circle_dot_blue);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initData() {
        imageViews = new ArrayList<>();
        for (int i : images) {
            ImageView imageView = new ImageView(this);
            imageView.setBackgroundResource(i);
            imageViews.add(imageView);
        }
    }

    @OnClick({R.id.iv_enter_home})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_enter_home:
                startActivity(HomeActivity.getStartIntent(this));
                spHelper.putBoolean(Constants.FIRST_LAUCH,false);
                break;
        }
    }
}
