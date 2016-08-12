package com.ervin.android.rxervin.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Ervin on 2016/8/10.
 */
public class BaseEntity {

    @SerializedName("description")
    public String description;
    public String created_at;
    public String image_url;
}
