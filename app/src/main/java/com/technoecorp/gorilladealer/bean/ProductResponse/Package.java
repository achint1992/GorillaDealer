
package com.technoecorp.gorilladealer.bean.ProductResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Package {

    @SerializedName("packageId")
    @Expose
    private Integer packageId;
    @SerializedName("productId")
    @Expose
    private Integer productId;
    @SerializedName("packageName")
    @Expose
    private String packageName;
    @SerializedName("packageQty")
    @Expose
    private String packageQty;
    @SerializedName("packageImg")
    @Expose
    private String packageImg="";
    @SerializedName("packagePrice")
    @Expose
    private String packagePrice;
    @SerializedName("dealerAmount")
    @Expose
    private String dealerAmount;
    @SerializedName("distributorAmount")
    @Expose
    private String distributorAmount;
    @SerializedName("companyAmount")
    @Expose
    private String companyAmount;
    @SerializedName("packageOffer")
    @Expose
    private Object packageOffer;
    @SerializedName("isAmountDistribute")
    @Expose
    private Integer isAmountDistribute;
    @SerializedName("createdAt")
    @Expose
    private String createdAt;
    @SerializedName("updatedAt")
    @Expose
    private String updatedAt;

    public String getPackageImg() {
        return packageImg;
    }

    public void setPackageImg(String packageImg) {
        this.packageImg = packageImg;
    }

    public Integer getPackageId() {
        return packageId;
    }

    public void setPackageId(Integer packageId) {
        this.packageId = packageId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getPackageQty() {
        return packageQty;
    }

    public void setPackageQty(String packageQty) {
        this.packageQty = packageQty;
    }

    public String getPackagePrice() {
        return packagePrice;
    }

    public void setPackagePrice(String packagePrice) {
        this.packagePrice = packagePrice;
    }

    public String getDealerAmount() {
        return dealerAmount;
    }

    public void setDealerAmount(String dealerAmount) {
        this.dealerAmount = dealerAmount;
    }

    public String getDistributorAmount() {
        return distributorAmount;
    }

    public void setDistributorAmount(String distributorAmount) {
        this.distributorAmount = distributorAmount;
    }

    public String getCompanyAmount() {
        return companyAmount;
    }

    public void setCompanyAmount(String companyAmount) {
        this.companyAmount = companyAmount;
    }

    public Object getPackageOffer() {
        return packageOffer;
    }

    public void setPackageOffer(Object packageOffer) {
        this.packageOffer = packageOffer;
    }

    public Integer getIsAmountDistribute() {
        return isAmountDistribute;
    }

    public void setIsAmountDistribute(Integer isAmountDistribute) {
        this.isAmountDistribute = isAmountDistribute;
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

}
