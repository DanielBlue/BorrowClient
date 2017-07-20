package com.zoesap.borrowclient.adapter;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by maoqi on 2017/6/5.
 */

public class SpacesItemDecoration extends RecyclerView.ItemDecoration{
    int v;
    int h;


    /**
     *
     * @param v top and bottom
     * @param h left and right
     */
    public SpacesItemDecoration(int v,int h) {
        this.v = v;

        this.h = h;

    }

    public SpacesItemDecoration(int space) {
        this.v = space;

        this.h = space;

    }

    @Override
    public void getItemOffsets(Rect outRect, View view,
                               RecyclerView parent, RecyclerView.State state) {
        outRect.left = h;
        outRect.right = h;
        outRect.bottom = v;

        // Add top margin only for the first item to avoid double space between items
        if (parent.getChildPosition(view) == 0)
            outRect.top = v;
    }
}
