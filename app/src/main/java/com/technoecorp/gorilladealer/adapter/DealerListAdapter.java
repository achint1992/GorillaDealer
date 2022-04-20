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

import com.technoecorp.gorilladealer.R;
import com.technoecorp.gorilladealer.bean.OTPBean.Dealer;
import com.technoecorp.gorilladealer.utils.TextDrawable;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by achint on 9/14/17.
 */


public class DealerListAdapter extends RecyclerView.Adapter<DealerListAdapter.ViewHolder> {

    public int SELECTED_ITEM = 0;
    Context context;
    Activity activity;
    private ArrayList<Dealer> mDataArray;
    int[] androidColors;
    Typeface tf;


    //TODO: remove this later
    public DealerListAdapter(ArrayList<Dealer> mDataArray, Context context) {
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

    public void addAllData(ArrayList<Dealer> mDataArray) {
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
    public DealerListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.dealer_list_item, parent, false);
        DealerListAdapter.ViewHolder vh = new DealerListAdapter.ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(DealerListAdapter.ViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        holder.nameText.setText(mDataArray.get(position).getName());
        int random = new Random().nextInt(androidColors.length);
        TextDrawable textDrawable = TextDrawable.builder().beginConfig().useFont(tf).endConfig().buildRound(((Character) mDataArray.get(position).getName().toUpperCase(Locale.ROOT).charAt(0)).toString(), androidColors[random]);
        holder.ivAvatar.setImageDrawable(textDrawable);
        holder.phoneText.setText(mDataArray.get(position).getMobileNo());
        if (mDataArray.get(position).getStateId() != null) {
            holder.locationText.setText(mDataArray.get(position).getCity().getCityName() + ", " + mDataArray.get(position).getState().getStateName());
        }else{
            holder.locationText.setText("NA");
        }
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


        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }


    }


}
