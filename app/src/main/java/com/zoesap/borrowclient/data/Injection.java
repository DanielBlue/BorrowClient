package com.zoesap.borrowclient.data;

import android.content.Context;
import android.support.annotation.NonNull;

import com.zoesap.borrowclient.data.source.Repository;
import com.zoesap.borrowclient.data.source.remote.RemoteDataSource;
import pers.maoqi.core.sp.SpHelper;
import pers.maoqi.core.util.NullUtils;

/**
 * Created by maoqi on 2017/7/18.
 */

public class Injection {
    public static Repository provideRepository(@NonNull Context context) {
        NullUtils.checkNotNull(context);
        return Repository.getInstance(RemoteDataSource.getInstance(context), SpHelper.getInstance(context));
    }
}
