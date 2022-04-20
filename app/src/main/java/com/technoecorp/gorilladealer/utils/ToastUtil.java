package com.technoecorp.gorilladealer.utils;

import android.content.Context;
import android.widget.Toast;

public class ToastUtil {

    public static void showToast(Context context, final String msg) {

        // if(isToastNotRunning()) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();

        //}


    }

    public static void showLongToast(Context context, final String msg) {

        // if(isToastNotRunning()) {
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show();

        //}


    }
  /*  static boolean isToastNotRunning() {
        return (toast == null || toast.getView().getWindowVisibility() != View.VISIBLE);
    }*/
}
