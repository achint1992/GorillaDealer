
package com.technoecorp.gorilladealer.bean.stateResponse;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class CountryResponseBean implements Parcelable {

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("data")
    @Expose
    private ArrayList<CountryBean> data = new ArrayList<>();
    public final static Creator<CountryResponseBean> CREATOR = new Creator<CountryResponseBean>() {


        @SuppressWarnings({
                "unchecked"
        })
        public CountryResponseBean createFromParcel(Parcel in) {
            return new CountryResponseBean(in);
        }

        public CountryResponseBean[] newArray(int size) {
            return (new CountryResponseBean[size]);
        }

    };

    protected CountryResponseBean(Parcel in) {
        this.message = ((String) in.readValue((String.class.getClassLoader())));
        this.status = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        in.readList(this.data, (CountryBean.class.getClassLoader()));
    }

    public CountryResponseBean() {
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

    public ArrayList<CountryBean> getData() {
        return data;
    }

    public void setData(ArrayList<CountryBean> data) {
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

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(message);
        dest.writeValue(status);
        dest.writeList(data);
    }

    public int describeContents() {
        return 0;
    }

}
