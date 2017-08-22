package com.example.arhamakbar.pace3.Activity.Activity;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.arhamakbar.pace3.Activity.Activity.data.DatabaseHandler;
import com.example.arhamakbar.pace3.Activity.Adapter.DataAdapter;

import com.example.arhamakbar.pace3.Activity.Model.ImageBankModel;
import com.example.arhamakbar.pace3.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class ImageBank extends AppCompatActivity
{
    DatabaseHandler databaseHandler;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_bank);

        databaseHandler = new DatabaseHandler(this);
        mRecyclerView = (RecyclerView)findViewById(R.id.card_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));//make new layout
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);


        ArrayList androidVersions = prepareData();
        DataAdapter adapter = new DataAdapter(getApplicationContext(),androidVersions);
        mRecyclerView.setAdapter(adapter);

    }

    private ArrayList prepareData(){

        ArrayList ret = new ArrayList<>();
        for(int i=1;i<333;i++){

                ImageBankModel m = new ImageBankModel();
               // Log.v("image","getting here");
                String url = databaseHandler.getCaseImage(i);

                String name = databaseHandler.getCaseText(i);

                if (url != null && url.length()>=1) {
                    Log.v("image",url+"     ");
                    Log.v("image",name);
                    m.setDiseaseName(name);
                    m.setDiseaseURL(url);
                    ret.add(m);
                }
               // Log.v("image","stuff added");

        }
        return ret;
    }
}
