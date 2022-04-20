package com.technoecorp.gorilladealer.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.Gson;
import com.technoecorp.gorilladealer.R;
import com.technoecorp.gorilladealer.bean.LoginBean.LoginResponse;
import com.technoecorp.gorilladealer.bean.RegistrationBean.RegistrationResponse;
import com.technoecorp.gorilladealer.dialog.CustomDialogClass;
import com.technoecorp.gorilladealer.interfaces.OkHttpCustomResponse;
import com.technoecorp.gorilladealer.utils.APICallConstants;
import com.technoecorp.gorilladealer.utils.HttpCall;
import com.technoecorp.gorilladealer.utils.NetworkUtil;
import com.technoecorp.gorilladealer.utils.ResourceUtils;
import com.technoecorp.gorilladealer.utils.SharedPrefrenceUtils;
import com.technoecorp.gorilladealer.utils.ToastUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Response;


public class RegistrationActivity extends AppCompatActivity {
    @BindView(R.id.registerButton)
    MaterialButton registerButton;
    @BindView(R.id.loginButton)
    MaterialButton loginButton;
    @BindView(R.id.userName)
    TextInputEditText userName;
    @BindView(R.id.userMobile)
    TextInputEditText userMobile;
    @BindView(R.id.userEmail)
    TextInputEditText userEmail;
    @BindView(R.id.refCode)
    TextInputEditText refCode;
    Pattern pattern = Pattern.compile("^(.+)@(.+)$");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        ButterKnife.bind(this);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = userName.getText().toString();
                String mobile = userMobile.getText().toString();
                String email = userEmail.getText().toString();
                String refCodeText = refCode.getText().toString();
                if (isValidation(name, mobile, email, refCodeText)) {
                    signUp(name, mobile, email, refCodeText);
                }
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegistrationActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        if(!SharedPrefrenceUtils.loadSavedPreferences(RegistrationActivity.this,SharedPrefrenceUtils.referCodeByFirebase,"").equalsIgnoreCase("")){
            refCode.setText(SharedPrefrenceUtils.loadSavedPreferences(RegistrationActivity.this,SharedPrefrenceUtils.referCodeByFirebase,""));
        }
    }

    boolean isValidation(String name, String mobile, String email, String refCodeText) {
        Matcher matcher = pattern.matcher(email);
        if (name.equalsIgnoreCase("")) {
            ToastUtil.showToast(RegistrationActivity.this, "Full Name is required");
            return false;
        } else if (mobile.equalsIgnoreCase("") || mobile.length() < 10) {
            ToastUtil.showToast(RegistrationActivity.this, "Mobile Number is required");
            return false;
        } else if (email.equalsIgnoreCase("")) {
            ToastUtil.showToast(RegistrationActivity.this, "Email is required");
            return false;
        } else if (!matcher.matches()) {
            ToastUtil.showToast(RegistrationActivity.this, "Valid Email id is required");
            return false;
        } else if (refCodeText.equalsIgnoreCase("")) {
            ToastUtil.showToast(RegistrationActivity.this, "Refer Code is required");
            return false;
        }
        return true;
    }

    void login(String mobile, String code) {
        try {
            if (NetworkUtil.isNetworkConnected(RegistrationActivity.this)) {
                final CustomDialogClass customDialogClass = new CustomDialogClass(RegistrationActivity.this);
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
                            Intent otpIntent = new Intent(RegistrationActivity.this, OtpActivity.class);
                            otpIntent.putExtra("ref", registerBean.getRefId());
                            otpIntent.putExtra("id", registerBean.getId());
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
                                    ToastUtil.showToast(RegistrationActivity.this, registerBean.getMessage() + "");
                                }
                            });
                        }
                    }

                    @Override
                    public void onFailure(Call call, IOException e) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                ToastUtil.showToast(RegistrationActivity.this, "Invalid Username password..");
                                customDialogClass.dismiss();
                            }
                        });
                    }
                });

                Map<String, Object> postData = new HashMap<>();
                postData.put("username", mobile);
                postData.put("countryDialCode", code);

                call.callNewHTTP(postData, APICallConstants.signInApiRequest, RegistrationActivity.this);
            } else {
                ToastUtil.showToast(RegistrationActivity.this, ResourceUtils.getString(RegistrationActivity.this, R.string.internet_connection));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    void signUp(String username, String mobile, String email, String refCode) {
        try {
            if (NetworkUtil.isNetworkConnected(RegistrationActivity.this)) {
                final CustomDialogClass customDialogClass = new CustomDialogClass(RegistrationActivity.this);
                customDialogClass.show();
                HttpCall call = new HttpCall();
                call.setOkHttpCustomResponse(new OkHttpCustomResponse() {
                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String responseData = response.body().string();
                        Log.e("already response is", responseData);
                        Gson gson = new Gson();

                        final RegistrationResponse registerBean = gson.fromJson(responseData, RegistrationResponse.class);
                        if (registerBean.getStatusCode() == 403) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    customDialogClass.dismiss();
                                    ToastUtil.showToast(RegistrationActivity.this, registerBean.getMessage() + "");
                                }
                            });
                        } else {
                            if (registerBean.getStatus()) {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        login(mobile, "+91");
                                        customDialogClass.dismiss();
                                    }
                                });
                            } else {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        customDialogClass.dismiss();
                                        ToastUtil.showToast(RegistrationActivity.this, registerBean.getMessage() + "");
                                    }
                                });
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call call, IOException e) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                ToastUtil.showToast(RegistrationActivity.this, "Invalid Username password..");
                                customDialogClass.dismiss();
                            }
                        });
                    }
                });

                Map<String, Object> postData = new HashMap<>();
                postData.put("name", username);
                postData.put("mobileNo", mobile);
                postData.put("email", email);
                postData.put("type", "dealer");
                postData.put("referBy", refCode);

                call.callNewHTTP(postData, APICallConstants.signUpApiRequest, RegistrationActivity.this);
            } else {
                ToastUtil.showToast(RegistrationActivity.this, ResourceUtils.getString(RegistrationActivity.this, R.string.internet_connection));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}