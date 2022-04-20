package com.technoecorp.gorilladealer.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.technoecorp.gorilladealer.R;
import com.technoecorp.gorilladealer.adapter.GalleryPhotoPdfAdapter;
import com.technoecorp.gorilladealer.adapter.GalleryVideoAdapter;
import com.technoecorp.gorilladealer.bean.GalleryResponse.GalleryBeanRequest;
import com.technoecorp.gorilladealer.interfaces.OkHttpCustomResponse;
import com.technoecorp.gorilladealer.utils.APICallConstants;
import com.technoecorp.gorilladealer.utils.HttpCall;
import com.technoecorp.gorilladealer.utils.NetworkUtil;
import com.technoecorp.gorilladealer.utils.PermissionUtils;
import com.technoecorp.gorilladealer.utils.ToastUtil;


import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Response;

public class GalleryActivity extends AppCompatActivity {
    @BindView(R.id.galleryRecycler)
    RecyclerView galleryRecycler;
    @BindView(R.id.linearBack)
    LinearLayout linearBack;
    String type;
    GalleryPhotoPdfAdapter dashboardServiceAdapter;
    GalleryVideoAdapter galleryVideoAdapter;
    final int PERMISSION_DISK_WRITE_CODE = 102;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        type = intent.getStringExtra("type");
        if (type.equalsIgnoreCase("video")) {
            galleryVideoAdapter = new GalleryVideoAdapter(GalleryActivity.this);
            galleryRecycler.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false) {
                @Override
                public boolean checkLayoutParams(RecyclerView.LayoutParams lp) {
                    // force height of viewHolder here, this will override layout_height from xml
                    lp.height = getHeight() / 3;
                    return true;
                }
            });
            galleryRecycler.setAdapter(galleryVideoAdapter);
        } else {
            dashboardServiceAdapter = new GalleryPhotoPdfAdapter(GalleryActivity.this);
            galleryRecycler.setLayoutManager(new GridLayoutManager(GalleryActivity.this, 2));
            galleryRecycler.setAdapter(dashboardServiceAdapter);
        }
        checkForGallery(type);
        linearBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        requestPermission();
    }

    void requestPermission() {
        if (!PermissionUtils.checkPhoneAccess(GalleryActivity.this)) {
            PermissionUtils.requestStorage(GalleryActivity.this, PERMISSION_DISK_WRITE_CODE);
        }
    }


    void checkForGallery(String type) {
        if (NetworkUtil.isNetworkConnected(this)) {
            HttpCall call = new HttpCall();
            call.setOkHttpCustomResponse(new OkHttpCustomResponse() {
                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    String responseData = response.body().string();
                    Log.e("already response is", responseData);
                    Gson gson = new Gson();
                    final GalleryBeanRequest backUpBean = gson.fromJson(responseData, GalleryBeanRequest.class);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (backUpBean.getStatus()) {
                                if (type.equalsIgnoreCase("video")) {

                                    galleryVideoAdapter.addAll(backUpBean.getData());
                                }else{
                                    dashboardServiceAdapter.addAll(backUpBean.getData());
                                }
                            } else {
                                if (type != null) {
                                    ToastUtil.showToast(GalleryActivity.this, "No Data Found on Server For " + type.toUpperCase());
                                }
                            }

                        }
                    });
                }

                @Override
                public void onFailure(Call call, IOException e) {

                }
            });
            Map<String, Object> postData = new HashMap<>();
            postData.put("type", type);
            call.callNewHTTP(postData, APICallConstants.galleryRequest, GalleryActivity.this);
        }
    }
}