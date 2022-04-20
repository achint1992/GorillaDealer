package com.technoecorp.gorilladealer.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.technoecorp.gorilladealer.R;
import com.technoecorp.gorilladealer.activity.YoutubeFullScreenActivity;
import com.technoecorp.gorilladealer.bean.GalleryResponse.GalleryBean;
import com.technoecorp.gorilladealer.bean.ResourceBean;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GalleryVideoAdapter extends RecyclerView.Adapter<GalleryVideoAdapter.MyViewHolder> {

    Context context;
    ArrayList<GalleryBean> clientBeans;
    ImageLoader imageLoader;

    public GalleryVideoAdapter(Context context) {
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
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_dashboard_gallery, parent, false);
        return new MyViewHolder(itemView);
    }

    @SuppressLint("ResourceType")
    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        Glide.with(context).load("https://img.youtube.com/vi/"+clientBeans.get(position).getVideoId()+"/mqdefault.jpg").into(holder.image);
        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ResourceBean resourceBean = new ResourceBean();
                resourceBean.setUrl(clientBeans.get(position).getUrl());
                resourceBean.setVideoId(clientBeans.get(position).getVideoId());
                Intent intent = new Intent(context, YoutubeFullScreenActivity.class);
                intent.putExtra("resourceBean", resourceBean);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return clientBeans.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.image)
        ImageView image;


        public MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, itemView);

        }
    }
}

