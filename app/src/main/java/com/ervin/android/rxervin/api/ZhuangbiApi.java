package com.ervin.android.rxervin.api;

import com.ervin.android.rxervin.entity.BaseEntity;
import com.ervin.android.rxervin.entity.ZhuangbiEntity;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;
import retrofit2.http.Streaming;
import retrofit2.http.Url;
import rx.Observable;

/**
 * Created by Ervin on 2016/8/10.
 */
public interface ZhuangbiApi {

    @GET("search")
    Observable<List<ZhuangbiEntity>> search(@Query("q") String query);



    //模拟一个下载API
    /*
      url由于是可变的，因此用 @URL 注解符号来进行指定，大文件官方建议用 @Streaming 来进行注解，不然会出现IO异常，小文件可以忽略不注入。
      如果想进行断点续传的话 可以在此加入header，但不建议直接在api中写死，每个下载的请求大小是不同的，在拦截器加入更为妥善。
    */
    @Streaming
    @GET
    Observable<BaseEntity> downLoadFile(@Header("Range") String range,@Url String fileUrl);
}
