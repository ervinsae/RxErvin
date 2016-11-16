package com.ervin.android.rxervin.api;

import com.ervin.android.rxervin.App;
import com.ervin.android.rxervin.utils.NetworkHelper;

import java.io.IOException;

import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Ervin on 2016/11/15.
 */

public class CacheInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();

        //没有网络
        if(!NetworkHelper.isNetworkConnected(App.getInstance().getApplicationContext())){
            request = request.newBuilder().cacheControl(CacheControl.FORCE_CACHE).build();
        }
        Response response = chain.proceed(request);

        if(NetworkHelper.isNetworkConnected(App.getInstance().getApplicationContext())){
            // 有网络时 设置缓存超时时间0个小时
            int maxAge = 0;
            return response.newBuilder().header("Cache-Control","public,max-age="+maxAge)
                    .removeHeader("").build();
        }else{
            // 无网络时，设置超时为2周
            int maxAge = 60 * 60 * 24 * 14;
            return response.newBuilder().header("Cache-Control","public,cached,max-age="+maxAge)
                    .removeHeader("").build();
        }
    }
}
