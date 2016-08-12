package com.ervin.android.rxervin;

import android.app.Application;

/**
 * Created by Ervin on 2016/8/10.
 */
public class App extends Application {

    private static App mInstance;
    public static App getInstance(){
        return mInstance;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }

}
