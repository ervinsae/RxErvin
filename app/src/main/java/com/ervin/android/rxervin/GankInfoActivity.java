package com.ervin.android.rxervin;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.ervin.android.rxervin.widget.Loading;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Ervin on 2016/8/12.
 */
public class GankInfoActivity extends BaseActivity {

    @BindView(R.id.wv_info)
    WebView wvGank;

    Loading loading;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gankinfo);
        ButterKnife.bind(this);
        setToolbar();

        initView();
    }

    private void initView(){
        loading = new Loading(this);
        String url = getIntent().getStringExtra("url");
        wvGank.loadUrl(url);
        wvGank.setWebChromeClient(new GankChromeWebView());
        wvGank.setWebViewClient(new GankWebView());
    }
    @Override
    protected void setToolbar() {
        toolbarTitle.setText("详情");
    }

    @OnClick(R.id.iv_back) void onBack(){
        onBackPressed();
    }

    class GankWebView extends WebViewClient{
        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            loading.dismiss();
        }
    }

    class GankChromeWebView extends WebChromeClient{
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            super.onProgressChanged(view, newProgress);
            loading.show();
        }
    }
}
