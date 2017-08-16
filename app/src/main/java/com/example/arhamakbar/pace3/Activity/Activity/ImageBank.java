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
import com.example.arhamakbar.pace3.Activity.Adapter.ImageBankAdapter;

import com.example.arhamakbar.pace3.Activity.Model.ImageBankModel;
import com.example.arhamakbar.pace3.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ImageBank extends AppCompatActivity
{

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_bank);




//        }
//        mRecyclerView = (RecyclerView)findViewById(R.id.imgRecycler);
//        //recyclerView.setLayoutManager(new LinearLayoutManager(this));//make new layout
//        mLayoutManager = new LinearLayoutManager(this);
//        mRecyclerView.setLayoutManager(mLayoutManager);
//
//        //make adapter?
//        mAdapter = new ImageBankAdapter(getBaseContext(),ImageBankModel.dummyData());
//
//        mRecyclerView.setAdapter(mAdapter);
    }
}
