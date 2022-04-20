package com.technoecorp.gorilladealer.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;
import com.google.gson.Gson;
import com.technoecorp.gorilladealer.R;
import com.technoecorp.gorilladealer.activity.DealerListActivity;
import com.technoecorp.gorilladealer.bean.DealerDashboardBean.Data;
import com.technoecorp.gorilladealer.bean.StateCountResponse.StateWiseCount;
import com.technoecorp.gorilladealer.utils.SharedPrefrenceUtils;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by achint on 9/14/17.
 */


public class StateAdapter extends RecyclerView.Adapter<StateAdapter.ViewHolder> {

    public int SELECTED_ITEM = 0;
    Context context;
    Activity activity;
    private ArrayList<StateWiseCount> mDataArray;


    //TODO: remove this later
    public StateAdapter(ArrayList<StateWiseCount> mDataArray, Context context) {
        this.context = context;
        this.activity = (Activity) context;
        this.mDataArray = mDataArray;
    }


    @Override
    public int getItemCount() {
        return mDataArray.size();
    }

    public void addAllData(ArrayList<StateWiseCount> mDataArray) {
        this.mDataArray.clear();
        this.mDataArray.addAll(mDataArray);
        for(int i=0;i<mDataArray.size();i++){
            int[] androidColors = activity.getResources().getIntArray(R.array.androidcolors);
            int randomAndroidColor = androidColors[new Random().nextInt(androidColors.length)];
            mDataArray.get(i).setColorCode(randomAndroidColor);
        }
        notifyDataSetChanged();
    }

    public void clearAllData() {
        this.mDataArray.clear();
        notifyDataSetChanged();
    }


    @NotNull
    @Override
    public StateAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.state_list_item, parent, false);
        StateAdapter.ViewHolder vh = new StateAdapter.ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(StateAdapter.ViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        holder.stateText.setText(mDataArray.get(position).getStateName());
        holder.countText.setText(mDataArray.get(position).getActiveCount() + "/" + mDataArray.get(position).getDeactiveCount());
        if (SELECTED_ITEM == position) {
            holder.selectedLinear.setVisibility(View.VISIBLE);
        } else {
            holder.selectedLinear.setVisibility(View.GONE);
        }

        holder.materialCard.setCardBackgroundColor(mDataArray.get(position).getColorCode());

        holder.materialCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SELECTED_ITEM = position;
                notifyDataSetChanged();
                if (activity instanceof DealerListActivity) {
                    DealerListActivity dealerListActivity = (DealerListActivity) activity;
                    dealerListActivity.getCityList(context, mDataArray.get(position));
                }
            }
        });

    }

    public static class ViewHolder extends RecyclerView.ViewHolder {


        @BindView(R.id.stateText)
        TextView stateText;
        @BindView(R.id.countText)
        TextView countText;
        @BindView(R.id.selectedLinear)
        LinearLayout selectedLinear;
        @BindView(R.id.materialCard)
        MaterialCardView materialCard;


        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }


    }


}
