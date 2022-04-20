package com.technoecorp.gorilladealer.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;


import com.google.android.material.button.MaterialButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.Gson;
import com.technoecorp.gorilladealer.R;
import com.technoecorp.gorilladealer.bean.LoginBean.LoginResponse;
import com.technoecorp.gorilladealer.dialog.CustomDialogClass;
import com.technoecorp.gorilladealer.interfaces.OkHttpCustomResponse;
import com.technoecorp.gorilladealer.utils.APICallConstants;
import com.technoecorp.gorilladealer.utils.HttpCall;
import com.technoecorp.gorilladealer.utils.NetworkUtil;
import com.technoecorp.gorilladealer.utils.PermissionUtils;
import com.technoecorp.gorilladealer.utils.ResourceUtils;
import com.technoecorp.gorilladealer.utils.ToastUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Response;


public class LoginActivity extends AppCompatActivity {
    @BindView(R.id.userMobile)
    TextInputEditText userMobile;
    @BindView(R.id.loginButton)
    MaterialButton loginButton;
    @BindView(R.id.registerButton)
    MaterialButton registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegistrationActivity.class);
                startActivity(intent);
                finish();
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mobile = userMobile.getText().toString();
                if (!mobile.equalsIgnoreCase("") && mobile.length() == 10) {
                    login(mobile,"+91");
                } else {
                    ToastUtil.showToast(LoginActivity.this, "Please Enter Valid Mobile Number");
                }
            }
        });
    }


    void login(String mobile, String code) {
        try {
            if (NetworkUtil.isNetworkConnected(LoginActivity.this)) {
                final CustomDialogClass customDialogClass = new CustomDialogClass(LoginActivity.this);
                customDialogClass.show();
                HttpCall call = new HttpCall();
                call.setOkHttpCustomResponse(new OkHttpCustomResponse() {
                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String responseData = response.body().string();
                        Log.e("already response is", responseData);
                        Gson gson = new Gson();

                        final LoginResponse registerBean = gson.fromJson(responseData, LoginResponse.class);
                        if (registerBean.getStatus()) {
                            Intent otpIntent=new Intent(LoginActivity.this,OtpActivity.class);
                            otpIntent.putExtra("ref",registerBean.getRefId());
                            otpIntent.putExtra("id",registerBean.getId());
                            startActivity(otpIntent);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    customDialogClass.dismiss();
                                    finish();
                                }
                            });
                        }else{
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    customDialogClass.dismiss();
                                    ToastUtil.showToast(LoginActivity.this, registerBean.getMessage()+"");
                                }
                            });
                        }
                    }

                    @Override
                    public void onFailure(Call call, IOException e) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                ToastUtil.showToast(LoginActivity.this, "Invalid Username password..");
                                customDialogClass.dismiss();
                            }
                        });
                    }
                });

                Map<String, Object> postData = new HashMap<>();
                postData.put("username", mobile);
                postData.put("countryDialCode", code);

                call.callNewHTTP(postData, APICallConstants.signInApiRequest, LoginActivity.this);
            } else {
                ToastUtil.showToast(LoginActivity.this, ResourceUtils.getString(LoginActivity.this, R.string.internet_connection));
            }
        } catch (Exception e) {
            Log.e("Error",e.getMessage());
        }

    }
}