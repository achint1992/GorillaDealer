package com.technoecorp.gorilladealer.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class ResourceBean implements Parcelable {
    String url;
    String videoId;
    int resourceId;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getResourceId() {
        return resourceId;
    }

    public void setResourceId(int resourceId) {
        this.resourceId = resourceId;
    }

    public ResourceBean(String url, int resourceId) {
        this.url = url;
        this.resourceId = resourceId;
        videoId="";
    }

    public ResourceBean(String url, int resourceId, String videoId) {
        this.url = url;
        this.resourceId = resourceId;
        this.videoId=videoId;
    }

    public ResourceBean() {
    }

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    @Override
    public String toString() {
        return "ResourceBean{" +
                "url='" + url + '\'' +
                ", resourceId=" + resourceId +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.url);
        dest.writeString(this.videoId);
        dest.writeInt(this.resourceId);
    }

    protected ResourceBean(Parcel in) {
        this.url = in.readString();
        this.videoId = in.readString();
        this.resourceId = in.readInt();
    }

    public static final Creator<ResourceBean> CREATOR = new Creator<ResourceBean>() {
        @Override
        public ResourceBean createFromParcel(Parcel source) {
            return new ResourceBean(source);
        }

        @Override
        public ResourceBean[] newArray(int size) {
            return new ResourceBean[size];
        }
    };
}
