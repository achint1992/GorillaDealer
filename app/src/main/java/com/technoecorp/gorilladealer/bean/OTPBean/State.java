
package com.technoecorp.gorilladealer.bean.OTPBean;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class State implements Parcelable {

    @SerializedName("stateId")
    @Expose
    private Integer stateId=-1;
    @SerializedName("stateName")
    @Expose
    private String stateName;
    @SerializedName("countryId")
    @Expose
    private Integer countryId;
    @SerializedName("createdAt")
    @Expose
    private String createdAt;
    @SerializedName("updatedAt")
    @Expose
    private String updatedAt;

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

    public Integer getCountryId() {
        return countryId;
    }

    public void setCountryId(Integer countryId) {
        this.countryId = countryId;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.stateId);
        dest.writeString(this.stateName);
        dest.writeValue(this.countryId);
        dest.writeString(this.createdAt);
        dest.writeString(this.updatedAt);
    }

    public void readFromParcel(Parcel source) {
        this.stateId = (Integer) source.readValue(Integer.class.getClassLoader());
        this.stateName = source.readString();
        this.countryId = (Integer) source.readValue(Integer.class.getClassLoader());
        this.createdAt = source.readString();
        this.updatedAt = source.readString();
    }

    public State() {
    }

    protected State(Parcel in) {
        this.stateId = (Integer) in.readValue(Integer.class.getClassLoader());
        this.stateName = in.readString();
        this.countryId = (Integer) in.readValue(Integer.class.getClassLoader());
        this.createdAt = in.readString();
        this.updatedAt = in.readString();
    }

    public static final Parcelable.Creator<State> CREATOR = new Parcelable.Creator<State>() {
        @Override
        public State createFromParcel(Parcel source) {
            return new State(source);
        }

        @Override
        public State[] newArray(int size) {
            return new State[size];
        }
    };
}
