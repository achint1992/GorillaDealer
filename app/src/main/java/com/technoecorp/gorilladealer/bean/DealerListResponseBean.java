
package com.technoecorp.gorilladealer.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.technoecorp.gorilladealer.bean.OTPBean.Dealer;
import com.technoecorp.gorilladealer.bean.stateResponse.StateBean;

import java.util.ArrayList;

public class DealerListResponseBean implements Parcelable {

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("data")
    @Expose
    private ArrayList<Dealer> data = null;

    public DealerListResponseBean() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public ArrayList<Dealer> getData() {
        return data;
    }

    public void setData(ArrayList<Dealer> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "StateResponseBean{" +
                "message='" + message + '\'' +
                ", status=" + status +
                ", data=" + data +
                '}';
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.message);
        dest.writeValue(this.status);
        dest.writeList(this.data);
    }

    public void readFromParcel(Parcel source) {
        this.message = source.readString();
        this.status = (Boolean) source.readValue(Boolean.class.getClassLoader());
        this.data = new ArrayList<Dealer>();
        source.readList(this.data, Dealer.class.getClassLoader());
    }

    protected DealerListResponseBean(Parcel in) {
        this.message = in.readString();
        this.status = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.data = new ArrayList<Dealer>();
        in.readList(this.data, Dealer.class.getClassLoader());
    }

    public static final Creator<DealerListResponseBean> CREATOR = new Creator<DealerListResponseBean>() {
        @Override
        public DealerListResponseBean createFromParcel(Parcel source) {
            return new DealerListResponseBean(source);
        }

        @Override
        public DealerListResponseBean[] newArray(int size) {
            return new DealerListResponseBean[size];
        }
    };
}
