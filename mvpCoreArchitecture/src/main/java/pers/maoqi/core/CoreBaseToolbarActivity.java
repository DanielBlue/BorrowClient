package pers.maoqi.core;

import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;

/**
 * Created by maoqi on 2017/8/2.
 */

public class CoreBaseToolbarActivity extends CoreBaseActivity {

    void initReturnIcon(Toolbar toolbar) {
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);
    }
}
