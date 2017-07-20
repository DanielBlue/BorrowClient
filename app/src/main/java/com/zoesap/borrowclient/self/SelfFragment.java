package com.zoesap.borrowclient.self;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zoesap.borrowclient.R;
import com.zoesap.borrowclient.util.NullUtils;

/**
 * Created by maoqi on 2017/7/18.
 */

public class SelfFragment extends Fragment implements SelfContract.View {
    SelfContract.Presenter mPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_self, null);

    }

    @Override
    public void setPresent(@NonNull SelfContract.Presenter presenter) {
        mPresenter = NullUtils.checkNotNull(presenter);
        NullUtils.checkNotNull(mPresenter);
    }

    @Override
    public void showNetError() {

    }

    @Override
    public void showLoadindDialog() {

    }

    @Override
    public void loadingDialogDismiss() {

    }
}
