package pers.maoqi.core;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;

/**
 * Created by maoqi on 2017/6/27.
 */

public interface CoreBaseView<T> {
    void setPresent(@NonNull T presenter);

    void toastInfo(String info);

    void toastInfo(@StringRes int StrId);

    void showLoadindDialog();

    void loadingDialogDismiss();

    Context getContext();
}
