package com.ervin.android.rxervin.widget;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.ervin.android.rxervin.R;
import com.wang.avi.AVLoadingIndicatorView;


public class Loading extends Dialog {
    private TextView tvLoadingText;
    private AVLoadingIndicatorView loading;
    public Loading(Context context) {
        super(context, R.style.CustomLoadingDialog);
        setContentView(R.layout.dialog_loading);
        tvLoadingText = (TextView) findViewById(R.id.loading_text);
        loading = (AVLoadingIndicatorView) findViewById(R.id.av_loading);
        Window window = getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.gravity = Gravity.CENTER;
        getWindow().setAttributes(lp);
        setCancelable(false);
    }

    @Override
    public void show() {
        super.show();
        loading.show();
    }

    @Override
    public void dismiss() {
        super.dismiss();
        loading.hide();
    }

    public void setLoadingStyle(String name){
        loading.setIndicator(name);
    }
    public void setLoadingText(String loadingText) {
        this.tvLoadingText.setText(loadingText);
    }
    public void setLoadingText(int loadingText) {
        this.tvLoadingText.setText(loadingText);
    }
}
