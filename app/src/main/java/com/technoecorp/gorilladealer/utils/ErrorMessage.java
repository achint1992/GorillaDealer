package com.technoecorp.gorilladealer.utils;

import android.util.Log;

public class ErrorMessage {
    public static String tag="Error";

    public static void showError(Exception e){
        Log.e(tag, e.getMessage());
    }
}
