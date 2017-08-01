package com.zoesap.borrowclient.login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.zoesap.borrowclient.BaseActivity;
import com.zoesap.borrowclient.R;
import com.zoesap.borrowclient.data.Injection;
import com.zoesap.borrowclient.register.RegisterActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends BaseActivity {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tb_toolbar)
    Toolbar tbToolbar;
    private FragmentManager mFragmentManager;
    private LoginFragment mLoginFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        initToolbar();

        mFragmentManager = getSupportFragmentManager();
        mLoginFragment = (LoginFragment) mFragmentManager.findFragmentById(R.id.fl_content);
        if (mLoginFragment==null){
            mLoginFragment = LoginFragment.newInstance();
            mFragmentManager.beginTransaction().add(R.id.fl_content,mLoginFragment).commit();
        }
        new LoginPresenter(mLoginFragment, Injection.provideRepository(this));
    }

    private void initToolbar() {
        setSupportActionBar(tbToolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);
        tvTitle.setText(R.string.login);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.register:
                startActivity(RegisterActivity.getStartIntent(this));
                return true;
            case android.R.id.home:
                finish();
                return true;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public static Intent getStartIntent(Activity activity) {
        Intent intent = new Intent(activity, LoginActivity.class);
        return intent;
    }
}
