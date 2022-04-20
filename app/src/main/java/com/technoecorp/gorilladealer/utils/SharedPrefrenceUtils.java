package com.technoecorp.gorilladealer.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;

/**
 * Created by developer on 04/07/16.
 */
public class SharedPrefrenceUtils {
    //Registration
    public static final String dealerBean = "dealerBean";
    public static final String distributerBean = "distributerBean";
    public static final String lastDashBoardObject = "lastDashBoardObject";
    public static final String isLoggedIn = "isLoggedIn";
    public static final String isVerified = "isVerified";
    public static final String countryCode = "countryCode";
    public static final String dealerId = "dealerId";
    public static final String referCodeByFirebase = "referCodeByFirebase";

    public static void savePreferences(Context context, String key, String value) {
        SharedPreferences sp = PreferenceManager
                .getDefaultSharedPreferences(context);
        SharedPreferences.Editor sp_edit = sp.edit();
        sp_edit.putString(key, value);
        sp_edit.commit();
    }

    public static void saveObjectPreferences(Context context, String key, Object value) {
        Gson gson=new Gson();
        String jsonString=gson.toJson(value);
        SharedPreferences sp = PreferenceManager
                .getDefaultSharedPreferences(context);
        SharedPreferences.Editor sp_edit = sp.edit();
        sp_edit.putString(key, jsonString);
        sp_edit.commit();
    }



    public static void savePreferences(Context context, String key, long value) {
        SharedPreferences sp = PreferenceManager
                .getDefaultSharedPreferences(context);
        SharedPreferences.Editor sp_edit = sp.edit();
        sp_edit.putLong(key, value);
        sp_edit.commit();
    }

    public static void clearPreferences(Context context) {
        SharedPreferences sp = PreferenceManager
                .getDefaultSharedPreferences(context);
        SharedPreferences.Editor sp_edit = sp.edit();
        sp_edit.clear();
        sp_edit.commit();
    }

    public static void savePreferences(Context context, String key, int value) {
        SharedPreferences sp = PreferenceManager
                .getDefaultSharedPreferences(context);
        SharedPreferences.Editor sp_edit = sp.edit();
        sp_edit.putInt(key, value);
        sp_edit.commit();
    }

    public static void savePreferences(Context context, String key, boolean value) {
        SharedPreferences sp = PreferenceManager
                .getDefaultSharedPreferences(context);
        SharedPreferences.Editor sp_edit = sp.edit();
        sp_edit.putBoolean(key, value);
        sp_edit.commit();
    }

    public static String loadSavedPreferences(Context context, String key, String defaultValue) {
        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(context);
        return sharedPreferences.getString(key, defaultValue);

    }

    public static int loadSavedPreferences(Context context, String key, int defaultValue) {
        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(context);
        return sharedPreferences.getInt(key, defaultValue);

    }

    public static long loadSavedPreferences(Context context, String key, long defaultValue) {
        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(context);
        return sharedPreferences.getLong(key, defaultValue);
    }


    public static boolean loadSavedPreferences(Context context, String key, boolean defaultValue) {
        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(context);
        return sharedPreferences.getBoolean(key, defaultValue);

    }


}
