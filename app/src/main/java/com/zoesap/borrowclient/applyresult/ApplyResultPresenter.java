package com.zoesap.borrowclient.applyresult;

import android.support.annotation.NonNull;

import com.zoesap.borrowclient.data.source.Repository;
import pers.maoqi.core.util.NullUtils;

/**
 * Created by maoqi on 2017/7/25.
 */

public class ApplyResultPresenter implements ApplyResultContract.Presenter {
    private final ApplyResultContract.View mApplyResultView;
    private final Repository mRepository;

    public ApplyResultPresenter(@NonNull ApplyResultContract.View view,@NonNull Repository repository) {
        mApplyResultView = NullUtils.checkNotNull(view);
        mRepository = NullUtils.checkNotNull(repository);
        mApplyResultView.setPresent(this);
    }

    @Override
    public void start() {

    }
}
