package com.technoecorp.gorilladealer.activity;

import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.android.material.button.MaterialButton;
import com.technoecorp.gorilladealer.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ImageFullActivity extends AppCompatActivity {

    @BindView(R.id.linearBack)
    LinearLayout linearBack;
    @BindView(R.id.imageView)
    ImageView imageView;
    @BindView(R.id.btnExport)
    MaterialButton btnExport;
    DownloadManager manager;
    String url;
    boolean isDownloaded;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_full);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        url = intent.getStringExtra("url");
        isDownloaded=intent.getBooleanExtra("isDownloaded",false);
        if (url == null) {
            finish();
        }

        Glide.with(ImageFullActivity.this).load(url).into(imageView);
        linearBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btnExport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                manager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
                Uri uri = Uri.parse(url);
                String fileName = url.substring(url.lastIndexOf('/') + 1, url.length());
                DownloadManager.Request request = new DownloadManager.Request(uri);
                request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE);
                request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, fileName);
                long reference = manager.enqueue(request);
            }
        });
        btnExport.setText("Download");

    }
}