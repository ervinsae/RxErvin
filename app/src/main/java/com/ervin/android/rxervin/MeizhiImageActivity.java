package com.ervin.android.rxervin;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.ervin.android.rxervin.entity.BaseEntity;
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
        //String url = getIntent().getStringExtra("url");
        BaseEntity data = getIntent().getParcelableExtra("data");
        if(data != null) {
            Picasso.with(this).load(data.image_url).into(ivMeizhi);
            toolbarRight.setText(data.created_at);
        }
    }

    @OnClick(R.id.iv_back) void onBack(){
        onBackPressed();
    }
}
