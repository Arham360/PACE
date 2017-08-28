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

import com.example.arhamakbar.pace3.Activity.Fragment.CaseFragment;
import com.example.arhamakbar.pace3.Activity.Fragment.Primary;
import com.example.arhamakbar.pace3.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class OptionsActivity extends AppCompatActivity {

    DatabaseHandler databaseHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
        databaseHandler = new DatabaseHandler(this);
        Intent intent = getIntent();//pulling data from previous activity
        int age = intent.getIntExtra("id", -1);//getting selected age
        String title = intent.getStringExtra("title"); //getting selected title


        FragmentManager fm  = getSupportFragmentManager();
        Primary mainFragment = (Primary) fm.findFragmentById(R.id.buttonFragment);

        if (mainFragment == null){
            //make new initial fragment if none exist
            ArrayList<String> initial = new ArrayList<>();
            initial.add(title);
            mainFragment = Primary.newInstance( age  ,title, 1 ,initial);
            fm.beginTransaction().add(R.id.buttonFragment,mainFragment).commit();

        }


    }

    public void setActionBarTitle(String newTitle){
        //programatically updates the actionbar title for each screen
        ActionBar ab = getSupportActionBar();
        ab.setTitle(newTitle);
        ab.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#0082c8")));
    }

    public void makeNewFragment(int age, String newTitle, ArrayList<String> listViewItems){
        //shows the next list of options
        FragmentManager fm  = getSupportFragmentManager();
        Primary mainFragment = (Primary) fm.findFragmentById(R.id.buttonFragment);
        mainFragment = Primary.newInstance( age  ,newTitle, 2, listViewItems);
        fm.beginTransaction().replace(R.id.buttonFragment,mainFragment).addToBackStack(null).commit();
    }

    public void makeNewCaseFragment(int age, String newTitle, ArrayList<String> listViewItems){
        //shows a list of cases
        FragmentManager fm  = getSupportFragmentManager();
        Primary mainFragment = (Primary) fm.findFragmentById(R.id.buttonFragment);
        mainFragment = Primary.newInstance( age  ,newTitle, 3, listViewItems);
        fm.beginTransaction().replace(R.id.buttonFragment,mainFragment).addToBackStack(null).commit();
    }

    public void showCaseFragment(int id ){
        //shows the picture of the selected case
        FragmentManager fm  = getSupportFragmentManager();
        CaseFragment caseFragment;
        caseFragment = CaseFragment.newInstance(id);
        fm.beginTransaction().replace(R.id.buttonFragment,caseFragment).addToBackStack(null).commit();
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



