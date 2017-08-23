package com.example.arhamakbar.pace3.Activity.Activity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ScrollView;
import android.widget.TextView;


import com.android.volley.NetworkError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.arhamakbar.pace3.Activity.Activity.data.Age;
import com.example.arhamakbar.pace3.Activity.Activity.data.Case;
import com.example.arhamakbar.pace3.Activity.Activity.data.DatabaseHandler;
import com.example.arhamakbar.pace3.Activity.Activity.data.LocalData;
import com.example.arhamakbar.pace3.Activity.Activity.data.Option;

import com.example.arhamakbar.pace3.Activity.Fragment.Primary;
import com.example.arhamakbar.pace3.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Activity2 extends AppCompatActivity {

    DatabaseHandler databaseHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
        databaseHandler = new DatabaseHandler(this);
        Intent intent = getIntent();
        //int id = Integer.parseInt(intent.getStringExtra("id"));
        int age = intent.getIntExtra("id", -1);
        String title = intent.getStringExtra("title");





        FragmentManager fm  = getSupportFragmentManager();
        Primary mainFragment = (Primary) fm.findFragmentById(R.id.buttonFragment);

        if (mainFragment == null){

            ArrayList<String> initial = new ArrayList<>();
            //initial.add("Options List (Do not touch)");
            //TODO make this untouchable
            initial.add(title);
            mainFragment = Primary.newInstance( age  ,title, true ,initial);
            fm.beginTransaction().add(R.id.buttonFragment,mainFragment).commit();

        }


    }

    public void setActionBarTitle(String newTitle){
        ActionBar ab = getSupportActionBar();
        ab.setTitle(newTitle);
        ab.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FF497FFD")));
    }

    public void makeNewFragment(int age, String newTitle, ArrayList<String> listViewItems){
        FragmentManager fm  = getSupportFragmentManager();
        Primary mainFragment = (Primary) fm.findFragmentById(R.id.buttonFragment);
        mainFragment = Primary.newInstance( age  ,newTitle, false, listViewItems);
        fm.beginTransaction().replace(R.id.buttonFragment,mainFragment).addToBackStack(null).commit();
    }

    @Override
    public void onBackPressed() {

        super.onBackPressed();
    }




    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }


}



