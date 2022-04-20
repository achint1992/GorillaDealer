
package com.technoecorp.gorilladealer.bean.cityResponse;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class CityResponseBean implements Parcelable {

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("data")
    @Expose
    private ArrayList<CityBean> data = null;

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

    public ArrayList<CityBean> getData() {
        return data;
    }

    public void setData(ArrayList<CityBean> data) {
        this.data = data;
    }


    @Override
    public String toString() {
        return "CityResponseBean{" +
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
        dest.writeTypedList(this.data);
    }

    public CityResponseBean() {
    }

    protected CityResponseBean(Parcel in) {
        this.message = in.readString();
        this.status = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.data = in.createTypedArrayList(CityBean.CREATOR);
    }

    public static final Creator<CityResponseBean> CREATOR = new Creator<CityResponseBean>() {
        @Override
        public CityResponseBean createFromParcel(Parcel source) {
            return new CityResponseBean(source);
        }

        @Override
        public CityResponseBean[] newArray(int size) {
            return new CityResponseBean[size];
        }
    };
}
