package com.technoecorp.gorilladealer.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.Gson;
import com.technoecorp.gorilladealer.R;
import com.technoecorp.gorilladealer.bean.LoginBean.LoginResponse;
import com.technoecorp.gorilladealer.bean.OTPBean.OtpResponse;
import com.technoecorp.gorilladealer.dialog.CustomDialogClass;
import com.technoecorp.gorilladealer.interfaces.OkHttpCustomResponse;
import com.technoecorp.gorilladealer.utils.APICallConstants;
import com.technoecorp.gorilladealer.utils.ErrorMessage;
import com.technoecorp.gorilladealer.utils.HttpCall;
import com.technoecorp.gorilladealer.utils.NetworkUtil;
import com.technoecorp.gorilladealer.utils.ResourceUtils;
import com.technoecorp.gorilladealer.utils.SharedPrefrenceUtils;
import com.technoecorp.gorilladealer.utils.ToastUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Response;

public class OtpActivity extends AppCompatActivity {
    @BindView(R.id.linearBack)
    LinearLayout linearBack;
    @BindView(R.id.btnContinue)
    MaterialButton btnContinue;
    @BindView(R.id.otpText)
    TextInputEditText otpText;
    int timerCount = 30;
    String randomPIN = "";
    String ref;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        ref = intent.getStringExtra("ref");
        id = intent.getIntExtra("id", -1);
        linearBack.setVisibility(View.INVISIBLE);
        if (ref == null || id == -1) {
            finish();
        }
        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String otp=otpText.getText().toString();
                if(!otp.equalsIgnoreCase("")&&otp.length()==4){
                    verifyOtp(otp);
                }else{
                    ToastUtil.showToast(OtpActivity.this,"Please Enter Valid OTP");
                }
            }
        });
    }

    void verifyOtp(String otp) {
        try {
            if (NetworkUtil.isNetworkConnected(OtpActivity.this)) {
                final CustomDialogClass customDialogClass = new CustomDialogClass(OtpActivity.this);
                customDialogClass.show();
                HttpCall call = new HttpCall();
                call.setOkHttpCustomResponse(new OkHttpCustomResponse() {
                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String responseData = response.body().string();
                        Log.e("already response is", responseData);
                        Gson gson = new Gson();

                        final OtpResponse registerBean = gson.fromJson(responseData, OtpResponse.class);
                        if (registerBean.getStatus()) {
                            SharedPrefrenceUtils.saveObjectPreferences(OtpActivity.this, SharedPrefrenceUtils.dealerBean, registerBean.getData().getData().getDealer());
                            SharedPrefrenceUtils.saveObjectPreferences(OtpActivity.this, SharedPrefrenceUtils.distributerBean, registerBean.getData().getData().getDistributor());

                            SharedPrefrenceUtils.savePreferences(OtpActivity.this, SharedPrefrenceUtils.dealerId, registerBean.getData().getData().getDealer().getDealerId());
                            SharedPrefrenceUtils.savePreferences(OtpActivity.this, SharedPrefrenceUtils.countryCode, "+91");
                            SharedPrefrenceUtils.savePreferences(OtpActivity.this, SharedPrefrenceUtils.isLoggedIn, true);
                            SharedPrefrenceUtils.savePreferences(OtpActivity.this, SharedPrefrenceUtils.isVerified, true);


                            Intent otpIntent = new Intent(OtpActivity.this, DashboardActivity.class);
                            otpIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(otpIntent);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    customDialogClass.dismiss();
                                    finish();
                                }
                            });
                        } else {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    customDialogClass.dismiss();
                                    ToastUtil.showToast(OtpActivity.this, registerBean.getMessage() + "");
                                }
                            });
                        }
                    }

                    @Override
                    public void onFailure(Call call, IOException e) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                ToastUtil.showToast(OtpActivity.this, "Invalid OTP..");
                                customDialogClass.dismiss();
                            }
                        });
                    }
                });

                Map<String, Object> postData = new HashMap<>();
                postData.put("refId", ref);
                postData.put("id", id);
                postData.put("type","login");
                postData.put("otp", otp);

                call.callNewHTTP(postData, APICallConstants.verifyOTP, OtpActivity.this);
            } else {
                ToastUtil.showToast(OtpActivity.this, ResourceUtils.getString(OtpActivity.this, R.string.internet_connection));
            }
        } catch (Exception e) {
            ErrorMessage.showError(e);
        }

    }

}