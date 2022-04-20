
package com.technoecorp.gorilladealer.bean.OTPBean;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.technoecorp.gorilladealer.bean.KYCResponse.KycBean;

import java.util.ArrayList;

public class Dealer implements Parcelable {

    @SerializedName("dealerId")
    @Expose
    private Integer dealerId;
    @SerializedName("distributorId")
    @Expose
    private Integer distributorId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("mobileNo")
    @Expose
    private String mobileNo;
    @SerializedName("email")
    @Expose
    private String email="";
    @SerializedName("alternetMobileNo1")
    @Expose
    private String alternetMobileNo1="";
    @SerializedName("gender")
    @Expose
    private String gender;
    @SerializedName("userAddress")
    @Expose
    private String userAddress="";
    @SerializedName("districtName")
    @Expose
    private String districtName="";
    @SerializedName("countryId")
    @Expose
    private String countryId;
    @SerializedName("stateId")
    @Expose
    private String stateId;
    @SerializedName("cityId")
    @Expose
    private String cityId;
    @SerializedName("pincode")
    @Expose
    private String pincode="";
    @SerializedName("referCode")
    @Expose
    private String referCode;
    @SerializedName("isVerifed")
    @Expose
    private Integer isVerifed;
    @SerializedName("isActive")
    @Expose
    private Integer isActive;
    @SerializedName("referBy")
    @Expose
    private String referBy;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("profilePic")
    @Expose
    private String profilePic;
    @SerializedName("fatherName")
    @Expose
    private String fatherName="";
    @SerializedName("dob")
    @Expose
    private String dob="";
    @SerializedName("timestamp")
    @Expose
    private Long timestamp;
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
    @SerializedName("KycDetail")
    @Expose
    private KycBean kycBean;
    @SerializedName("payment")
    @Expose
    private ArrayList<PaymentDataBean> payment = new ArrayList<>();

    public ArrayList<PaymentDataBean> getPayment() {
        return payment;
    }

    public void setPayment(ArrayList<PaymentDataBean> payment) {
        this.payment = payment;
    }

    public KycBean getKycBean() {
        return kycBean;
    }

    public void setKycBean(KycBean kycBean) {
        this.kycBean = kycBean;
    }

    public Integer getDealerId() {
        return dealerId;
    }

    public void setDealerId(Integer dealerId) {
        this.dealerId = dealerId;
    }

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAlternetMobileNo1() {
        return alternetMobileNo1;
    }

    public void setAlternetMobileNo1(String alternetMobileNo1) {
        this.alternetMobileNo1 = alternetMobileNo1;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public String getCountryId() {
        return countryId;
    }

    public void setCountryId(String countryId) {
        this.countryId = countryId;
    }

    public String getStateId() {
        return stateId;
    }

    public void setStateId(String stateId) {
        this.stateId = stateId;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
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

    public String getReferBy() {
        return referBy;
    }

    public void setReferBy(String referBy) {
        this.referBy = referBy;
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

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.dealerId);
        dest.writeValue(this.distributorId);
        dest.writeString(this.name);
        dest.writeString(this.mobileNo);
        dest.writeString(this.email);
        dest.writeString(this.alternetMobileNo1);
        dest.writeString(this.gender);
        dest.writeString(this.userAddress);
        dest.writeString(this.districtName);
        dest.writeString(this.countryId);
        dest.writeString(this.stateId);
        dest.writeString(this.cityId);
        dest.writeString(this.pincode);
        dest.writeString(this.referCode);
        dest.writeValue(this.isVerifed);
        dest.writeValue(this.isActive);
        dest.writeString(this.referBy);
        dest.writeString(this.type);
        dest.writeString(this.profilePic);
        dest.writeString(this.fatherName);
        dest.writeString(this.dob);
        dest.writeValue(this.timestamp);
        dest.writeString(this.createdAt);
        dest.writeString(this.updatedAt);
        dest.writeParcelable(this.country, flags);
        dest.writeParcelable(this.state, flags);
        dest.writeParcelable(this.city, flags);
        dest.writeParcelable(this.kycBean, flags);
        dest.writeTypedList(this.payment);
    }

    public void readFromParcel(Parcel source) {
        this.dealerId = (Integer) source.readValue(Integer.class.getClassLoader());
        this.distributorId = (Integer) source.readValue(Integer.class.getClassLoader());
        this.name = source.readString();
        this.mobileNo = source.readString();
        this.email = source.readString();
        this.alternetMobileNo1 = source.readString();
        this.gender = source.readString();
        this.userAddress = source.readString();
        this.districtName = source.readString();
        this.countryId = source.readString();
        this.stateId = source.readString();
        this.cityId = source.readString();
        this.pincode = source.readString();
        this.referCode = source.readString();
        this.isVerifed = (Integer) source.readValue(Integer.class.getClassLoader());
        this.isActive = (Integer) source.readValue(Integer.class.getClassLoader());
        this.referBy = source.readString();
        this.type = source.readString();
        this.profilePic = source.readString();
        this.fatherName = source.readString();
        this.dob = source.readString();
        this.timestamp = (Long) source.readValue(Long.class.getClassLoader());
        this.createdAt = source.readString();
        this.updatedAt = source.readString();
        this.country = source.readParcelable(Country.class.getClassLoader());
        this.state = source.readParcelable(State.class.getClassLoader());
        this.city = source.readParcelable(City.class.getClassLoader());
        this.kycBean = source.readParcelable(KycBean.class.getClassLoader());
        this.payment = source.createTypedArrayList(PaymentDataBean.CREATOR);
    }

    public Dealer() {
    }

    protected Dealer(Parcel in) {
        this.dealerId = (Integer) in.readValue(Integer.class.getClassLoader());
        this.distributorId = (Integer) in.readValue(Integer.class.getClassLoader());
        this.name = in.readString();
        this.mobileNo = in.readString();
        this.email = in.readString();
        this.alternetMobileNo1 = in.readString();
        this.gender = in.readString();
        this.userAddress = in.readString();
        this.districtName = in.readString();
        this.countryId = in.readString();
        this.stateId = in.readString();
        this.cityId = in.readString();
        this.pincode = in.readString();
        this.referCode = in.readString();
        this.isVerifed = (Integer) in.readValue(Integer.class.getClassLoader());
        this.isActive = (Integer) in.readValue(Integer.class.getClassLoader());
        this.referBy = in.readString();
        this.type = in.readString();
        this.profilePic = in.readString();
        this.fatherName = in.readString();
        this.dob = in.readString();
        this.timestamp = (Long) in.readValue(Long.class.getClassLoader());
        this.createdAt = in.readString();
        this.updatedAt = in.readString();
        this.country = in.readParcelable(Country.class.getClassLoader());
        this.state = in.readParcelable(State.class.getClassLoader());
        this.city = in.readParcelable(City.class.getClassLoader());
        this.kycBean = in.readParcelable(KycBean.class.getClassLoader());
        this.payment = in.createTypedArrayList(PaymentDataBean.CREATOR);
    }

    public static final Creator<Dealer> CREATOR = new Creator<Dealer>() {
        @Override
        public Dealer createFromParcel(Parcel source) {
            return new Dealer(source);
        }

        @Override
        public Dealer[] newArray(int size) {
            return new Dealer[size];
        }
    };
}
