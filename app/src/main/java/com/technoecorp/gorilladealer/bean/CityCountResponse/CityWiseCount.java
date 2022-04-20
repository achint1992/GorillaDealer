
package com.technoecorp.gorilladealer.bean.CityCountResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CityWiseCount {

    @SerializedName("stateName")
    @Expose
    private String stateName="";
    @SerializedName("cityName")
    @Expose
    private String cityName;
    @SerializedName("activeCount")
    @Expose
    private Integer activeCount;
    @SerializedName("deactiveCount")
    @Expose
    private Integer deactiveCount;
    @SerializedName("cityId")
    @Expose
    private Integer cityId;
    @SerializedName("count")
    @Expose
    private Integer count;

    public CityWiseCount(String cityName, Integer activeCount, Integer deactiveCount, Integer cityId) {
        this.cityName = cityName;
        this.activeCount = activeCount;
        this.deactiveCount = deactiveCount;
        this.cityId = cityId;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public Integer getActiveCount() {
        return activeCount;
    }

    public void setActiveCount(Integer activeCount) {
        this.activeCount = activeCount;
    }

    public Integer getDeactiveCount() {
        return deactiveCount;
    }

    public void setDeactiveCount(Integer deactiveCount) {
        this.deactiveCount = deactiveCount;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

}
