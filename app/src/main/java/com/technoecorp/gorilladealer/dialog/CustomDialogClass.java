package com.technoecorp.gorilladealer.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Window;
import android.view.WindowManager;

import com.technoecorp.gorilladealer.R;


/**
 * Created by achint on 3/25/17.
 */

public class CustomDialogClass extends Dialog {

    private Activity c;
    private Dialog d;


    public CustomDialogClass(Activity a) {
        super(a);
        // TODO Auto-generated constructor stub
        this.c = a;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.loader_layout);
        WindowManager wm = (WindowManager) c.getSystemService(Context.WINDOW_SERVICE);
        getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        Display display = wm.getDefaultDisplay();
        setCancelable(false);
        DisplayMetrics metrics = new DisplayMetrics();
        display.getMetrics(metrics);
        int orientation = c.getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            Double width = metrics.widthPixels * .7;
            Double height = metrics.heightPixels * .2;
            Window win = this.getWindow();
            win.setLayout(width.intValue(), height.intValue());
        } else {
            Double width = metrics.widthPixels * .5;
            Double height = metrics.heightPixels * .26;
            Window win = this.getWindow();
            win.setLayout(width.intValue(), height.intValue());
        }

    }
}