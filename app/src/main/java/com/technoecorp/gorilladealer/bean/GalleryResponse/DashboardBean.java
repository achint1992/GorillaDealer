package com.technoecorp.gorilladealer.bean.GalleryResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class DashboardBean {
    @SerializedName("photo")
    @Expose
    private ArrayList<GalleryBean> photo=new ArrayList<>();
    @SerializedName("video")
    @Expose
    private ArrayList<GalleryBean> video=new ArrayList<>();

    @SerializedName("pdf")
    @Expose
    private ArrayList<GalleryBean> pdf=new ArrayList<>();

    public ArrayList<GalleryBean> getPhoto() {
        return photo;
    }

    public void setPhoto(ArrayList<GalleryBean> photo) {
        this.photo = photo;
    }

    public ArrayList<GalleryBean> getVideo() {
        return video;
    }

    public void setVideo(ArrayList<GalleryBean> video) {
        this.video = video;
    }

    public ArrayList<GalleryBean> getPdf() {
        return pdf;
    }

    public void setPdf(ArrayList<GalleryBean> pdf) {
        this.pdf = pdf;
    }
}
