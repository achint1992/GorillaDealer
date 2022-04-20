package com.technoecorp.gorilladealer.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;

import androidx.annotation.ColorRes;
import androidx.annotation.DimenRes;


/**
 * Created by Developer on 8/9/16.
 */
public class ResourceUtils {
    public static String getString(Context context, int resId) {
        return context.getResources().getString(resId);
    }

    public static int getColor(Context context, @ColorRes int colorId) {
        return context.getResources().getColor(colorId);
    }

    public static int getDimen(Context context, @DimenRes int dimenId) {
        return (int) context.getResources().getDimension(dimenId);
    }

    public static int getScreenWidth(Activity activity) {
        Point size = new Point();
        activity.getWindowManager().getDefaultDisplay().getSize(size);
        return size.x;
    }

    public static int getScreenHeight(Activity activity) {
        Point size = new Point();
        activity.getWindowManager().getDefaultDisplay().getSize(size);
        return size.y;
    }
}
