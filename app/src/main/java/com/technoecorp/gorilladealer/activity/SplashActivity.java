package com.technoecorp.gorilladealer.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.github.florent37.viewanimator.AnimationListener;
import com.github.florent37.viewanimator.ViewAnimator;
import com.mikhaellopez.circleview.CircleView;
import com.technoecorp.gorilladealer.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SplashActivity extends AppCompatActivity {
    CircleView circleView;
    @BindView(R.id.appIcon)
    ImageView appIcon;
    @BindView(R.id.madeWith)
    ImageView madeWith;
    @BindView(R.id.companyName)
    LinearLayout companyName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        ViewAnimator
                .animate(circleView)
                .fadeIn().onStop(new AnimationListener.Stop() {
            @Override
            public void onStop() {
                appIcon.setVisibility(View.VISIBLE);
            }
        })
                .thenAnimate(appIcon).rotation(360f).onStop(new AnimationListener.Stop() {
            @Override
            public void onStop() {
                madeWith.setVisibility(View.VISIBLE);
                companyName.setVisibility(View.VISIBLE);
            }
        })
                .thenAnimate(madeWith).slideTopIn()
                .andAnimate(companyName).slideBottomIn()
                .onStop(new AnimationListener.Stop() {
                    @Override
                    public void onStop() {
                        Intent intent = new Intent(SplashActivity.this, DealerLoginActivity.class);
                        startActivity(intent);
                        finish();
                    }
                })
                .start();
    }


}