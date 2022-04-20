package com.technoecorp.gorilladealer.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.technoecorp.gorilladealer.R;
import com.technoecorp.gorilladealer.adapter.CityCountAdapter;
import com.technoecorp.gorilladealer.adapter.StateAdapter;
import com.technoecorp.gorilladealer.bean.CityCountResponse.CityCountBean;
import com.technoecorp.gorilladealer.bean.CityCountResponse.CityWiseCount;
import com.technoecorp.gorilladealer.bean.DealerDashboardBean.Data;
import com.technoecorp.gorilladealer.bean.DealerListResponseBean;
import com.technoecorp.gorilladealer.bean.OTPBean.Dealer;
import com.technoecorp.gorilladealer.bean.StateCountResponse.StateCountBean;
import com.technoecorp.gorilladealer.bean.StateCountResponse.StateWiseCount;
import com.technoecorp.gorilladealer.dialog.CustomDialogClass;
import com.technoecorp.gorilladealer.interfaces.OkHttpCustomResponse;
import com.technoecorp.gorilladealer.utils.APICallConstants;
import com.technoecorp.gorilladealer.utils.HttpCall;
import com.technoecorp.gorilladealer.utils.NetworkUtil;
import com.technoecorp.gorilladealer.utils.ResourceUtils;
import com.technoecorp.gorilladealer.utils.SharedPrefrenceUtils;
import com.technoecorp.gorilladealer.utils.ToastUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Response;

