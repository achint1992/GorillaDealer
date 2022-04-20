
package com.technoecorp.gorilladealer.bean.OTPBean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Distributor {

    @SerializedName("distributorId")
    @Expose
    private Integer distributorId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("mobileNo")
    @Expose
    private String mobileNo;
    @SerializedName("alternetMobileNo1")
    @Expose
    private Object alternetMobileNo1;
    @SerializedName("userAddress")
    @Expose
    private String userAddress;
    @SerializedName("districtName")
    @Expose
    private Object districtName;
    @SerializedName("countryId")
    @Expose
    private Integer countryId;
    @SerializedName("stateId")
    @Expose
    private Integer stateId;
    @SerializedName("cityId")
    @Expose
    private Integer cityId;
    @SerializedName("pincode")
    @Expose
    private String pincode;
    @SerializedName("referCode")
    @Expose
    private String referCode;
    @SerializedName("isVerifed")
    @Expose
    private Integer isVerifed;
    @SerializedName("isActive")
    @Expose
    private Integer isActive;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("createdAt")
    @Expose
    private String createdAt;
    @SerializedName("updatedAt")
    @Expose
    private String updatedAt;
    @SerializedName("country")
    @Expose
    private Country country;
    @SerializedName("state")
    @Expose
    private State state;
    @SerializedName("city")
    @Expose
    private City city;

    public Integer getDistributorId() {
        return distributorId;
    }

    public void setDistributorId(Integer distributorId) {
        this.distributorId = distributorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public Object getAlternetMobileNo1() {
        return alternetMobileNo1;
    }

    public void setAlternetMobileNo1(Object alternetMobileNo1) {
        this.alternetMobileNo1 = alternetMobileNo1;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public Object getDistrictName() {
        return districtName;
    }

    public void setDistrictName(Object districtName) {
        this.districtName = districtName;
    }

    public Integer getCountryId() {
        return countryId;
    }

    public void setCountryId(Integer countryId) {
        this.countryId = countryId;
    }

    public Integer getStateId() {
        return stateId;
    }

    public void setStateId(Integer stateId) {
        this.stateId = stateId;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getReferCode() {
        return referCode;
    }

    public void setReferCode(String referCode) {
        this.referCode = referCode;
    }

    public Integer getIsVerifed() {
        return isVerifed;
    }

    public void setIsVerifed(Integer isVerifed) {
        this.isVerifed = isVerifed;
    }

    public Integer getIsActive() {
        return isActive;
    }

    public void setIsActive(Integer isActive) {
        this.isActive = isActive;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

}
