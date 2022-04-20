package com.technoecorp.gorilladealer.utils;

/**
 * Created by developer on 27/02/16.
 */
public class NetworkStatusSingleton {
    static NetworkStatusSingleton instance;
    static String networkStatus="NoInternet";
    public static NetworkStatusSingleton getInstance(){
        if(instance==null){
            instance=new NetworkStatusSingleton();
        }
        return instance;
    }

    public void setNetworkStatus(String networkStatus){
       this.networkStatus= networkStatus;
    }

    public String getNetworkStatus(){
        return networkStatus;
    }
}
