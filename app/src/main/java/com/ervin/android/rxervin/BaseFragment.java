package com.ervin.android.rxervin;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import rx.Subscription;

/**
 * Created by Ervin on 2016/8/10.
 */
public abstract class BaseFragment extends Fragment {

    @Nullable
    @BindView(R.id.tv_title)
    TextView toolbarTitle;
    @Nullable
    @BindView(R.id.iv_back)
    ImageView toolbarLeft;
    protected abstract void setToolbar();

    protected Subscription subscription;

    protected void unSubscribe(){
        if(subscription != null && !subscription.isUnsubscribed()){
            subscription.unsubscribe();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unSubscribe();
    }
}
