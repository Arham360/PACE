package com.example.arhamakbar.pace3.Activity.Adapter;

/**
 * Created by arhamakbar on 8/21/17.
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.arhamakbar.pace3.Activity.Model.ImageBankModel;
import com.example.arhamakbar.pace3.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {
    private ArrayList<ImageBankModel> imageBankModels;
    private Context context;

    public DataAdapter(Context context,ArrayList<ImageBankModel> imageBankModels) {
        this.context = context;
        this.imageBankModels = imageBankModels;

    }

    @Override
    public DataAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.image_bank_card, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        Log.v("ASDF", imageBankModels.get(i).getDiseaseName());
        //viewHolder.tv_android.setText("HELLO WORLD");
            try {
                viewHolder.tv_android.setText(imageBankModels.get(i).getDiseaseName());
                Picasso.with(context).load(imageBankModels.get(i).getDiseaseURL()).resize(100, 100).into(viewHolder.img_android);
            }catch (Exception e){

            }

    }

    @Override
    public int getItemCount() {
        return imageBankModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tv_android;
        ImageView img_android;
        public ViewHolder(View view) {
            super(view);
            tv_android = (TextView)view.findViewById(R.id.tv_android);
            img_android = (ImageView)view.findViewById(R.id.img_android);
        }
    }
}