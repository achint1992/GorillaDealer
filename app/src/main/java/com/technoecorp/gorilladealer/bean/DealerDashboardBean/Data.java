
package com.technoecorp.gorilladealer.bean.DealerDashboardBean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.technoecorp.gorilladealer.bean.OTPBean.Dealer;

import java.util.ArrayList;
import java.util.List;

public class Data {

    @SerializedName("allDealerCount")
    @Expose
    private Integer allDealerCount;
    @SerializedName("activeDealer")
    @Expose
    private Integer activeDealer;
    @SerializedName("deactiveDealer")
    @Expose
    private Integer deactiveDealer;
    @SerializedName("totalBusiness")
    @Expose
    private Integer totalBusiness;
    @SerializedName("lastWeekBusiness")
    @Expose
    private Integer lastWeekBusiness;
    @SerializedName("lastMonthBusiness")
    @Expose
    private Integer lastMonthBusiness;
    @SerializedName("todayBusiness")
    @Expose
    private Integer todayBusiness;
    @SerializedName("lastWeekList")
    @Expose
    private ArrayList<Dealer> lastWeekList = new ArrayList<>();
    @SerializedName("lastMonthList")
    @Expose
    private ArrayList<Dealer> lastMonthList = new ArrayList<>();
    @SerializedName("todayList")
    @Expose
    private ArrayList<Dealer> todayList = new ArrayList<>();
    @SerializedName("recentDealers")
    @Expose
    private List<String> recentDealers = null;
    @SerializedName("withdrawalData")
    @Expose
    private WithdrawlBean withdrawlBean;

    public WithdrawlBean getWithdrawlBean() {
        return withdrawlBean;
    }

    public void setWithdrawlBean(WithdrawlBean withdrawlBean) {
        this.withdrawlBean = withdrawlBean;
    }

    public Integer getAllDealerCount() {
        return allDealerCount;
    }

    public void setAllDealerCount(Integer allDealerCount) {
        this.allDealerCount = allDealerCount;
    }

    public Integer getActiveDealer() {
        return activeDealer;
    }

    public void setActiveDealer(Integer activeDealer) {
        this.activeDealer = activeDealer;
    }

    public Integer getDeactiveDealer() {
        return deactiveDealer;
    }

    public void setDeactiveDealer(Integer deactiveDealer) {
        this.deactiveDealer = deactiveDealer;
    }

    public Integer getTotalBusiness() {
        return totalBusiness;
    }

    public void setTotalBusiness(Integer totalBusiness) {
        this.totalBusiness = totalBusiness;
    }

    public Integer getLastWeekBusiness() {
        return lastWeekBusiness;
    }

    public void setLastWeekBusiness(Integer lastWeekBusiness) {
        this.lastWeekBusiness = lastWeekBusiness;
    }

    public Integer getLastMonthBusiness() {
        return lastMonthBusiness;
    }

    public void setLastMonthBusiness(Integer lastMonthBusiness) {
        this.lastMonthBusiness = lastMonthBusiness;
    }

    public Integer getTodayBusiness() {
        return todayBusiness;
    }

    public void setTodayBusiness(Integer todayBusiness) {
        this.todayBusiness = todayBusiness;
    }

    public ArrayList<Dealer> getLastWeekList() {
        return lastWeekList;
    }

    public void setLastWeekList(ArrayList<Dealer> lastWeekList) {
        this.lastWeekList = lastWeekList;
    }

    public ArrayList<Dealer> getLastMonthList() {
        return lastMonthList;
    }

    public void setLastMonthList(ArrayList<Dealer> lastMonthList) {
        this.lastMonthList = lastMonthList;
    }

    public ArrayList<Dealer> getTodayList() {
        return todayList;
    }

    public void setTodayList(ArrayList<Dealer> todayList) {
        this.todayList = todayList;
    }

    public List<String> getRecentDealers() {
        return recentDealers;
    }

    public void setRecentDealers(List<String> recentDealers) {
        this.recentDealers = recentDealers;
    }
}
