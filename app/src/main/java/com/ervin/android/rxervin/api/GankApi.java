package com.ervin.android.rxervin.api;

import com.ervin.android.rxervin.entity.AndroidGankEntity;
import com.ervin.android.rxervin.entity.MeizhiEntity;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by Ervin on 2016/8/11.
 */
public interface GankApi {
    //Gank.io API 进行请求
    @GET("data/福利/{number}/{page}")
    Observable<MeizhiEntity> getMeizhiData(@Path("number") int number ,@Path("page") int page);   //https://gank.io/api/data/福利/10/1

    @GET("data/Android/{number}/{page}")
    Observable<MeizhiEntity> getAndroidGank(@Path("number") int number ,@Path("page") int page);

    @GET("day/{year}/{month}/{day}")
    Observable<AndroidGankEntity> getAndroidDayGank(@Path("yera") int year,@Path("month") int month,@Path("day") int day);
}
