package com.ervin.android.rxervin.entity;

import java.util.List;

/**
 * Created by Ervin on 2016/8/12.
 */
public class AndroidGankEntity{

    public DayData results;

    public static class DayData{
        public List<Meizhis> Android;
        public List<Meizhis> iOS;
    }

    public String error;
}
