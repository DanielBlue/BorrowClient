package com.zoesap.borrowclient.adapter;

/**
 * Created by maoqi on 2017/7/19.
 */

public interface AdapterContract {
    interface ListItemClickListener {
        void onItemClickListener(int position);
    }

    class MyLoanMultiItem{
        public static final int LOAN_LIST_EMPTY = 100;
        public static final int LOAN_LIST = 101;
        public static final int RECOMMEND_LOAN_LIST = 102;
        public static final int RECOMMEND_LOAN_LIST_HEADER = 103;
    }
}
