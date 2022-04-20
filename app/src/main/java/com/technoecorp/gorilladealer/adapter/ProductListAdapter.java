package com.technoecorp.gorilladealer.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.technoecorp.gorilladealer.R;
import com.technoecorp.gorilladealer.bean.OTPBean.Dealer;
import com.technoecorp.gorilladealer.bean.ProductResponse.Package;
import com.technoecorp.gorilladealer.bean.ProductResponse.ProductBean;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by achint on 9/14/17.
 */


public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.ViewHolder> {

    Context context;
    Activity activity;
    private ArrayList<ProductBean> mDataArray;
    int[] androidColors;
    int dealerId;

    //TODO: remove this later
    public ProductListAdapter(ArrayList<ProductBean> mDataArray, Context context,int dealerId) {
        this.context = context;
        this.activity = (Activity) context;
        this.mDataArray = mDataArray;
        this.dealerId=dealerId;
        this.androidColors = activity.getResources().getIntArray(R.array.androidcolors);

    }


    @Override
    public int getItemCount() {
        return mDataArray.size();
    }

    public void addAllData(ArrayList<ProductBean> mDataArray) {
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
    public ProductListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_list_item, parent, false);
        ProductListAdapter.ViewHolder vh = new ProductListAdapter.ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ProductListAdapter.ViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        holder.productName.setText(mDataArray.get(position).getProductName());
        ProductPackageListAdapter dealerListAdapter = new ProductPackageListAdapter((ArrayList<Package>) mDataArray.get(position).getPackage(), context,dealerId);
        LinearLayoutManager layoutManager1
                = new LinearLayoutManager(context, RecyclerView.VERTICAL, false);
        holder.packageDetails.setLayoutManager(layoutManager1);
        holder.packageDetails.setAdapter(dealerListAdapter);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.productName)
        TextView productName;
        @BindView(R.id.packageDetails)
        RecyclerView packageDetails;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }


    }


}
