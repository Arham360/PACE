package com.example.arhamakbar.pace3.Activity.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.arhamakbar.pace3.Activity.Model.ImageBankModel;
import com.example.arhamakbar.pace3.Activity.ViewHolder.ImageViewHolder;
import com.example.arhamakbar.pace3.Activity.Activity.ImageBank;
import com.example.arhamakbar.pace3.R;

import java.util.List;

/**
 * Created by arhamakbar on 7/31/17.
 */

public class ImageBankAdapter extends RecyclerView.Adapter<ImageViewHolder> {

    public List<ImageBankModel> models = ImageBankModel.dummyData();
    public LayoutInflater mInflater;
    private Context context;


    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView mTextView;
        public ViewHolder(TextView v) {
            super(v);
            mTextView = v;
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
//    public ImageBankAdapter(String[] myDataset) {
//
//    }

//    public class ViewHolder extends RecyclerView.ViewHolder{
//        public TextView diseaseName;
//        public ImageView imageView;
//
//    public RecyclerView.ViewHolder(View itemView){
//            super(itemView);
//
//            diseaseName = (TextView) itemView.findViewById(R.id.diseaseName);
//            imageView = (ImageView) itemView.findViewById(R.id.imageView);
//        }
//
        private List<ImageBankModel> mImageBank;
        private Context mContext;
//
        public ImageBankAdapter(Context context, List<ImageBankModel> imageBanks){
            mImageBank = imageBanks;
            mContext = context;
        }
//        private Context getContext(){
//            return mContext;
//        }
//
//    }

    @Override
    public void onBindViewHolder(ImageViewHolder holder, int position) {
       // holder.itemView.(models.get(position).getPath());
        String name = models.get(position).getName();
        holder.diseaseName.setText(name);

        int image = models.get(position).getImage();
        holder.diseaseName.setBackgroundResource(image);

    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    @Override
    public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.activity_image_bank, parent, false);
        Context context = parent.getContext();
        //LayoutInflater inflater = LayoutInflater.from(context);
        return new ImageViewHolder(view);


    }



}
