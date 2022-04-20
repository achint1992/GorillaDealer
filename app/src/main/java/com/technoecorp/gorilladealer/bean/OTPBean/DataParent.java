package com.technoecorp.gorilladealer.bean.OTPBean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataParent {
    @SerializedName("data")
    @Expose
    private Data data;
    @SerializedName("token")
    @Expose
    private String token;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
