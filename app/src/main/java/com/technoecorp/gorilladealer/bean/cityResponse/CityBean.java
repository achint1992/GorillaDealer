
package com.technoecorp.gorilladealer.bean.cityResponse;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class CityBean implements Parcelable {

    @SerializedName("cityId")
    @Expose
    private Integer cityId;
    @SerializedName("cityName")
    @Expose
    private String cityName;
    @SerializedName("stateId")
    @Expose
    private Integer stateId;

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public Integer getStateId() {
        return stateId;
    }

    public void setStateId(Integer stateId) {
        this.stateId = stateId;
    }


    @Override
    public String toString() {
        return "CityBean{" +
                "cityId=" + cityId +
                ", cityName='" + cityName + '\'' +
                ", stateId=" + stateId +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.cityId);
        dest.writeString(this.cityName);
        dest.writeValue(this.stateId);
    }

    public CityBean() {
    }

    protected CityBean(Parcel in) {
        this.cityId = (Integer) in.readValue(Integer.class.getClassLoader());
        this.cityName = in.readString();
        this.stateId = (Integer) in.readValue(Integer.class.getClassLoader());
    }

    public static final Creator<CityBean> CREATOR = new Creator<CityBean>() {
        @Override
        public CityBean createFromParcel(Parcel source) {
            return new CityBean(source);
        }

        @Override
        public CityBean[] newArray(int size) {
            return new CityBean[size];
        }
    };
}
