package com.technoecorp.gorilladealer.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferListener;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferObserver;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferState;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferUtility;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3Client;
import com.bumptech.glide.Glide;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.technoecorp.gorilladealer.R;
import com.technoecorp.gorilladealer.activity.ImageFullActivity;
import com.technoecorp.gorilladealer.activity.PDFActivity;
import com.technoecorp.gorilladealer.bean.GalleryResponse.GalleryBean;
import com.technoecorp.gorilladealer.utils.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GalleryPhotoPdfAdapter extends RecyclerView.Adapter<GalleryPhotoPdfAdapter.MyViewHolder> {

    Context context;
    ArrayList<GalleryBean> clientBeans;
    ImageLoader imageLoader;


    public GalleryPhotoPdfAdapter(Context context) {
        this.context = context;
        this.clientBeans = new ArrayList<>();
        imageLoader = ImageLoader.getInstance();
        imageLoader.init(ImageLoaderConfiguration.createDefault(context));
    }

    public void addAll(ArrayList<GalleryBean> list) {
        clientBeans.clear();
        clientBeans.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.gallery_photo_row, parent, false);
        return new MyViewHolder(itemView);
    }

    @SuppressLint("ResourceType")
    @Override
    public void onBindViewHolder(final MyViewHolder holder, @SuppressLint("RecyclerView") final int position) {

        if (clientBeans.get(position).getType().equalsIgnoreCase("photo")) {
            Glide.with(context).load(clientBeans.get(position).getUrl()).into(holder.iconImage);
        } else {
            holder.iconImage.setImageResource(R.drawable.pdf_icon);
        }
        holder.fileTitle.setText(clientBeans.get(position).getTitle());
        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (clientBeans.get(position).getType().equalsIgnoreCase("photo")) {
                    Intent chart = new Intent(context, ImageFullActivity.class);
                    chart.putExtra("url", clientBeans.get(position).getUrl());
                    chart.putExtra("isDownloaded", false);
                    context.startActivity(chart);
                } else if (clientBeans.get(position).getType().equalsIgnoreCase("pdf")) {
                    Intent chart = new Intent(context, PDFActivity.class);
                    Log.e("PDF is ",clientBeans.get(position).getUrl());
                    chart.putExtra("url", clientBeans.get(position).getUrl());
                    context.startActivity(chart);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return clientBeans.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.downloadIcon)
        ImageView downloadIcon;
        @BindView(R.id.iconImage)
        ImageView iconImage;
        @BindView(R.id.fileTitle)
        TextView fileTitle;
        @BindView(R.id.card)
        LinearLayout card;

        public MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, itemView);

        }
    }
}

