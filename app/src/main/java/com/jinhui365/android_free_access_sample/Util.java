package com.jinhui365.android_free_access_sample;

import android.content.Context;
import android.content.SharedPreferences;


import java.util.HashMap;
import java.util.Map;

public class Util {

    public static void saveUserInfo(Context context, Map<String,Object> params){
//        SharedPreferences preferences = context.getSharedPreferences("jinhui365.demo", 0);
//        SharedPreferences.Editor editor = preferences.edit();
//        editor.putString("userInfo", GsonUtil.toJson(params));
//        editor.commit();
    }

    public static Map<String, Object> getUserInfo(Context context){
//        SharedPreferences preferences = context.getSharedPreferences("jinhui365.demo", 0);
//        String json = preferences.getString("userInfo","");
//        Map<String, Object> params = GsonUtil.jsonToMap(json,String.class,Object.class);
//        return params;
        return new HashMap<>();
    }

    public static void clearUserInfo(Context context){
//        SharedPreferences preferences = context.getSharedPreferences("jinhui365.demo", 0);
//        SharedPreferences.Editor editor = preferences.edit();
//        editor.putString("userInfo", "");
//        editor.commit();
    }
}
