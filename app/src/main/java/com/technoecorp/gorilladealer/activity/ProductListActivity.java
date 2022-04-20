package com.technoecorp.gorilladealer.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.gson.Gson;
import com.technoecorp.gorilladealer.R;
import com.technoecorp.gorilladealer.adapter.ProductListAdapter;
import com.technoecorp.gorilladealer.bean.OTPBean.Dealer;
import com.technoecorp.gorilladealer.bean.ProductResponse.ProductBean;
import com.technoecorp.gorilladealer.bean.ProductResponse.ProductDetailsBean;
import com.technoecorp.gorilladealer.dialog.CustomDialogClass;
import com.technoecorp.gorilladealer.interfaces.OkHttpCustomResponse;
import com.technoecorp.gorilladealer.utils.APICallConstants;
import com.technoecorp.gorilladealer.utils.HttpCall;
import com.technoecorp.gorilladealer.utils.NetworkUtil;
import com.technoecorp.gorilladealer.utils.ResourceUtils;
import com.technoecorp.gorilladealer.utils.SharedPrefrenceUtils;
import com.technoecorp.gorilladealer.utils.ToastUtil;

import java.io.IOException;
import java.net.ProtocolException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Response;

public class ProductListActivity extends AppCompatActivity {
    @BindView(R.id.dealerList)
    RecyclerView dealerList;
    @BindView(R.id.linearBack)
    LinearLayout linearBack;
    @BindView(R.id.headingText)
    TextView headingText;
    @BindView(R.id.btnExport)
    MaterialButton btnExport;
    ArrayList<ProductBean> product = new ArrayList<>();
    ProductListAdapter productListAdapter;
    AlertDialog dialog;
    Dealer dealer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_income_list);
        ButterKnife.bind(this);
        Gson gson = new Gson();
        String dealerBean = SharedPrefrenceUtils.loadSavedPreferences(this, SharedPrefrenceUtils.dealerBean, "");
        dealer = gson.fromJson(dealerBean, Dealer.class);
        if (dealer == null) {
            finish();
        }

        productListAdapter = new ProductListAdapter(product, ProductListActivity.this, dealer.getDealerId());
        LinearLayoutManager layoutManager1
                = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        dealerList.setLayoutManager(layoutManager1);
        dealerList.setAdapter(productListAdapter);
        linearBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        headingText.setText("Gorilla Products");
        btnExport.setVisibility(View.INVISIBLE);
        getCountryList(ProductListActivity.this);
       /* if (!isValidationProfile()) {
            showMaterialDialog();
        }*/
    }

    boolean isValidationProfile() {
        if (dealer.getEmail().equalsIgnoreCase("")) {
            return false;
        } else if (dealer.getAlternetMobileNo1().equalsIgnoreCase("")) {
            return false;
        } else if (dealer.getCountry().getId() == -1) {
            return false;
        } else if (dealer.getState().getStateId() == -1) {
            return false;
        } else if (dealer.getCity().getCityId() == -1) {
            return false;
        } else if (dealer.getUserAddress().equalsIgnoreCase("")) {
            return false;
        } else if (dealer.getDistrictName().equalsIgnoreCase("")) {
            return false;
        } else if (dealer.getPincode().equalsIgnoreCase("")) {
            return false;
        } else if (dealer.getFatherName().equalsIgnoreCase("")) {
            return false;
        } else if (dealer.getDob().equalsIgnoreCase("")) {
            return false;
        } else if (dealer.getKycBean() == null) {
            return false;
        }
        return true;
    }

    void showMaterialDialog() {
        dialog = new MaterialAlertDialogBuilder(ProductListActivity.this)
                .setTitle("Profile Update")
                .setMessage("Your banking & profile details are not updated. You won't be able to receive any payment without updating it.")
                .setPositiveButton("Edit Profile Now", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent edit = new Intent(ProductListActivity.this, KycActivity.class);
                        startActivity(edit);
                        dialogInterface.dismiss();
                    }
                }).setCancelable(false).show();

    }

    @Override
    protected void onResume() {
        Log.e("on resume ", "resume called");
        Gson gson = new Gson();
        String dealerBean = SharedPrefrenceUtils.loadSavedPreferences(this, SharedPrefrenceUtils.dealerBean, "");
        dealer = gson.fromJson(dealerBean, Dealer.class);
        if (dealer == null) {
            finish();
        }
      /*  if (!isValidationProfile()) {
            showMaterialDialog();
        }*/
        super.onResume();
    }

    void getCountryList(final Context context) {
        try {
            if (NetworkUtil.isNetworkConnected(context)) {
                final CustomDialogClass customDialogClass = new CustomDialogClass(ProductListActivity.this);
                customDialogClass.show();
                HttpCall call = new HttpCall();
                call.setOkHttpCustomResponse(new OkHttpCustomResponse() {
                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String responseData = "";
                        try {
                            responseData = response.body().string();
                        } catch (ProtocolException e) {
                            ToastUtil.showToast(context, "Something went wrong..");
                            finish();
                        }
                        Log.e("already response is", responseData);
                        Gson gson = new Gson();
                        final ProductDetailsBean stateResponseBean = gson.fromJson(responseData, ProductDetailsBean.class);

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                customDialogClass.dismiss();
                                productListAdapter.addAllData(stateResponseBean.getData());

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

                call.callGetWithoutParameter(APICallConstants.getAllPackages, context);
            } else {
                ToastUtil.showToast(ProductListActivity.this, ResourceUtils.getString(context, R.string.internet_connection));
            }
        } catch (Exception e) {
            ErrorMessage.showError(e);
        }
    }

}