public class DealerListActivity extends AppCompatActivity {
    @BindView(R.id.stateList)
    RecyclerView stateList;
    @BindView(R.id.dealerList)
    RecyclerView dealerList;
    @BindView(R.id.linearBack)
    LinearLayout linearBack;
    ArrayList<StateWiseCount> stateBeans = new ArrayList<>();
    ArrayList<CityWiseCount> cityWiseCounts = new ArrayList<>();
    StateAdapter stateAdapter;
    CityCountAdapter cityCountAdapter;
    Dealer dealer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dealer_list);
        ButterKnife.bind(this);
        Gson gson = new Gson();
        String dealerBean = SharedPrefrenceUtils.loadSavedPreferences(this, SharedPrefrenceUtils.dealerBean, "");
        dealer = gson.fromJson(dealerBean, Dealer.class);
        if (dealer == null) {
            finish();
        }

        stateAdapter = new StateAdapter(stateBeans, DealerListActivity.this);
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        stateList.setLayoutManager(layoutManager);
        stateList.setAdapter(stateAdapter);

        cityCountAdapter = new CityCountAdapter(cityWiseCounts, DealerListActivity.this);
        LinearLayoutManager layoutManager1
                = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        dealerList.setLayoutManager(layoutManager1);
        dealerList.setAdapter(cityCountAdapter);

        linearBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        getStateList(DealerListActivity.this);

    }


    public void getDealerList(final Context context, int stateId, CityWiseCount cityWiseCount, String type) {
        try {
            if (NetworkUtil.isNetworkConnected(context)) {
                final CustomDialogClass customDialogClass = new CustomDialogClass(DealerListActivity.this);
                customDialogClass.show();
                HttpCall call = new HttpCall();
                call.setOkHttpCustomResponse(new OkHttpCustomResponse() {
                    @Override
                    public void onResponse(Call call, Response response) throws IOException {

                        String responseData = response.body().string();
                        Log.e("already response is", responseData);
                        Gson gson = new Gson();
                        final DealerListResponseBean stateResponseBean = gson.fromJson(responseData, DealerListResponseBean.class);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                customDialogClass.dismiss();
                                Intent intent = new Intent(DealerListActivity.this, IncomeListActivity.class);
                                if (type.equalsIgnoreCase("active")) {
                                    intent.putExtra("title", "Active User, " + cityWiseCount.getCityName());
                                    intent.putExtra("isActive", true);
                                    if (stateId != -1) {
                                        intent.putExtra("stateId", String.valueOf(stateId));
                                    }
                                    if (cityWiseCount.getCityId() != -1) {
                                        intent.putExtra("cityId", String.valueOf(cityWiseCount.getCityId()));
                                    }
                                } else if (type.equalsIgnoreCase("deactive")) {
                                    intent.putExtra("title", "Deactive User, " + cityWiseCount.getCityName());
                                    intent.putExtra("isActive", false);
                                    if (stateId != -1) {
                                        intent.putExtra("stateId", String.valueOf(stateId));
                                    }
                                    if (cityWiseCount.getCityId() != -1) {
                                        intent.putExtra("cityId", String.valueOf(cityWiseCount.getCityId()));
                                    }
                                }
                                intent.putParcelableArrayListExtra("dealers", stateResponseBean.getData());
                                startActivity(intent);
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
                postData.put("startDate", "");
                postData.put("endDate", "");
                postData.put("dealerId", dealer.getDealerId());
                postData.put("referCode", dealer.getReferCode());
                if (type.equalsIgnoreCase("all")) {
                    postData.put("isActive", null);
                } else if (type.equalsIgnoreCase("active")) {
                    postData.put("isActive", true);
                } else if (type.equalsIgnoreCase("deactive")) {
                    postData.put("isActive", false);
                }

                if (stateId == -1) {
                    postData.put("stateId", null);
                } else {
                    postData.put("stateId", stateId);
                }

                if (cityWiseCount.getCityId() == -1) {
                    postData.put("cityId", null);
                } else {
                    postData.put("cityId", cityWiseCount.getCityId());
                }

                call.callNewHTTP(postData, APICallConstants.dealerFilter, context);
            } else {
                ToastUtil.showToast(DealerListActivity.this, ResourceUtils.getString(context, R.string.internet_connection));
            }
        } catch (Exception e) {
            Log.e("Error",e.getMessage());
        }
    }

    void getStateList(final Context context) {
        try {
            if (NetworkUtil.isNetworkConnected(context)) {
                final CustomDialogClass customDialogClass = new CustomDialogClass(DealerListActivity.this);
                customDialogClass.show();
                HttpCall call = new HttpCall();
                call.setOkHttpCustomResponse(new OkHttpCustomResponse() {
                    @Override
                    public void onResponse(Call call, Response response) throws IOException {

                        String responseData = response.body().string();
                        Log.e("already response is", responseData);
                        Gson gson = new Gson();
                        final StateCountBean stateResponseBean = gson.fromJson(responseData, StateCountBean.class);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                customDialogClass.dismiss();
                                Gson gson = new Gson();
                                String dashboardData = SharedPrefrenceUtils.loadSavedPreferences(context, SharedPrefrenceUtils.lastDashBoardObject, "");
                                if (!dashboardData.equalsIgnoreCase("")) {
                                    Data dashboard = gson.fromJson(dashboardData, Data.class);
                                    if (dashboard != null) {
                                        StateWiseCount stateWiseCount = new StateWiseCount("All States", dashboard.getActiveDealer(), dashboard.getDeactiveDealer(), -1);
                                        stateResponseBean.getData().add(0, stateWiseCount);
                                        getCityList(DealerListActivity.this, stateWiseCount);
                                    }
                                }
                                stateAdapter.addAllData(stateResponseBean.getData());
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
                postData.put("referCode", dealer.getReferCode());
                call.callNewHTTP(postData, APICallConstants.getStateCountForDealer, context);
            } else {
                ArrayList<StateWiseCount> stateWise = new ArrayList<>();
                Gson gson = new Gson();
                String dashboardData = SharedPrefrenceUtils.loadSavedPreferences(context, SharedPrefrenceUtils.lastDashBoardObject, "");
                if (!dashboardData.equalsIgnoreCase("")) {
                    Data dashboard = gson.fromJson(dashboardData, Data.class);
                    if (dashboard != null) {
                        StateWiseCount stateWiseCount = new StateWiseCount("All States", dashboard.getActiveDealer(), dashboard.getDeactiveDealer(), -1);
                        stateWise.add(0, stateWiseCount);
                        getCityList(DealerListActivity.this, stateWiseCount);
                    }
                }
                stateAdapter.addAllData(stateWise);
                ToastUtil.showToast(DealerListActivity.this, ResourceUtils.getString(context, R.string.internet_connection));
            }
        } catch (Exception e) {
            Log.e("Error",e.getMessage());
        }
    }

    public void getCityList(final Context context, StateWiseCount stateWiseCount) {
        try {
            if (stateWiseCount.getStateId() == -1) {
                cityCountAdapter.setStateId(stateWiseCount.getStateId());
                ArrayList<CityWiseCount> tempList = new ArrayList<>();
                Gson gson = new Gson();
                String dashboardData = SharedPrefrenceUtils.loadSavedPreferences(context, SharedPrefrenceUtils.lastDashBoardObject, "");
                if (!dashboardData.equalsIgnoreCase("")) {
                    Data dashboard = gson.fromJson(dashboardData, Data.class);
                    if (dashboard != null) {
                        tempList.add(new CityWiseCount("All City", dashboard.getActiveDealer(), dashboard.getDeactiveDealer(), -1));
                    }
                }
                cityCountAdapter.addAllData(tempList);
            } else {
                if (NetworkUtil.isNetworkConnected(context)) {
                    final CustomDialogClass customDialogClass = new CustomDialogClass(DealerListActivity.this);
                    customDialogClass.show();
                    HttpCall call = new HttpCall();
                    call.setOkHttpCustomResponse(new OkHttpCustomResponse() {
                        @Override
                        public void onResponse(Call call, Response response) throws IOException {

                            String responseData = response.body().string();
                            Log.e("already response is", responseData);
                            Gson gson = new Gson();
                            cityCountAdapter.setStateId(stateWiseCount.getStateId());
                            final CityCountBean stateResponseBean = gson.fromJson(responseData, CityCountBean.class);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    customDialogClass.dismiss();
                                    stateResponseBean.getData().add(0, new CityWiseCount("All City", stateWiseCount.getActiveCount(), stateWiseCount.getDeactiveCount(), -1));
                                    cityCountAdapter.addAllData(stateResponseBean.getData());
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
                    postData.put("referCode", dealer.getReferCode());
                    postData.put("stateId", stateWiseCount.getStateId());
                    call.callNewHTTP(postData, APICallConstants.getCityCountForDealer, context);
                } else {
                    ToastUtil.showToast(DealerListActivity.this, ResourceUtils.getString(context, R.string.internet_connection));
                }
            }
        } catch (Exception e) {
            Log.e("Error",e.getMessage());
        }
    }

}