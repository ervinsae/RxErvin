package com.ervin.android.rxervin.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Ervin on 2017/3/16.
 */

public class GankEntity {

    @SerializedName("results")
    public List<Gank> results;

    public class Gank{
        @SerializedName("desc")
        public String desc;
        @SerializedName("ganhuo_id")
        public String id;
        @SerializedName("publishedAt")
        public String publishTime;
        @SerializedName("readability")
        public String readability;
        @SerializedName("type")
        public String type;
        @SerializedName("url")
        public String url;
        @SerializedName("who")
        public String who;

    }

}
