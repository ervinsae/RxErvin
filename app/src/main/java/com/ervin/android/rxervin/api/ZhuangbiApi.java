package com.ervin.android.rxervin.api;

import com.ervin.android.rxervin.entity.ZhuangbiEntity;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Ervin on 2016/8/10.
 */
public interface ZhuangbiApi {

    @GET("search")
    Observable<List<ZhuangbiEntity>> search(@Query("q") String query);
}
