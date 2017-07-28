package com.zoesap.borrowclient.home;

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
import com.zoesap.borrowclient.data.bean.LoginBean;
import com.zoesap.borrowclient.data.source.DataSource;
import com.zoesap.borrowclient.data.source.Repository;
import com.zoesap.borrowclient.loan.LoanFragment;
import com.zoesap.borrowclient.loan.LoanPresenter;
import com.zoesap.borrowclient.login.LoginActivity;
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
    public
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
        autoLogin();
        rgGroup = (RadioGroup) findViewById(R.id.rg_group);
        rgGroup.setOnCheckedChangeListener(this);
    }

    private void autoLogin() {
        String account = mRepository.getAccountFromSp();
        String password = mRepository.getPasswordFromSp();
        mRepository.login(account, password, new DataSource.LoadCallback<LoginBean>() {
            @Override
            public void onSuccessful(LoginBean loginBean) {
                if (loginBean.getCode() != 10000) {
                    Toast.makeText(HomeActivity.this, R.string.login_failed, Toast.LENGTH_SHORT).show();
                    startActivity(LoginActivity.getStartIntent(HomeActivity.this));
                } else {
                    BorrowApplication.getInstance().setmSignIn(true);
                }
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }

    private void initFragments() {
        mHomeFragment = new HomeFragment();
        mLoanFragment = new LoanFragment();
        mSelfFragment = new SelfFragment();
        mFragments = new Fragment[]{mHomeFragment, mLoanFragment, mSelfFragment};

        mFragmentManager = getSupportFragmentManager();
        mFragmentTransaction = mFragmentManager.beginTransaction();
        mFragmentTransaction
                .add(R.id.fl_content, mHomeFragment)
                .commit();
        mRepository = Injection.provideRepository(this);
        new HomePresenter(mHomeFragment, mRepository);
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
            mFragmentTransaction.add(R.id.fl_content, mFragments[index]).commit();
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
    public void onBackPressed() {
        if (firstTime + 2000 > System.currentTimeMillis()) {
            BorrowApplication.removeAllActivity();
        } else {
            Toast.makeText(this, R.string.click_twice_exit, Toast.LENGTH_SHORT).show();
        }
        firstTime = System.currentTimeMillis();
    }
}
