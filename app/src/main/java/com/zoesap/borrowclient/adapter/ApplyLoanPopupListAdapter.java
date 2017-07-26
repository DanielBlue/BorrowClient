package com.zoesap.borrowclient.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zoesap.borrowclient.R;

import java.util.List;

/**
 * Created by maoqi on 2017/7/26.
 */

public class ApplyLoanPopupListAdapter extends BaseQuickAdapter<String,BaseViewHolder> {


    public ApplyLoanPopupListAdapter(@LayoutRes int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.tv_text,item)
                .addOnClickListener(R.id.tv_text);
    }
}
