package com.technoecorp.gorilladealer.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.Gson;
import com.technoecorp.gorilladealer.R;
import com.technoecorp.gorilladealer.adapter.CityListAdapter;
import com.technoecorp.gorilladealer.adapter.CountryListAdapter;
import com.technoecorp.gorilladealer.adapter.StateListAdapter;
import com.technoecorp.gorilladealer.bean.OTPBean.Dealer;
import com.technoecorp.gorilladealer.bean.OTPBean.OtpResponse;
import com.technoecorp.gorilladealer.bean.cityResponse.CityBean;
import com.technoecorp.gorilladealer.bean.cityResponse.CityResponseBean;
import com.technoecorp.gorilladealer.bean.stateResponse.CountryBean;
import com.technoecorp.gorilladealer.bean.stateResponse.CountryResponseBean;
import com.technoecorp.gorilladealer.bean.stateResponse.StateBean;
import com.technoecorp.gorilladealer.bean.stateResponse.StateResponseBean;
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
import java.net.ProtocolException;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Response;

public class EditProfileActivity extends AppCompatActivity {
    @BindView(R.id.fatherName)
    TextInputEditText fatherName;
    @BindView(R.id.dob)
    TextInputEditText dob;
    @BindView(R.id.userEmail)
    TextInputEditText userEmail;
    @BindView(R.id.registerButton)
    MaterialButton registerButton;
    @BindView(R.id.userCountry)
    AutoCompleteTextView userCountry;
    @BindView(R.id.userAltMobile)
    TextInputEditText userAltMobile;
    @BindView(R.id.userAddress)
    TextInputEditText userAddress;
    @BindView(R.id.userState)
    AutoCompleteTextView userState;
    @BindView(R.id.userCity)
    AutoCompleteTextView userCity;
    @BindView(R.id.userDistrict)
    TextInputEditText userDistrict;
    @BindView(R.id.userPincode)
    TextInputEditText userPincode;
    @BindView(R.id.linearBack)
    LinearLayout linearBack;
    int countryId = -1;
    int stateId = -1;
    int cityId = -1;
    Dealer dealer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        ButterKnife.bind(this);
        Gson gson = new Gson();
        String dealerBean = SharedPrefrenceUtils.loadSavedPreferences(this, SharedPrefrenceUtils.dealerBean, "");
        dealer = gson.fromJson(dealerBean, Dealer.class);
        if (dealer == null ) {
            finish();
        }
        init();
    }

    void init() {
        linearBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateProfile();
            }
        });

        userCountry.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View arg1, int position, long arg3) {
                Object item = parent.getItemAtPosition(position);
                if (item instanceof CountryBean) {
                    CountryBean stateBean = (CountryBean) item;
                    Log.e("state bean is", stateBean.toString());
                    countryId = stateBean.getId();
                    stateId = -1;
                    cityId = -1;
                    userCity.setText("");
                    userState.setText("");
                    getStateList(EditProfileActivity.this, countryId);
                } else {
                    countryId = -1;
                }
            }
        });

        userState.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View arg1, int position, long arg3) {
                Object item = parent.getItemAtPosition(position);
                if (item instanceof StateBean) {
                    StateBean stateBean = (StateBean) item;
                    Log.e("state bean is", stateBean.toString());
                    stateId = stateBean.getStateId();
                    cityId = -1;
                    userCity.setText("");
                    getCityList(EditProfileActivity.this, stateId);
                } else {
                    stateId = -1;
                }
            }
        });

        userCity.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View arg1, int position, long arg3) {
                Object item = parent.getItemAtPosition(position);
                if (item instanceof CityBean) {
                    CityBean stateBean = (CityBean) item;
                    Log.e("city bean is", stateBean.toString());
                    cityId = stateBean.getCityId();
                } else {
                    cityId = -1;
                }
            }
        });
        getCountryList(EditProfileActivity.this);
        setDataOnUI();
    }

    void setDataOnUI(){
        if(dealer.getCountry()!=null){
            if(dealer.getCountry().getCountryName()!=null){
                userCountry.setText(dealer.getCountry().getCountryName());
            }
        }

        if(dealer.getState()!=null){
            if(dealer.getState().getStateName()!=null){
                userState.setText(dealer.getState().getStateName());
            }
        }

        if(dealer.getCity()!=null){
            if(dealer.getCity().getCityName()!=null){
                userCity.setText(dealer.getCity().getCityName());
            }
        }

        userEmail.setText(dealer.getEmail());
        userAltMobile.setText(dealer.getAlternetMobileNo1());
        userAddress.setText(dealer.getUserAddress());
        userDistrict.setText(dealer.getDistrictName());
        userPincode.setText(dealer.getPincode());
        fatherName.setText(dealer.getFatherName());
        dob.setText(dealer.getDob());
    }

    void getStateList(final Context context, int countryId) {
        try {
            if (NetworkUtil.isNetworkConnected(context)) {
                final CustomDialogClass customDialogClass = new CustomDialogClass(EditProfileActivity.this);
                customDialogClass.show();
                HttpCall call = new HttpCall();
                call.setOkHttpCustomResponse(new OkHttpCustomResponse() {
                    @Override
                    public void onResponse(Call call, Response response) throws IOException {

                        String responseData = response.body().string();
                        Log.e("already response is", responseData);
                        Gson gson = new Gson();
                        final StateResponseBean stateResponseBean = gson.fromJson(responseData, StateResponseBean.class);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                customDialogClass.dismiss();
                                StateListAdapter stateListAdapter = new StateListAdapter(EditProfileActivity.this, R.layout.state_selected_item, R.id.stateName, stateResponseBean.getData());
                                userState.setAdapter(stateListAdapter);
                            }
                        });
                    }

                    @Override
                    public void onFailure(Call call, IOException e) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                customDialogClass.dismiss();
                            }
                        });

                    }
                });

                Map<String, Object> postData = new HashMap<>();
                postData.put("countryId", countryId);
                call.callNewHTTP(postData, APICallConstants.stateApiRequest, context);
            } else {
                ToastUtil.showToast(EditProfileActivity.this, ResourceUtils.getString(context, R.string.internet_connection));
            }
        } catch (Exception e) {
            ErrorMessage.showError(e);
        }
    }


    void getCityList(final Context context, int cityId) {
        try {
            if (NetworkUtil.isNetworkConnected(context)) {
                final CustomDialogClass customDialogClass = new CustomDialogClass(EditProfileActivity.this);
                customDialogClass.show();
                HttpCall call = new HttpCall();
                call.setOkHttpCustomResponse(new OkHttpCustomResponse() {
                    @Override
                    public void onResponse(Call call, Response response) throws IOException {

                        String responseData = response.body().string();
                        Log.e("already response is", responseData);
                        Gson gson = new Gson();
                        final CityResponseBean cityResponseBean = gson.fromJson(responseData, CityResponseBean.class);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                customDialogClass.dismiss();
                                CityListAdapter stateListAdapter = new CityListAdapter(EditProfileActivity.this, R.layout.state_selected_item, R.id.stateName, cityResponseBean.getData());
                                userCity.setAdapter(stateListAdapter);
                            }
                        });
                    }

                    @Override
                    public void onFailure(Call call, IOException e) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                customDialogClass.dismiss();
                            }
                        });

                    }
                });
                Map<String, Object> postData = new HashMap<>();
                postData.put("stateId", cityId);

                call.callNewHTTP(postData, APICallConstants.cityApiRequest, context);
            } else {
                ToastUtil.showToast(EditProfileActivity.this, ResourceUtils.getString(context, R.string.internet_connection));
            }
        } catch (Exception e) {
            ErrorMessage.showError(e);
        }
    }

    void getCountryList(final Context context) {
        try {
            if (NetworkUtil.isNetworkConnected(context)) {
                final CustomDialogClass customDialogClass = new CustomDialogClass(EditProfileActivity.this);
                customDialogClass.show();
                HttpCall call = new HttpCall();
                call.setOkHttpCustomResponse(new OkHttpCustomResponse() {
                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String responseData="";
                        try {
                            responseData= response.body().string();
                        }catch (ProtocolException e){
                            ToastUtil.showToast(context,"Something went wrong..");
                            finish();
                        }
                        Log.e("already response is", responseData);
                        Gson gson = new Gson();
                        final CountryResponseBean stateResponseBean = gson.fromJson(responseData, CountryResponseBean.class);

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                customDialogClass.dismiss();
                                CountryListAdapter stateListAdapter = new CountryListAdapter(EditProfileActivity.this, R.layout.state_selected_item, R.id.stateName, stateResponseBean.getData());
                                userCountry.setAdapter(stateListAdapter);
                            }
                        });
                    }

                    @Override
                    public void onFailure(Call call, IOException e) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                customDialogClass.dismiss();
                            }
                        });

                    }
                });

                call.callGetWithoutParameter(APICallConstants.countryApiRequest, context);
            } else {
                ToastUtil.showToast(EditProfileActivity.this, ResourceUtils.getString(context, R.string.internet_connection));
            }
        } catch (Exception e) {
            ErrorMessage.showError(e);
        }
    }

    void updateProfile() {
        String email = userEmail.getText().toString();
        String altMobile = userAltMobile.getText().toString();
        String address = userAddress.getText().toString();
        String district = userDistrict.getText().toString();
        String pincode = userPincode.getText().toString();
        String fatherNameText = fatherName.getText().toString();
        String dobText = dob.getText().toString();

        if (isValidation(email, altMobile, cityId, stateId,countryId,  address, district, pincode,fatherNameText,dobText)) {
            updateUser(email, altMobile, address, district, pincode,fatherNameText,dobText);
        }
    }

    boolean isValidation(String email, String altMobile, int cityId, int stateId,int countryId,
                         String address, String district, String pincode, String fatherNameText, String dobText) {
        if (email.equalsIgnoreCase("") || !email.matches("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+")) {
            ToastUtil.showToast(EditProfileActivity.this, "Valid Email Address is required");
            return false;
        }else if (altMobile.equalsIgnoreCase("") || altMobile.length() < 10) {
            ToastUtil.showToast(EditProfileActivity.this, "Alternate Mobile Number is required");
            return false;
        } else if (countryId == -1) {
            ToastUtil.showToast(EditProfileActivity.this, "Country is required");
            return false;
        }else if (stateId == -1) {
            ToastUtil.showToast(EditProfileActivity.this, "State is required");
            return false;
        } else if (cityId == -1) {
            ToastUtil.showToast(EditProfileActivity.this, "City is required");
            return false;
        } else if (address.equalsIgnoreCase("")) {
            ToastUtil.showToast(EditProfileActivity.this, "Address is Required");
            return false;
        } else if (district.equalsIgnoreCase("")) {
            ToastUtil.showToast(EditProfileActivity.this, "District is required");
            return false;
        } else if (pincode.equalsIgnoreCase("")) {
            ToastUtil.showToast(EditProfileActivity.this, "Pincode is required");
            return false;
        } else if (fatherNameText.equalsIgnoreCase("")) {
            ToastUtil.showToast(EditProfileActivity.this, "Pincode is required");
            return false;
        } else if (dobText.equalsIgnoreCase("")) {
            ToastUtil.showToast(EditProfileActivity.this, "Pincode is required");
            return false;
        }
        return true;
    }

    public void updateUser(String email, String altMobile, String address, String district, String pincode, String fatherNameText, String dobText) {
        try {
            if (NetworkUtil.isNetworkConnected(EditProfileActivity.this)) {
                final CustomDialogClass customDialogClass = new CustomDialogClass(EditProfileActivity.this);
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
                            SharedPrefrenceUtils.saveObjectPreferences(EditProfileActivity.this, SharedPrefrenceUtils.dealerBean, registerBean.getData().getData().getDealer());
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    customDialogClass.dismiss();
                                }
                            });
                        }

                    }

                    @Override
                    public void onFailure(Call call, IOException e) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                customDialogClass.dismiss();
                            }
                        });
                    }
                });

                Map<String, Object> postData = new HashMap<>();
                postData.put("email", email);
                postData.put("userAddress", address);
                postData.put("stateId", stateId);
                postData.put("cityId", cityId);
                postData.put("pincode", pincode);
                postData.put("alternetMobileNo1", altMobile);
                postData.put("districtName", district);
                postData.put("fatherName", fatherNameText);
                postData.put("dob", dobText);
                postData.put("countryId", countryId);
                postData.put("dealerId",dealer.getDealerId());
                call.callNewHTTP(postData, APICallConstants.editDealerApiRequest, EditProfileActivity.this);
            } else {
                ToastUtil.showToast(EditProfileActivity.this, ResourceUtils.getString(EditProfileActivity.this, R.string.internet_connection));
            }
        } catch (Exception e) {
            ErrorMessage.showError(e);
        }
    }

}