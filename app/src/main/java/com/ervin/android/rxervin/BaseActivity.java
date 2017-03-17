package com.ervin.android.rxervin;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import rx.Subscription;

/**
 * Created by Ervin on 2016/8/12.
 */
public abstract class BaseActivity extends AppCompatActivity {

    @Nullable
    @BindView(R.id.tv_title)
    TextView toolbarTitle;
    @Nullable
    @BindView(R.id.iv_back)
    ImageView toolbarLeft;
    @Nullable
    @BindView(R.id.toolbar_right)
    TextView toolbarRight;

    protected abstract void setToolbar();

    protected Subscription subscription;

    protected void unSubscribe() {
        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unSubscribe();
    }
}
