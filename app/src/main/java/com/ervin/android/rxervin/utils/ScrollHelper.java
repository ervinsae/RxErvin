package com.ervin.android.rxervin.utils;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

/**
 * Created by Ervin on 2016/8/15.
 */
public class ScrollHelper {

    public interface OnScrollStateChangedListener{
        void onScrollToBottom();
    }

    public static void init(RecyclerView recyclerView, final RecyclerView.LayoutManager layoutManager,final OnScrollStateChangedListener callback){
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

            private boolean scrollDown = false;
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                switch (newState){
                    case RecyclerView.SCROLL_STATE_IDLE:
                        int LastVisibleItem ;
                        if(layoutManager instanceof StaggeredGridLayoutManager){
                            //最后一列是单数
                            LastVisibleItem = ((StaggeredGridLayoutManager) layoutManager).findLastCompletelyVisibleItemPositions(new int[2])[0];
                        }else if(layoutManager instanceof GridLayoutManager){
                            LastVisibleItem = ((GridLayoutManager) layoutManager).findLastCompletelyVisibleItemPosition();
                        }else{
                            LastVisibleItem = ((LinearLayoutManager)layoutManager).findLastCompletelyVisibleItemPosition();
                        }

                        int totalItemCount = layoutManager.getItemCount();
                        if((LastVisibleItem >= (totalItemCount - 1)) && scrollDown){
                            if(callback != null){
                                callback.onScrollToBottom();
                            }
                        }
                        break;
                    case RecyclerView.SCROLL_STATE_DRAGGING:
                        break;
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                scrollDown = dy > 0;
            }
        });
    }
}
