package com.ervin.android.rxervin.utils;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by think on 2016/4/24.
 */
public class SpaceItemDecoration extends RecyclerView.ItemDecoration{

    private int space;

    public SpaceItemDecoration(int space) {
        this.space = space;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {

        if(parent.getChildLayoutPosition(view)%2 == 0){
            outRect.right = space /2;
            outRect.left = space;
        } else {
            outRect.left = space /2;
            outRect.right = space;
        }



        outRect.top = space;
    }
}
