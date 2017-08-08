package com.example.arhamakbar.pace3.Activity.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.arhamakbar.pace3.R;


public class ImageViewHolder extends RecyclerView.ViewHolder {
    public String temp;
    public TextView diseaseName;
    public ImageView imageView;

    public ImageViewHolder(View itemView) {
        super(itemView);
        diseaseName = (TextView) itemView.findViewById(R.id.diseaseName);
        imageView = (ImageView) itemView.findViewById(R.id.imageView);

    }
}
