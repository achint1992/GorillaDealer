package com.technoecorp.gorilladealer.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.technoecorp.gorilladealer.R;
import com.technoecorp.gorilladealer.activity.DealerListActivity;
import com.technoecorp.gorilladealer.bean.CityCountResponse.CityWiseCount;
import com.technoecorp.gorilladealer.utils.TextDrawable;
import com.technoecorp.gorilladealer.utils.ToastUtil;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by achint on 9/14/17.
 */


public class CityCountAdapter extends RecyclerView.Adapter<CityCountAdapter.ViewHolder> {

    public int SELECTED_ITEM = 0;
    Context context;
    Activity activity;
    private ArrayList<CityWiseCount> mDataArray;
    int[] androidColors;
    Typeface tf;
    int stateId;


    //TODO: remove this later
    public CityCountAdapter(ArrayList<CityWiseCount> mDataArray, Context context) {
        this.context = context;
        this.activity = (Activity) context;
        this.mDataArray = mDataArray;
        this.tf = Typeface.createFromAsset(context.getAssets(), "ebrimagras.ttf");
        this.androidColors = activity.getResources().getIntArray(R.array.androidcolors);

    }


    @Override
    public int getItemCount() {
        return mDataArray.size();
    }

    public void addAllData(ArrayList<CityWiseCount> mDataArray) {
        this.mDataArray.clear();
        this.mDataArray.addAll(mDataArray);
        notifyDataSetChanged();
    }

    public void clearAllData() {
        this.mDataArray.clear();
        notifyDataSetChanged();
    }

    public void setStateId(int stateId) {
        this.stateId = stateId;
    }

    @NotNull
    @Override
    public CityCountAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.city_count_list_item, parent, false);
        CityCountAdapter.ViewHolder vh = new CityCountAdapter.ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(CityCountAdapter.ViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        holder.nameText.setText(mDataArray.get(position).getCityName());
        int random = new Random().nextInt(androidColors.length);
        TextDrawable textDrawable = TextDrawable.builder().beginConfig().useFont(tf).endConfig()
                .buildRound(((Character) mDataArray.get(position).getCityName().toUpperCase(Locale.ROOT).charAt(0)).toString(), androidColors[random]);
        holder.ivAvatar.setImageDrawable(textDrawable);
        holder.phoneText.setText("Active User: " + mDataArray.get(position).getActiveCount());
        holder.locationText.setText("Deactive User: " + mDataArray.get(position).getDeactiveCount());
        holder.activeUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mDataArray.get(position).getActiveCount() > 0) {
                    if (activity instanceof DealerListActivity) {
                        DealerListActivity dealerListActivity = (DealerListActivity) activity;
                        dealerListActivity.getDealerList(context, stateId, mDataArray.get(position), "active");
                    }
                } else {
                    ToastUtil.showToast(context, "No User Found");
                }
            }
        });
        holder.deactiveUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mDataArray.get(position).getDeactiveCount() > 0) {
                    if (activity instanceof DealerListActivity) {
                        DealerListActivity dealerListActivity = (DealerListActivity) activity;
                        dealerListActivity.getDealerList(context, stateId, mDataArray.get(position), "deactive");
                    }
                } else {
                    ToastUtil.showToast(context, "No User Found");
                }
            }
        });

    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.ivAvatar)
        ImageView ivAvatar;
        @BindView(R.id.nameText)
        TextView nameText;
        @BindView(R.id.phoneText)
        TextView phoneText;
        @BindView(R.id.locationText)
        TextView locationText;
        @BindView(R.id.activeUser)
        MaterialButton activeUser;
        @BindView(R.id.deactiveUser)
        MaterialButton deactiveUser;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


}
