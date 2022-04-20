package com.technoecorp.gorilladealer.utils;

import android.content.Context;
import android.util.Log;


import com.google.gson.Gson;
import com.technoecorp.gorilladealer.interfaces.OkHttpCustomResponse;

import org.json.JSONObject;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


/**
 * Created by developer on 09/07/16.
 */
public class HttpCall implements Callback{

    private static final String TAG = HttpCall.class.getSimpleName();
    private OkHttpCustomResponse okHttpCustomResponse;

    public void setOkHttpCustomResponse(OkHttpCustomResponse listener) {
        okHttpCustomResponse = listener;
    }

    public void httpWithURLENCODE(Map mp, String url, Context context) {

        if (okHttpCustomResponse != null) {
            OkHttpClient client = new OkHttpClient.Builder()
                    .connectTimeout(60, TimeUnit.SECONDS)
                    .writeTimeout(60, TimeUnit.SECONDS)
                    .readTimeout(60, TimeUnit.SECONDS)
                    .build();
            FormBody.Builder builder = new FormBody.Builder();
            Iterator it = mp.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry pair = (Map.Entry) it.next();
                builder.add(pair.getKey().toString(), pair.getValue().toString());
                Log.e(pair.getKey().toString(), pair.getValue().toString());
                it.remove(); // avoids a ConcurrentModificationException
            }
            RequestBody requestBody = builder.build();

            Request request = new Request.Builder()
                    .url(url)
                    .addHeader("content-type", "application/x-www-form-urlencoded")
                    .post(requestBody)
                    .build();
            client.newCall(request).enqueue(this);
        } else {
           ToastUtil.showToast(context, "No listener has been created...");
        }
    }

    public void callNewHTTP(Map mp, String url, Context context) {
        if (okHttpCustomResponse != null) {
            OkHttpClient client = new OkHttpClient.Builder()
                    .connectTimeout(60, TimeUnit.SECONDS)
                    .writeTimeout(60, TimeUnit.SECONDS)
                    .readTimeout(60, TimeUnit.SECONDS)
                    .build();
            MediaType mediaType = MediaType.parse("application/json");
            Gson gson = new Gson();
            String json = gson.toJson(mp);
            RequestBody requestBody = RequestBody.create(mediaType, json);
            Log.e("request json is", json);
            Request request = new Request.Builder()
                    .url(url)
                    .post(requestBody)
                    .build();

            client.newCall(request).enqueue(this);
        } else {
            ToastUtil.showToast(context, "No listener has been created...");
        }
    }

    public void callHTTPWithJSON(JSONObject obj, String url, Context context) {
        if (okHttpCustomResponse != null) {
            OkHttpClient client = new OkHttpClient.Builder()
                    .connectTimeout(60, TimeUnit.SECONDS)
                    .writeTimeout(60, TimeUnit.SECONDS)
                    .readTimeout(60, TimeUnit.SECONDS)
                    .build();
            MediaType mediaType = MediaType.parse("application/json");
            RequestBody requestBody = RequestBody.create(mediaType, obj.toString());
            Log.e("Request Json is ",obj.toString());
            Request request = new Request.Builder()
                    .url(url)
                    .post(requestBody)
                    .build();

            client.newCall(request).enqueue(this);
        } else {
           ToastUtil.showToast(context, "No listener has been created...");
        }
    }


    public void rawAPI(String url, Context context, String bodyString) {
        if (okHttpCustomResponse != null) {
            OkHttpClient client = new OkHttpClient.Builder()
                    .connectTimeout(60, TimeUnit.SECONDS)
                    .writeTimeout(60, TimeUnit.SECONDS)
                    .readTimeout(60, TimeUnit.SECONDS)
                    .build();
            Log.e("string requestbody", bodyString);
            MediaType mediaType = MediaType.parse("application/json");
            RequestBody body = RequestBody.create(mediaType, bodyString);
            Request request = new Request.Builder()
                    .url(url)
                    .post(body)
                    .addHeader("content-type", "application/json")
                    .build();

            client.newCall(request).enqueue(this);
        } else {
           ToastUtil.showToast(context, "No listener has been created...");
        }
    }

    public void callGetWithoutParameter(String url, Context context) {
        if (okHttpCustomResponse != null) {
            OkHttpClient client = new OkHttpClient.Builder()
                    .connectTimeout(60, TimeUnit.SECONDS)
                    .writeTimeout(60, TimeUnit.SECONDS)
                    .readTimeout(60, TimeUnit.SECONDS)
                    .build();

            Request request = new Request.Builder()
                    .url(url)
                    .get()
                    .build();

            client.newCall(request).enqueue(this);
        } else {
           ToastUtil.showToast(context, "No listener has been created...");
        }
    }


    @Override
    public void onFailure(Call call, IOException e) {
        Log.e("Error",e.getMessage());
        okHttpCustomResponse.onFailure(call, e);
    }

    @Override
    public void onResponse(Call call, Response response) throws IOException {
        switch (response.code()) {
            case 500:
                onFailure(call, new IOException("Unexpected code " + response));
            default:
                if (response.code() >= 200 && response.code() < 300) {
                    okHttpCustomResponse.onResponse(call, response);
                } else {
                    onFailure(call, new IOException("Unexpected code " + response));
                }
                break;
        }
    }
}
