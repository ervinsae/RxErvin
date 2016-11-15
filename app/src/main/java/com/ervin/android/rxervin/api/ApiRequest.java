package com.ervin.android.rxervin.api;

import com.ervin.android.rxervin.App;
import com.ervin.android.rxervin.BuildConfig;
import com.ervin.android.rxervin.CacheInterceptor;

import java.io.File;

import okhttp3.Cache;
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
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        if(BuildConfig.DEBUG) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(interceptor);//可以写多个拦截器
        }
        //设置缓存拦截器
        CacheInterceptor cacheInterceptor = new CacheInterceptor();
        File cacheFile = new File(App.getInstance().getApplicationContext().getCacheDir(),"RxErvin");//外部公共public缓存目录
        Cache cache = new Cache(cacheFile,1024 * 1024 * 5);
        builder.cache(cache).addInterceptor(cacheInterceptor);

        // TODO: 2016/11/15 可以做一些超时设置
        builder.retryOnConnectionFailure(true);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(builder.build()).build();

        return (T)retrofit.create(T);
    }

    public static ZhuangbiApi getZhuangbiApi(){
        return createRetrofitClient(ZhuangbiApi.class,ConstantsUrl.ZHUANGBI_BASE_URL);
    }

    public static GankApi getMeizhiApi(){
        return createRetrofitClient(GankApi.class,ConstantsUrl.MEIZI_BASE_URL);
    }
}
