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
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);


        ArrayList diseases = prepareData();
        DataAdapter adapter = new DataAdapter(getApplicationContext(),diseases);
        mRecyclerView.setAdapter(adapter);

    }

    private ArrayList prepareData(){

        ArrayList ret = new ArrayList<>();
        for(int i=1;i<333;i++){
                //Instantiate a new model
                ImageBankModel m = new ImageBankModel();

                //fetching the url for the current disease
                String url = databaseHandler.getCaseImage(i);

                //fetching the name for the current disease
                String name = databaseHandler.getCaseText(i);

                //ensuring that the url is something that can be processed by picasso
                if (url != null && url.length()>=1) {
                    m.setDiseaseName(name);
                    m.setDiseaseURL(url);
                    ret.add(m); //adding to the return array for the adapter
                }


        }
        return ret;
    }
}
