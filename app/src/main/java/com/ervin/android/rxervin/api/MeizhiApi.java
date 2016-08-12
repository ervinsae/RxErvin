package com.ervin.android.rxervin.api;

import com.ervin.android.rxervin.entity.AndroidGankEntity;
import com.ervin.android.rxervin.entity.MeizhiEntity;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by Ervin on 2016/8/11.
 */
public interface MeizhiApi {
    //Gank.io API 进行请求
    @GET("福利/{number}/{page}")
    Observable<MeizhiEntity> getMeizhiData(@Path("number") int number ,@Path("page") int page);   //https://gank.io/api/data/福利/10/1

    @GET("Android/{number}/{page}")
    Observable<List<AndroidGankEntity>> getAndroidGank(@Path("number") int number ,@Path("page") int page);
}
