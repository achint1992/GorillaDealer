package com.technoecorp.gorilladealer.activity;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.dynamiclinks.FirebaseDynamicLinks;
import com.google.firebase.dynamiclinks.PendingDynamicLinkData;
import com.technoecorp.gorilladealer.R;
import com.technoecorp.gorilladealer.utils.SharedPrefrenceUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DealerLoginActivity extends AppCompatActivity {
    String[] textList = {
            "India's Best mobile security app",
            "20+ Features Available ",
            "Become a Dealer in minimal price amount",
            "Start your earning in few simple steps",
            "Sign up now to join gorilla army"
    };
    int count = 0;
    ObjectAnimator expandAnimation;
    ObjectAnimator shrinkAnimation;
    TextView textView;
    @BindView(R.id.view6)
    View view6;
    @BindView(R.id.tvSwitch)
    TextSwitcher tvSwitch;
    @BindView(R.id.loginButton)
    MaterialButton loginButton;
    @BindView(R.id.registerButton)
    MaterialButton registerButton;
    @BindView(R.id.linear)
    LinearLayout linear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dealer_login);
        ButterKnife.bind(this);
        startAnimation();
        init();

    }

    void init() {
        boolean isVerified = SharedPrefrenceUtils.loadSavedPreferences(DealerLoginActivity.this, SharedPrefrenceUtils.isVerified, false);
        boolean isLoggedIn = SharedPrefrenceUtils.loadSavedPreferences(DealerLoginActivity.this, SharedPrefrenceUtils.isLoggedIn, false);
        if (isLoggedIn && isVerified) {
            Intent intent = new Intent(DealerLoginActivity.this, DashboardActivity.class);
            startActivity(intent);
            finish();
        }
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DealerLoginActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DealerLoginActivity.this, RegistrationActivity.class);
                startActivity(intent);
            }
        });

        FirebaseDynamicLinks.getInstance()
                .getDynamicLink(getIntent())
                .addOnSuccessListener(this, new OnSuccessListener<PendingDynamicLinkData>() {
                    @Override
                    public void onSuccess(PendingDynamicLinkData pendingDynamicLinkData) {
                        // Get deep link from result (may be null if no link is found)
                        Uri deepLink = null;
                        if (pendingDynamicLinkData != null) {
                            deepLink = pendingDynamicLinkData.getLink();
                            String referLink = deepLink.toString();
                            try {
                                Log.e("Deep link is", referLink);
                                String referId = referLink.substring(referLink.lastIndexOf("=") + 1);
                                Log.e("Refer Id is ", referId);
                                SharedPrefrenceUtils.savePreferences(DealerLoginActivity.this, SharedPrefrenceUtils.referCodeByFirebase, referId);
                            } catch (Exception e) {
                                Log.e("Expection is=", e.getMessage());
                            }
                        }


                        // Handle the deep link. For example, open the linked
                        // content, or apply promotional credit to the user's
                        // account.
                        // ...

                        // ...
                    }
                })
                .addOnFailureListener(this, new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("Dynamic links", "getDynamicLink:onFailure", e);
                    }
                });
    }


    @Override
    protected void onResume() {
        super.onResume();
        expandAnimation.resume();
        shrinkAnimation.resume();
    }

    @Override
    protected void onStop() {
        super.onStop();
        expandAnimation.pause();
        shrinkAnimation.pause();
    }

    void startAnimation() {
        expandAnimation = ObjectAnimator.ofFloat(view6, "scaleX", 1f);
        expandAnimation.setDuration(1000);
        expandAnimation.setRepeatMode(ObjectAnimator.REVERSE);
        shrinkAnimation = ObjectAnimator.ofFloat(view6, "scaleX", 0f);
        shrinkAnimation.setDuration(1000);
        shrinkAnimation.setStartDelay(2000);
        shrinkAnimation.start();
        Animation slideAnim = AnimationUtils.loadAnimation(DealerLoginActivity.this, R.anim.anim_slide_up_fade_in);
        tvSwitch.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                textView = new TextView(DealerLoginActivity.this);
                textView.setTextColor(Color.parseColor("#353535"));
                textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18f);
                textView.setGravity(Gravity.CENTER_HORIZONTAL);
                textView.setTypeface(Typeface.createFromAsset(getAssets(), "proximanova_semi.ttf"));
                return textView;
            }
        });
        tvSwitch.setInAnimation(AnimationUtils.loadAnimation(DealerLoginActivity.this, R.anim.fade_in));
        tvSwitch.setOutAnimation(AnimationUtils.loadAnimation(DealerLoginActivity.this, R.anim.fade_out));
        slideAnim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                ViewCompat.animate(view6).alpha(1F);
                ViewCompat.animate(tvSwitch).alpha(1F);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        linear.startAnimation(slideAnim);


        expandAnimation.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                shrinkAnimation.setStartDelay(0);
                shrinkAnimation.start();
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });

        shrinkAnimation.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                expandAnimation.start();
                tvSwitch.setText(textList[count]);
                if (count == 4) {
                    count = 0;
                } else {
                    count++;
                }

            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
        shrinkAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                if (valueAnimator.getAnimatedFraction() > 0.5) {
                    tvSwitch.setAlpha(1 - valueAnimator.getAnimatedFraction());
                }
            }
        });


        expandAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                tvSwitch.setAlpha(Math.abs(valueAnimator.getAnimatedFraction()));
            }
        });
    }
}