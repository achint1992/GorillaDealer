
package com.technoecorp.gorilladealer.bean.stateResponse;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CountryBean implements Parcelable {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("countryName")
    @Expose
    private String countryName;
    @SerializedName("countryDialCode")
    @Expose
    private String countryDialCode;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getCountryDialCode() {
        return countryDialCode;
    }

    public void setCountryDialCode(String countryDialCode) {
        this.countryDialCode = countryDialCode;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id);
        dest.writeString(this.countryName);
        dest.writeString(this.countryDialCode);
    }

    public void readFromParcel(Parcel source) {
        this.id = (Integer) source.readValue(Integer.class.getClassLoader());
        this.countryName = source.readString();
        this.countryDialCode = source.readString();
    }

    public CountryBean() {
    }

    protected CountryBean(Parcel in) {
        this.id = (Integer) in.readValue(Integer.class.getClassLoader());
        this.countryName = in.readString();
        this.countryDialCode = in.readString();
    }

    public static final Creator<CountryBean> CREATOR = new Creator<CountryBean>() {
        @Override
        public CountryBean createFromParcel(Parcel source) {
            return new CountryBean(source);
        }

        @Override
        public CountryBean[] newArray(int size) {
            return new CountryBean[size];
        }
    };
}
