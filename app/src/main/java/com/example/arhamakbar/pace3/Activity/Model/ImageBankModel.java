package com.example.arhamakbar.pace3.Activity.Model;

import android.media.Image;
import android.widget.ImageView;

import com.example.arhamakbar.pace3.R;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by arhamakbar on 7/31/17.
 */

public class ImageBankModel {
    private String name;
    final String DRAWABLE = "drawable/";
    private String path;
    private int img;

    public ImageBankModel(String name, int img) {
        this.name = name;

        this.img = img;
    }

    public String getName() {
        return name;
    }
    public String getPath(){
        return DRAWABLE+path;
    }

    public int getImage(){
        return img;
    }

    public static List<ImageBankModel> dummyData() {
        List<ImageBankModel> list = new ArrayList<>();

        ImageBankModel a = new ImageBankModel("Adrenal Mass Nueroblastoma", R.drawable.amn);
        list.add(a);

        return list;
    }

}

