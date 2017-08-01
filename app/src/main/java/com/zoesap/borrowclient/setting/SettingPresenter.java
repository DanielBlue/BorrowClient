package com.zoesap.borrowclient.setting;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.zoesap.borrowclient.R;
import com.zoesap.borrowclient.data.source.Repository;
import com.zoesap.borrowclient.util.NullUtils;

/**
 * Created by maoqi on 2017/7/25.
 */

public class SettingPresenter implements SettingContract.Presenter {
    private final SettingContract.View mSettingView;
    private final Repository mRepository;

    public SettingPresenter(SettingContract.View mSettingView, Repository mRepository) {
        this.mSettingView = NullUtils.checkNotNull(mSettingView);
        this.mRepository = NullUtils.checkNotNull(mRepository);

        mSettingView.setPresent(this);
    }


    @Override
    public void start() {
        mSettingView.showVersionCode(getVersion());
    }

    public String getVersion() {
        try {
            PackageManager manager = mSettingView.getParentActivity().getPackageManager();
            PackageInfo info = manager.getPackageInfo(mSettingView.getParentActivity().getPackageName(), 0);
            String version = info.versionName;
            return mSettingView.getParentActivity().getString(R.string.version_name) + version;
        } catch (Exception e) {
            e.printStackTrace();
            return mSettingView.getParentActivity().getString(R.string.can_not_find_version_name);
        }
    }

    @Override
    public void clearAccountAndPassword() {
        mRepository.savePassword2Sp("");
    }
}
