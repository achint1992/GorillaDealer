
package com.technoecorp.gorilladealer.bean.OTPBean;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PaymentDataBean implements Parcelable {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("dealerId")
    @Expose
    private Integer dealerId;
    @SerializedName("distributorId")
    @Expose
    private String distributorId;
    @SerializedName("referByDealerId")
    @Expose
    private String referByDealerId;
    @SerializedName("productId")
    @Expose
    private Integer productId;
    @SerializedName("packageId")
    @Expose
    private Integer packageId;
    @SerializedName("productBuyQty")
    @Expose
    private Integer productBuyQty;
    @SerializedName("order_id")
    @Expose
    private String orderId;
    @SerializedName("payment_id")
    @Expose
    private String paymentId;
    @SerializedName("razorpay_signature")
    @Expose
    private String razorpaySignature;
    @SerializedName("receipt_id")
    @Expose
    private String receiptId;
    @SerializedName("amount")
    @Expose
    private String amount;
    @SerializedName("totalAmount")
    @Expose
    private String totalAmount;
    @SerializedName("paymentStage")
    @Expose
    private String paymentStage;
    @SerializedName("payment_create_at")
    @Expose
    private String paymentCreateAt;
    @SerializedName("captured")
    @Expose
    private Integer captured;
    @SerializedName("distributorAmount")
    @Expose
    private String distributorAmount;
    @SerializedName("dealerAmount")
    @Expose
    private String dealerAmount;
    @SerializedName("companyAmount")
    @Expose
    private String companyAmount;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("bank")
    @Expose
    private String bank;
    @SerializedName("card_id")
    @Expose
    private String cardId;
    @SerializedName("method")
    @Expose
    private String method;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("amount_refunded")
    @Expose
    private String amountRefunded;
    @SerializedName("refund_status")
    @Expose
    private Integer refundStatus;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("contact")
    @Expose
    private String contact;
    @SerializedName("error_code")
    @Expose
    private String errorCode;
    @SerializedName("error_description")
    @Expose
    private String errorDescription;
    @SerializedName("fee")
    @Expose
    private String fee;
    @SerializedName("tax")
    @Expose
    private String tax;
    @SerializedName("refund_id")
    @Expose
    private String refundId;
    @SerializedName("is_widthral")
    @Expose
    private Integer isWidthral;
    @SerializedName("createdAt")
    @Expose
    private String createdAt;
    @SerializedName("updatedAt")
    @Expose
    private String updatedAt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDealerId() {
        return dealerId;
    }

    public void setDealerId(Integer dealerId) {
        this.dealerId = dealerId;
    }

    public String getDistributorId() {
        return distributorId;
    }

    public void setDistributorId(String distributorId) {
        this.distributorId = distributorId;
    }

    public String getReferByDealerId() {
        return referByDealerId;
    }

    public void setReferByDealerId(String referByDealerId) {
        this.referByDealerId = referByDealerId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getPackageId() {
        return packageId;
    }

    public void setPackageId(Integer packageId) {
        this.packageId = packageId;
    }

    public Integer getProductBuyQty() {
        return productBuyQty;
    }

    public void setProductBuyQty(Integer productBuyQty) {
        this.productBuyQty = productBuyQty;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public String getRazorpaySignature() {
        return razorpaySignature;
    }

    public void setRazorpaySignature(String razorpaySignature) {
        this.razorpaySignature = razorpaySignature;
    }

    public String getReceiptId() {
        return receiptId;
    }

    public void setReceiptId(String receiptId) {
        this.receiptId = receiptId;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getPaymentStage() {
        return paymentStage;
    }

    public void setPaymentStage(String paymentStage) {
        this.paymentStage = paymentStage;
    }

    public String getPaymentCreateAt() {
        return paymentCreateAt;
    }

    public void setPaymentCreateAt(String paymentCreateAt) {
        this.paymentCreateAt = paymentCreateAt;
    }

    public Integer getCaptured() {
        return captured;
    }

    public void setCaptured(Integer captured) {
        this.captured = captured;
    }

    public String getDistributorAmount() {
        return distributorAmount;
    }

    public void setDistributorAmount(String distributorAmount) {
        this.distributorAmount = distributorAmount;
    }

    public String getDealerAmount() {
        return dealerAmount;
    }

    public void setDealerAmount(String dealerAmount) {
        this.dealerAmount = dealerAmount;
    }

    public String getCompanyAmount() {
        return companyAmount;
    }

    public void setCompanyAmount(String companyAmount) {
        this.companyAmount = companyAmount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAmountRefunded() {
        return amountRefunded;
    }

    public void setAmountRefunded(String amountRefunded) {
        this.amountRefunded = amountRefunded;
    }

    public Integer getRefundStatus() {
        return refundStatus;
    }

    public void setRefundStatus(Integer refundStatus) {
        this.refundStatus = refundStatus;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorDescription() {
        return errorDescription;
    }

    public void setErrorDescription(String errorDescription) {
        this.errorDescription = errorDescription;
    }

    public String getFee() {
        return fee;
    }

    public void setFee(String fee) {
        this.fee = fee;
    }

    public String getTax() {
        return tax;
    }

    public void setTax(String tax) {
        this.tax = tax;
    }

    public String getRefundId() {
        return refundId;
    }

    public void setRefundId(String refundId) {
        this.refundId = refundId;
    }

    public Integer getIsWidthral() {
        return isWidthral;
    }

    public void setIsWidthral(Integer isWidthral) {
        this.isWidthral = isWidthral;
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
        dest.writeValue(this.id);
        dest.writeValue(this.dealerId);
        dest.writeString(this.distributorId);
        dest.writeString(this.referByDealerId);
        dest.writeValue(this.productId);
        dest.writeValue(this.packageId);
        dest.writeValue(this.productBuyQty);
        dest.writeString(this.orderId);
        dest.writeString(this.paymentId);
        dest.writeString(this.razorpaySignature);
        dest.writeString(this.receiptId);
        dest.writeString(this.amount);
        dest.writeString(this.totalAmount);
        dest.writeString(this.paymentStage);
        dest.writeString(this.paymentCreateAt);
        dest.writeValue(this.captured);
        dest.writeString(this.distributorAmount);
        dest.writeString(this.dealerAmount);
        dest.writeString(this.companyAmount);
        dest.writeString(this.status);
        dest.writeString(this.type);
        dest.writeString(this.bank);
        dest.writeString(this.cardId);
        dest.writeString(this.method);
        dest.writeString(this.description);
        dest.writeString(this.amountRefunded);
        dest.writeValue(this.refundStatus);
        dest.writeString(this.email);
        dest.writeString(this.contact);
        dest.writeString(this.errorCode);
        dest.writeString(this.errorDescription);
        dest.writeString(this.fee);
        dest.writeString(this.tax);
        dest.writeString(this.refundId);
        dest.writeValue(this.isWidthral);
        dest.writeString(this.createdAt);
        dest.writeString(this.updatedAt);
    }

    public void readFromParcel(Parcel source) {
        this.id = (Integer) source.readValue(Integer.class.getClassLoader());
        this.dealerId = (Integer) source.readValue(Integer.class.getClassLoader());
        this.distributorId = source.readString();
        this.referByDealerId = source.readString();
        this.productId = (Integer) source.readValue(Integer.class.getClassLoader());
        this.packageId = (Integer) source.readValue(Integer.class.getClassLoader());
        this.productBuyQty = (Integer) source.readValue(Integer.class.getClassLoader());
        this.orderId = source.readString();
        this.paymentId = source.readString();
        this.razorpaySignature = source.readString();
        this.receiptId = source.readString();
        this.amount = source.readString();
        this.totalAmount = source.readString();
        this.paymentStage = source.readString();
        this.paymentCreateAt = source.readString();
        this.captured = (Integer) source.readValue(Integer.class.getClassLoader());
        this.distributorAmount = source.readString();
        this.dealerAmount = source.readString();
        this.companyAmount = source.readString();
        this.status = source.readString();
        this.type = source.readString();
        this.bank = source.readString();
        this.cardId = source.readString();
        this.method = source.readString();
        this.description = source.readString();
        this.amountRefunded = source.readString();
        this.refundStatus = (Integer) source.readValue(Integer.class.getClassLoader());
        this.email = source.readString();
        this.contact = source.readString();
        this.errorCode = source.readString();
        this.errorDescription = source.readString();
        this.fee = source.readString();
        this.tax = source.readString();
        this.refundId = source.readString();
        this.isWidthral = (Integer) source.readValue(Integer.class.getClassLoader());
        this.createdAt = source.readString();
        this.updatedAt = source.readString();
    }

    public PaymentDataBean() {
    }

    protected PaymentDataBean(Parcel in) {
        this.id = (Integer) in.readValue(Integer.class.getClassLoader());
        this.dealerId = (Integer) in.readValue(Integer.class.getClassLoader());
        this.distributorId = in.readString();
        this.referByDealerId = in.readString();
        this.productId = (Integer) in.readValue(Integer.class.getClassLoader());
        this.packageId = (Integer) in.readValue(Integer.class.getClassLoader());
        this.productBuyQty = (Integer) in.readValue(Integer.class.getClassLoader());
        this.orderId = in.readString();
        this.paymentId = in.readString();
        this.razorpaySignature = in.readString();
        this.receiptId = in.readString();
        this.amount = in.readString();
        this.totalAmount = in.readString();
        this.paymentStage = in.readString();
        this.paymentCreateAt = in.readString();
        this.captured = (Integer) in.readValue(Integer.class.getClassLoader());
        this.distributorAmount = in.readString();
        this.dealerAmount = in.readString();
        this.companyAmount = in.readString();
        this.status = in.readString();
        this.type = in.readString();
        this.bank = in.readString();
        this.cardId = in.readString();
        this.method = in.readString();
        this.description = in.readString();
        this.amountRefunded = in.readString();
        this.refundStatus = (Integer) in.readValue(Integer.class.getClassLoader());
        this.email = in.readString();
        this.contact = in.readString();
        this.errorCode = in.readString();
        this.errorDescription = in.readString();
        this.fee = in.readString();
        this.tax = in.readString();
        this.refundId = in.readString();
        this.isWidthral = (Integer) in.readValue(Integer.class.getClassLoader());
        this.createdAt = in.readString();
        this.updatedAt = in.readString();
    }

    public static final Creator<PaymentDataBean> CREATOR = new Creator<PaymentDataBean>() {
        @Override
        public PaymentDataBean createFromParcel(Parcel source) {
            return new PaymentDataBean(source);
        }

        @Override
        public PaymentDataBean[] newArray(int size) {
            return new PaymentDataBean[size];
        }
    };
}
