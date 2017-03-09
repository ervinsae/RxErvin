package com.ervin.android.rxervin;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Ervin on 2017/3/9.
 */

public class MeizhiImageActivity extends BaseActivity {

    @BindView(R.id.iv_meizhi)
    ImageView ivMeizhi;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meizhi_image);
        ButterKnife.bind(this);

        initView();
    }

    @Override
    protected void setToolbar() {
        toolbarTitle.setText("妹子");
    }

    private void initView(){
        String url = getIntent().getStringExtra("url");
        Picasso.with(this).load(url).into(ivMeizhi);
    }

    @OnClick(R.id.iv_back) void onBack(){
        onBackPressed();
    }
}
