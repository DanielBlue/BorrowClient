package com.zoesap.borrowclient.home;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.zoesap.borrowclient.BaseActivity;
import com.zoesap.borrowclient.BorrowApplication;
import com.zoesap.borrowclient.R;
import com.zoesap.borrowclient.data.Injection;
import com.zoesap.borrowclient.data.source.Repository;
import com.zoesap.borrowclient.loan.LoanFragment;
import com.zoesap.borrowclient.loan.LoanPresenter;
import com.zoesap.borrowclient.self.SelfFragment;
import com.zoesap.borrowclient.self.SelfPresenter;

import butterknife.BindView;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initFragments();
        rgGroup = (RadioGroup) findViewById(R.id.rg_group);
        rgGroup.setOnCheckedChangeListener(this);
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
        mFragmentTransaction.hide(mFragments[mCurrentIndex]);

        if (mFragments[index].isAdded()) {
            mFragmentTransaction.show(mFragments[index]).commit();

        } else {
            mFragmentTransaction.add(R.id.fl_content, mFragments[index], String.valueOf(index)).commit();
            switch (index) {
                case 0:
                    new HomePresenter((HomeFragment) mFragments[index], mRepository);
                    break;
                case 1:
                    new LoanPresenter((LoanFragment) mFragments[index], mRepository);
                    break;
                case 2:
                    new SelfPresenter((SelfFragment) mFragments[index], mRepository);
                    break;
                default:
                    break;
            }

        }
        mCurrentIndex = index;
    }

    @Override
    protected void onStart() {
        super.onStart();

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
