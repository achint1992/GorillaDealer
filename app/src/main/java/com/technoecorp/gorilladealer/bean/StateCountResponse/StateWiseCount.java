
package com.technoecorp.gorilladealer.bean.StateCountResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StateWiseCount {

    @SerializedName("stateName")
    @Expose
    private String stateName;
    @SerializedName("activeCount")
    @Expose
    private Integer activeCount;
    @SerializedName("deactiveCount")
    @Expose
    private Integer deactiveCount;
    @SerializedName("stateId")
    @Expose
    private Integer stateId;
    @SerializedName("count")
    @Expose
    private Integer count;

    public int getColorCode() {
        return colorCode;
    }

    public void setColorCode(int colorCode) {
        this.colorCode = colorCode;
    }

    private int colorCode;

    public StateWiseCount(String stateName, Integer activeCount, Integer deactiveCount, Integer stateId) {
        this.stateName = stateName;
        this.activeCount = activeCount;
        this.deactiveCount = deactiveCount;
        this.stateId = stateId;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
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

    public Integer getStateId() {
        return stateId;
    }

    public void setStateId(Integer stateId) {
        this.stateId = stateId;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

}
