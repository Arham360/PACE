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

    List<Option> optionsList = new ArrayList<Option>();
//    final List<Age> ageList = new ArrayList<Age>();
//    // public List<Type> typeList;
//    final List<Case> caseList = new ArrayList<Case>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        Intent intent = getIntent();
        //int id = Integer.parseInt(intent.getStringExtra("id"));
        int age = intent.getIntExtra("id", -1);
        String title = intent.getStringExtra("title");

        if (age <1 || age > 6){
            Log.v("FUN","ERROR");
        }
        Log.v("SQl", "opening the database for read");
        SQLiteDatabase database = this.openOrCreateDatabase("LocalData",MODE_APPEND,null);
        Log.v("SQl", "opened database");
        Cursor c = database.rawQuery("SELECT * FROM option WHERE age = "+ age + "AND type = 1",null);
        Log.v("SQl", "cursor got data");
       // List itemIds = new ArrayList<>();
        c.moveToFirst();
        while (c!=null){

            int id = c.getInt(c.getColumnIndexOrThrow("id"));
            Log.v("2", ""+id);
            int parent  = c.getInt(c.getColumnIndexOrThrow("id"));
            int a  = c.getInt(c.getColumnIndexOrThrow("id"));
            int type  = c.getInt(c.getColumnIndexOrThrow("id"));
            String t = c.getString(c.getColumnIndexOrThrow("id"));
            String caption = c.getString(c.getColumnIndexOrThrow("caption"));
            String options = c.getString(c.getColumnIndexOrThrow("options"));
            String  [] op = options.split(",");
            int [] optionsList = new int[op.length] ;
            for (int i = 0; i < op.length; i++){
                optionsList[i] = Integer.parseInt(op[i]);
                Log.v("PULLING", ""+optionsList[i]);
            }
            for (int i = 0; i < op.length; i++){
                optionsList[i] = Integer.parseInt(op[i]);
                Log.v("PULLING", ""+optionsList[i]);
            }


            //Option option = new Option(id,t,a,type,parent,);
          //  optionsList.add();
            c.moveToNext();
        }


        FragmentManager fm  = getSupportFragmentManager();
        Primary mainFragment = (Primary) fm.findFragmentById(R.id.buttonFragment);

        if (mainFragment == null){
            //do not send in age, send in options
            mainFragment = Primary.newInstance( age  ,title);
            fm.beginTransaction().add(R.id.buttonFragment,mainFragment).commit();

        } else{
            //this means youre past the first symptom page
            //fm.beginTransaction().add(R.id.buttonFragment,mainFragment).addToBackStack().commit();
        }


    }

    public void setActionBarTitle(String newTitle){
        ActionBar ab = getSupportActionBar();
        ab.setTitle(newTitle);
        ab.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FF497FFD")));
    }

    //TODO UPDATE UI METHOD CALLED FROM WITHIN FRAGMENT
    //update UI how?????????????????

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



