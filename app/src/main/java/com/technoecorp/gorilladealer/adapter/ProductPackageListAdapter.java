package com.technoecorp.gorilladealer.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.google.gson.Gson;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.technoecorp.gorilladealer.R;
import com.technoecorp.gorilladealer.activity.PaymentCaptureActivity;
import com.technoecorp.gorilladealer.bean.PaymentResponse.PaymentLinkBean;
import com.technoecorp.gorilladealer.bean.ProductResponse.Package;
import com.technoecorp.gorilladealer.dialog.CustomDialogClass;
import com.technoecorp.gorilladealer.interfaces.OkHttpCustomResponse;
import com.technoecorp.gorilladealer.utils.APICallConstants;
import com.technoecorp.gorilladealer.utils.HttpCall;
import com.technoecorp.gorilladealer.utils.NetworkUtil;
import com.technoecorp.gorilladealer.utils.ResourceUtils;
import com.technoecorp.gorilladealer.utils.ToastUtil;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by achint on 9/14/17.
 */


public class ProductPackageListAdapter extends RecyclerView.Adapter<ProductPackageListAdapter.ViewHolder> {

    Context context;
    Activity activity;
    private ArrayList<Package> mDataArray;
    int[] androidColors;
    int dealerId;
    ImageLoader imageLoader;

    //TODO: remove this later
    public ProductPackageListAdapter(ArrayList<Package> mDataArray, Context context, int dealerId) {
        this.context = context;
        this.activity = (Activity) context;
        this.mDataArray = mDataArray;
        this.dealerId = dealerId;
        this.androidColors = activity.getResources().getIntArray(R.array.androidcolors);
        this.imageLoader = ImageLoader.getInstance();
        this.imageLoader.init(ImageLoaderConfiguration.createDefault(context));
    }


    @Override
    public int getItemCount() {
        return mDataArray.size();
    }

    public void addAllData(ArrayList<Package> mDataArray) {
        this.mDataArray.clear();
        this.mDataArray.addAll(mDataArray);
        notifyDataSetChanged();
    }

    public void clearAllData() {
        this.mDataArray.clear();
        notifyDataSetChanged();
    }


    @NotNull
    @Override
    public ProductPackageListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.package_list_item, parent, false);
        ProductPackageListAdapter.ViewHolder vh = new ProductPackageListAdapter.ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ProductPackageListAdapter.ViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        holder.productName.setText(mDataArray.get(position).getPackageName());
        holder.buyButton.setText("Buy at " + mDataArray.get(position).getPackagePrice());
        if (mDataArray.get(position).getPackageImg() != null) {
            if (!mDataArray.get(position).getPackageImg().equalsIgnoreCase("")) {
                imageLoader.displayImage(mDataArray.get(position).getPackageImg(), holder.packageImage);
            }
        }
        holder.buyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getPaymentLink(mDataArray.get(position).getProductId(), mDataArray.get(position).getPackageId(), String.valueOf(mDataArray.get(position).getPackagePrice()));
            }
        });
    }

    void getPaymentLink(int productId, int packageId, String totalAmount) {
        try {
            if (NetworkUtil.isNetworkConnected(activity)) {
                final CustomDialogClass customDialogClass = new CustomDialogClass(activity);
                customDialogClass.show();
                HttpCall call = new HttpCall();
                call.setOkHttpCustomResponse(new OkHttpCustomResponse() {
                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String responseData = response.body().string();
                        Log.e("already response is", responseData);
                        Gson gson = new Gson();
                        final PaymentLinkBean registerBean = gson.fromJson(responseData, PaymentLinkBean.class);
                        if (registerBean.getStatus()) {
                            activity.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    customDialogClass.dismiss();
                                    // Intent intent = new Intent(Intent.ACTION_VIEW);
                                    // intent.setData(Uri.parse( registerBean.getData().getRedirectUrl()));
                                    Intent intent = new Intent(activity, PaymentCaptureActivity.class);
                                    intent.putExtra("url", registerBean.getData().getRedirectUrl());
                                    activity.startActivity(intent);
                                }
                            });
                        }

                    }

                    @Override
                    public void onFailure(Call call, IOException e) {
                        activity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                customDialogClass.dismiss();
                            }
                        });
                    }
                });

                Map<String, Object> postData = new HashMap<>();
                postData.put("productId", productId);
                postData.put("packageId", packageId);
                postData.put("dealerId", dealerId);
                postData.put("productBuyQty", 1);
                postData.put("totalAmount", totalAmount);
                call.callNewHTTP(postData, APICallConstants.createPaymentLink, activity);
            } else {
                ToastUtil.showToast(activity, ResourceUtils.getString(activity, R.string.internet_connection));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.productName)
        TextView productName;
        @BindView(R.id.buyButton)
        MaterialButton buyButton;
        @BindView(R.id.packageImage)
        ImageView packageImage;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }


    }


}
