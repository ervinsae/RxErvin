package com.ervin.android.rxervin.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Ervin on 2016/8/10.
 */
public class BaseEntity implements Parcelable {

    @SerializedName("description")
    public String description;
    public String created_at;
    public String image_url;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.description);
        dest.writeString(this.created_at);
        dest.writeString(this.image_url);
    }

    public BaseEntity() {
    }

    protected BaseEntity(Parcel in) {
        this.description = in.readString();
        this.created_at = in.readString();
        this.image_url = in.readString();
    }

    public static final Parcelable.Creator<BaseEntity> CREATOR = new Parcelable.Creator<BaseEntity>() {
        @Override
        public BaseEntity createFromParcel(Parcel source) {
            return new BaseEntity(source);
        }

        @Override
        public BaseEntity[] newArray(int size) {
            return new BaseEntity[size];
        }
    };
}
