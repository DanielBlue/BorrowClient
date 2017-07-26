package com.zoesap.borrowclient;

import android.app.ProgressDialog;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.widget.Toast;

/**
 * Created by maoqi on 2017/7/25.
 */

public abstract class BaseFragment extends Fragment{
    private ProgressDialog mProgressDialog;

    public void toastInfo(String info) {
        Toast.makeText(getActivity(), info, Toast.LENGTH_SHORT).show();
    }

    public void toastInfo(@StringRes int StrId) {
        Toast.makeText(getActivity(), StrId, Toast.LENGTH_SHORT).show();

    }


    public void showLoadindDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(getActivity());
            mProgressDialog.setCanceledOnTouchOutside(false);
        }
        if (!mProgressDialog.isShowing()) {
            mProgressDialog.show();
        }
    }


    public void loadingDialogDismiss() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }
}
