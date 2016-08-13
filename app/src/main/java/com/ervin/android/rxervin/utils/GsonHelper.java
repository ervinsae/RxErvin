package com.ervin.android.rxervin.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by think on 2016/3/3.
 */
public class GsonHelper {

    public static <T> T fromJson(InputStream inputStream, Type type) {
        if (inputStream == null) return null;
        InputStreamReader reader = new InputStreamReader(inputStream);
        GsonBuilder builder = new GsonBuilder();

        Gson gson = builder.create();
        try {
            return gson.fromJson(reader, type);
        } catch (Exception ex) {
            return null;
        }
    }

    public static JSONArray collectionToJSONArray(List<?> src){
        Gson gson = new GsonBuilder().create();
        JsonArray jsonArray = gson.toJsonTree(src).getAsJsonArray();
        try {
            return  new JSONArray(jsonArray.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return new JSONArray();
    }
}
