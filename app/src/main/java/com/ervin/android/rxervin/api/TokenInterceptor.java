package com.ervin.android.rxervin.api;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Ervin on 2016/11/16.
 */

public class TokenInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();

        HttpUrl urlGet = request.url().newBuilder()
                .addQueryParameter("commonParams1","params1")
                .addQueryParameter("commonParams2","params2").build();//get请求

        Request.Builder requestBuilder = request.newBuilder()//token请求头
                .addHeader("Authorization","token");

        request = requestBuilder.url(urlGet).build();
        return chain.proceed(request);
    }
}
