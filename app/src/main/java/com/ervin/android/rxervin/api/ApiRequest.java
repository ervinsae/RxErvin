package com.ervin.android.rxervin.api;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Ervin on 2016/8/10.
 */
public class ApiRequest {

    private static <T>T createRetrofitClient(Class<?> T, String url){
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        //Logger.json(interceptor.intercept(new Ch).body());
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(client).build();

        return (T)retrofit.create(T);
    }

    public static ZhuangbiApi getZhuangbiApi(){
        return createRetrofitClient(ZhuangbiApi.class,ConstantsUrl.ZHUANGBI_BASE_URL);
    }

    public static GankApi getMeizhiApi(){
        return createRetrofitClient(GankApi.class,ConstantsUrl.MEIZI_BASE_URL);
    }
}
