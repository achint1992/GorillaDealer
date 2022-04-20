
package com.technoecorp.gorilladealer.bean.stateResponse;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class StateResponseBean implements Parcelable {

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("data")
    @Expose
    private ArrayList<StateBean> data = null;
    public final static Creator<StateResponseBean> CREATOR = new Creator<StateResponseBean>() {


        @SuppressWarnings({
                "unchecked"
        })
        public StateResponseBean createFromParcel(Parcel in) {
            return new StateResponseBean(in);
        }

        public StateResponseBean[] newArray(int size) {
            return (new StateResponseBean[size]);
        }

    };

    protected StateResponseBean(Parcel in) {
        this.message = ((String) in.readValue((String.class.getClassLoader())));
        this.status = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        in.readList(this.data, (StateBean.class.getClassLoader()));
    }

    public StateResponseBean() {
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

    public ArrayList<StateBean> getData() {
        return data;
    }

    public void setData(ArrayList<StateBean> data) {
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
