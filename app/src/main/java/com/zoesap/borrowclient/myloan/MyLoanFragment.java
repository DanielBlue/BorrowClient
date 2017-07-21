package com.zoesap.borrowclient.myloan;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zoesap.borrowclient.R;
import com.zoesap.borrowclient.data.bean.MyLoanBean;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by maoqi on 2017/7/21.
 */

public class MyLoanFragment extends Fragment implements MyLoanContract.View {

    @BindView(R.id.rv_list)
    RecyclerView rvList;
    Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_loan, null);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void setPresent(@NonNull MyLoanContract.Presenter presenter) {

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

    public static MyLoanFragment newInstance() {
        return new MyLoanFragment();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void updateList(MyLoanBean.DataBean bean) {

    }
}
