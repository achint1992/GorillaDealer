
package com.technoecorp.gorilladealer.bean.KYCResponse;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class KycBean implements Parcelable {

    @SerializedName("kycId")
    @Expose
    private Integer kycId;
    @SerializedName("distributorId")
    @Expose
    private Integer distributorId;
    @SerializedName("dealerId")
    @Expose
    private Integer dealerId;
    @SerializedName("accountName")
    @Expose
    private String accountName;
    @SerializedName("accountNumber")
    @Expose
    private String accountNumber;
    @SerializedName("bankName")
    @Expose
    private String bankName;
    @SerializedName("branchName")
    @Expose
    private String branchName;
    @SerializedName("ifscCode")
    @Expose
    private String ifscCode;
    @SerializedName("accountType")
    @Expose
    private String accountType;
    @SerializedName("addressProofId")
    @Expose
    private String addressProofId;
    @SerializedName("userVerificationId")
    @Expose
    private String userVerificationId;
    @SerializedName("bankProofId")
    @Expose
    private String bankProofId;
    @SerializedName("isKycVerify")
    @Expose
    private Integer isKycVerify=0;
    @SerializedName("isKycRejected")
    @Expose
    private Integer isKycRejected=0;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("timestamp")
    @Expose
    private Long timestamp;
    @SerializedName("createdAt")
    @Expose
    private String createdAt;
    @SerializedName("updatedAt")
    @Expose
    private String updatedAt;

    public Integer getKycId() {
        return kycId;
    }

    public void setKycId(Integer kycId) {
        this.kycId = kycId;
    }

    public Integer getDistributorId() {
        return distributorId;
    }

    public void setDistributorId(Integer distributorId) {
        this.distributorId = distributorId;
    }

    public Integer getDealerId() {
        return dealerId;
    }

    public void setDealerId(Integer dealerId) {
        this.dealerId = dealerId;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getIfscCode() {
        return ifscCode;
    }

    public void setIfscCode(String ifscCode) {
        this.ifscCode = ifscCode;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getAddressProofId() {
        return addressProofId;
    }

    public void setAddressProofId(String addressProofId) {
        this.addressProofId = addressProofId;
    }

    public String getUserVerificationId() {
        return userVerificationId;
    }

    public void setUserVerificationId(String userVerificationId) {
        this.userVerificationId = userVerificationId;
    }

    public String getBankProofId() {
        return bankProofId;
    }

    public void setBankProofId(String bankProofId) {
        this.bankProofId = bankProofId;
    }

    public Integer getIsKycVerify() {
        return isKycVerify;
    }

    public void setIsKycVerify(Integer isKycVerify) {
        this.isKycVerify = isKycVerify;
    }

    public Integer getIsKycRejected() {
        return isKycRejected;
    }

    public void setIsKycRejected(Integer isKycRejected) {
        this.isKycRejected = isKycRejected;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
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
        dest.writeValue(this.kycId);
        dest.writeValue(this.distributorId);
        dest.writeValue(this.dealerId);
        dest.writeString(this.accountName);
        dest.writeString(this.accountNumber);
        dest.writeString(this.bankName);
        dest.writeString(this.branchName);
        dest.writeString(this.ifscCode);
        dest.writeString(this.accountType);
        dest.writeString(this.addressProofId);
        dest.writeString(this.userVerificationId);
        dest.writeString(this.bankProofId);
        dest.writeValue(this.isKycVerify);
        dest.writeValue(this.isKycRejected);
        dest.writeString(this.message);
        dest.writeValue(this.timestamp);
        dest.writeString(this.createdAt);
        dest.writeString(this.updatedAt);
    }

    public void readFromParcel(Parcel source) {
        this.kycId = (Integer) source.readValue(Integer.class.getClassLoader());
        this.distributorId = (Integer) source.readValue(Integer.class.getClassLoader());
        this.dealerId = (Integer) source.readValue(Integer.class.getClassLoader());
        this.accountName = source.readString();
        this.accountNumber = source.readString();
        this.bankName = source.readString();
        this.branchName = source.readString();
        this.ifscCode = source.readString();
        this.accountType = source.readString();
        this.addressProofId = source.readString();
        this.userVerificationId = source.readString();
        this.bankProofId = source.readString();
        this.isKycVerify = (Integer) source.readValue(Integer.class.getClassLoader());
        this.isKycRejected = (Integer) source.readValue(Integer.class.getClassLoader());
        this.message = source.readParcelable(Object.class.getClassLoader());
        this.timestamp = (Long) source.readValue(Long.class.getClassLoader());
        this.createdAt = source.readString();
        this.updatedAt = source.readString();
    }

    public KycBean() {
    }

    protected KycBean(Parcel in) {
        this.kycId = (Integer) in.readValue(Integer.class.getClassLoader());
        this.distributorId = (Integer) in.readValue(Integer.class.getClassLoader());
        this.dealerId = (Integer) in.readValue(Integer.class.getClassLoader());
        this.accountName = in.readString();
        this.accountNumber = in.readString();
        this.bankName = in.readString();
        this.branchName = in.readString();
        this.ifscCode = in.readString();
        this.accountType = in.readString();
        this.addressProofId = in.readString();
        this.userVerificationId = in.readString();
        this.bankProofId = in.readString();
        this.isKycVerify = (Integer) in.readValue(Integer.class.getClassLoader());
        this.isKycRejected = (Integer) in.readValue(Integer.class.getClassLoader());
        this.message = in.readString();
        this.timestamp = (Long) in.readValue(Long.class.getClassLoader());
        this.createdAt = in.readString();
        this.updatedAt = in.readString();
    }

    public static final Creator<KycBean> CREATOR = new Creator<KycBean>() {
        @Override
        public KycBean createFromParcel(Parcel source) {
            return new KycBean(source);
        }

        @Override
        public KycBean[] newArray(int size) {
            return new KycBean[size];
        }
    };
}
