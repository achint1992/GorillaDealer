package com.technoecorp.gorilladealer.bean.DealerDashboardBean;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WithdrawlBean implements Parcelable {

    @SerializedName("withdrawalAmount")
    @Expose
    private int withdrawalAmount;
    @SerializedName("count")
    @Expose
    private int count;

    public int getWithdrawalAmount() {
        return withdrawalAmount;
    }

    public void setWithdrawalAmount(int withdrawalAmount) {
        this.withdrawalAmount = withdrawalAmount;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.withdrawalAmount);
        dest.writeInt(this.count);
    }

    public void readFromParcel(Parcel source) {
        this.withdrawalAmount = source.readInt();
        this.count = source.readInt();
    }

    public WithdrawlBean() {
    }

    protected WithdrawlBean(Parcel in) {
        this.withdrawalAmount = in.readInt();
        this.count = in.readInt();
    }

    public static final Parcelable.Creator<WithdrawlBean> CREATOR = new Parcelable.Creator<WithdrawlBean>() {
        @Override
        public WithdrawlBean createFromParcel(Parcel source) {
            return new WithdrawlBean(source);
        }

        @Override
        public WithdrawlBean[] newArray(int size) {
            return new WithdrawlBean[size];
        }
    };
}
