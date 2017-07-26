package com.zoesap.borrowclient.applyresult;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zoesap.borrowclient.BaseFragment;
import com.zoesap.borrowclient.R;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by maoqi on 2017/7/25.
 */

public class ApplyResultFragment extends BaseFragment implements ApplyResultContract.View {

    Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_apply_result, null);
        unbinder = ButterKnife.bind(this, super.onCreateView(inflater, container, savedInstanceState));
        return view;
    }

    @Override
    public void setPresent(@NonNull ApplyResultContract.Presenter presenter) {

    }

    public static ApplyResultFragment newInstance() {
        return new ApplyResultFragment();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.rl_apply)
    public void onViewClicked() {
        getActivity().finish();
    }
}
