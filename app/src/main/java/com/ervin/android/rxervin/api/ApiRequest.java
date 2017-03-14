package com.ervin.android.rxervin.api;

import com.ervin.android.rxervin.App;
import com.ervin.android.rxervin.BuildConfig;

import java.io.File;
import java.io.IOException;

import okhttp3.Authenticator;
import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Route;
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
        //如果你需要在遇到诸如 401 Not Authorised 的时候进行刷新 token，可以使用 Authenticator，这是一个专门设计用于当验证出现错误的时候，进行询问获取处理的拦截器：
        Authenticator mAuthenticator = new Authenticator() {
            @Override
            public Request authenticate(Route route, Response response) throws IOException {
                //刷新token
                //your.token = service.token;
                return response.request().newBuilder().addHeader("Authorization","token").build();
            }
        };
        builder.authenticator(mAuthenticator);

        //网络请求接口中携带token
        builder.addNetworkInterceptor(new TokenInterceptor());


        //可以在请求中添加统一的header
        builder.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request().newBuilder()
                        .addHeader("range","")//支持断点下载
                        .addHeader("type","")
                        .build();
                return chain.proceed(request);
            }
        });

        //支持自建证书，支持https（12306证书实验）
        builder.sslSocketFactory(CertificationFactory.getSLLContext(App.getInstance().getApplicationContext()).getSocketFactory());
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
