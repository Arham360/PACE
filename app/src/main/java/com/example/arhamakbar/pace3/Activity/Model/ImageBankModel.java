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
    private String DiseaseName = "";
    private String DiseaseURL = "" ;

    public String getDiseaseName() {
        return DiseaseName;
    }

    public void setDiseaseName(String diseaseName) {
        DiseaseName = diseaseName;
    }

    public String getDiseaseURL() {
        return DiseaseURL;
    }

    public void setDiseaseURL(String diseaseURL) {
        DiseaseURL = diseaseURL;
    }
}

