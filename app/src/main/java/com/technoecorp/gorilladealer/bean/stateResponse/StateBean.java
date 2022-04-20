
package com.technoecorp.gorilladealer.bean.stateResponse;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StateBean implements Parcelable
{

    @SerializedName("stateId")
    @Expose
    private Integer stateId;
    @SerializedName("stateName")
    @Expose
    private String stateName;

    public Integer getStateId() {
        return stateId;
    }

    public void setStateId(Integer stateId) {
        this.stateId = stateId;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    @Override
    public String toString() {
        return "Datum{" +
                "stateId=" + stateId +
                ", stateName='" + stateName + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.stateId);
        dest.writeString(this.stateName);
    }

    public StateBean() {
    }

    public StateBean(Integer stateId, String stateName) {
        this.stateId = stateId;
        this.stateName = stateName;
    }

    protected StateBean(Parcel in) {
        this.stateId = (Integer) in.readValue(Integer.class.getClassLoader());
        this.stateName = in.readString();
    }

    public static final Creator<StateBean> CREATOR = new Creator<StateBean>() {
        @Override
        public StateBean createFromParcel(Parcel source) {
            return new StateBean(source);
        }

        @Override
        public StateBean[] newArray(int size) {
            return new StateBean[size];
        }
    };
}
