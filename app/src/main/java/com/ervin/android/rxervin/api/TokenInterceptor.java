package com.ervin.android.rxervin.api;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Ervin on 2016/11/16.
 */

//用来携带token请求给服务器的拦截器
public class TokenInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();

        //可以添加一些公共的请求参数
        HttpUrl urlGet = request.url().newBuilder()
                .addQueryParameter("commonParams1","params1")
                .addQueryParameter("commonParams2","params2").build();//get请求

        Request.Builder requestBuilder = request.newBuilder()//token请求头
                .addHeader("Authorization","your local token");

        request = requestBuilder.url(urlGet).build();
        return chain.proceed(request);
    }
}
