package com.zoesap.borrowclient.adapter;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.List;

/**
 * Created by maoqi on 2017/7/21.
 */

public class MyLoanListAdapter extends BaseMultiItemQuickAdapter<MultiItemEntity,BaseViewHolder> {

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public MyLoanListAdapter(List<MultiItemEntity> data) {
        super(data);
        addItemType();
        addItemType();
        addItemType();
    }

    @Override
    protected void convert(BaseViewHolder helper, MultiItemEntity item) {

    }
}
