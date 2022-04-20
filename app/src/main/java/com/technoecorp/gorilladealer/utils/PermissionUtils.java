package com.technoecorp.gorilladealer.utils;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.provider.Settings;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class PermissionUtils {
    //Check Permission

    public static boolean checkStorage(Context act) {

            int result = ContextCompat.checkSelfPermission(act, Manifest.permission.WRITE_EXTERNAL_STORAGE);
            int result1 = ContextCompat.checkSelfPermission(act, Manifest.permission.READ_EXTERNAL_STORAGE);
            return (result == PackageManager.PERMISSION_GRANTED && result1 == PackageManager.PERMISSION_GRANTED);

    }

    public static boolean checkCamera(Context act) {
        int result = ContextCompat.checkSelfPermission(act, Manifest.permission.CAMERA);

        return result == PackageManager.PERMISSION_GRANTED;
    }

    public static boolean checkPhoneAccess(Context act) {
        return checkStorage(act);
    }


    //Request Permission

    public static void requestStorage(Activity act, int code) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            ActivityCompat.requestPermissions(act, new
                    String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, code);
        } else {
            ActivityCompat.requestPermissions(act, new
                    String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, code);
        }
    }

    public static void requestCamera(Activity act, int code) {
        ActivityCompat.requestPermissions(act, new
                String[]{Manifest.permission.CAMERA, Manifest.permission.RECORD_AUDIO}, code);
    }


}
