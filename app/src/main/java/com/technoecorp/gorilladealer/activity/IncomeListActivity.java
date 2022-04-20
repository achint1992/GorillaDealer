package com.technoecorp.gorilladealer.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.google.gson.Gson;
import com.technoecorp.gorilladealer.R;
import com.technoecorp.gorilladealer.adapter.DealerListAdapter;
import com.technoecorp.gorilladealer.bean.OTPBean.Dealer;
import com.technoecorp.gorilladealer.utils.SharedPrefrenceUtils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class IncomeListActivity extends AppCompatActivity {
    @BindView(R.id.dealerList)
    RecyclerView dealerList;
    @BindView(R.id.linearBack)
    LinearLayout linearBack;
    @BindView(R.id.headingText)
    TextView headingText;
    @BindView(R.id.btnExport)
    MaterialButton btnExport;
    ArrayList<Dealer> dealers = new ArrayList<>();
    DealerListAdapter dealerListAdapter;
    boolean isExport = true;
    boolean isActive = true;
    String stateId = null;
    String cityId = null;
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
        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        ArrayList<Dealer> tempList = intent.getParcelableArrayListExtra("dealers");
        isExport = intent.getBooleanExtra("isExport", true);
        isActive = intent.getBooleanExtra("isActive", true);
        stateId = intent.getStringExtra("stateId");
        cityId = intent.getStringExtra("cityId");

        if (tempList.size() > 0) {
            dealers.addAll(tempList);
        }

        dealerListAdapter = new DealerListAdapter(dealers, IncomeListActivity.this);
        LinearLayoutManager layoutManager1
                = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        dealerList.setLayoutManager(layoutManager1);
        dealerList.setAdapter(dealerListAdapter);
        linearBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        if (title != null) {
            headingText.setText(title);
        }

        if (!isExport) {
            btnExport.setVisibility(View.GONE);
        }

        btnExport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "http://65.2.21.88/exportToExcel?isActive=" + isActive + "&stateId=" + stateId + "&cityId=" + cityId + "&referCode=" + dealer.getReferCode() + "&dealerId=" + dealer.getDealerId();
                Log.e("url is", url);
                Intent intent1 = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(intent1);
            }
        });

    }

}