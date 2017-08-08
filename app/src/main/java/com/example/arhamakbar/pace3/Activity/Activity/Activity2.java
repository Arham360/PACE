package com.example.arhamakbar.pace3.Activity.Activity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.FragmentManager;
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
import com.example.arhamakbar.pace3.Activity.Fragment.MainFragment;
import com.example.arhamakbar.pace3.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class Activity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        Intent intent = getIntent();
        final int id = Integer.parseInt(intent.getStringExtra("id"));
        String title = intent.getStringExtra("title");

          final List<Option> optionsList = null;
          final List<Age> ageList = null;
        // public List<Type> typeList;
          final List<Case> caseList = null;

        final String url = "https://appbrewerydev.uwm.edu/pace/api/v1/init";
        final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url ,null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.v("FUN", "Success" + response.toString());
                try {
                    JSONObject data = response.getJSONObject("data");
                    JSONArray options = data.getJSONArray("options");
                    JSONArray ages = data.getJSONArray("ages");
                    JSONArray cases = data.getJSONArray("cases");
                    //JSONObject ages = options.getJSONObject("ages");

                    for (int a = 0; a < ages.length(); a++) {
                        JSONObject ag = ages.getJSONObject(a);
                        int id = ag.getInt("id");
                        String text = ag.getString("text");
                        Age age = new Age(id, text);
                        ageList.add(age);
                    }

                    for (int b = 0; b < cases.length(); b++) {

                        JSONObject ca = ages.getJSONObject(b);
                        int id = ca.getInt("id");
                        String text = ca.getString("text");
                        String description = ca.getString("description");
                        int age = ca.getInt("ages");
                        JSONArray img = ca.getJSONArray("images");
                        String image = img.getString(0);
                        Case c = new Case(id, text, description, age, image);
                        caseList.add(c);

                    }

                    for (int i = 0; i < options.length(); i++) {
                        JSONObject obj = options.getJSONObject(i);

                        int id = obj.getInt("id");
                        int age = obj.getInt("age");
                        int type = obj.getInt("type");
                        int parent = obj.getInt("parent");
                        String text = obj.getString("text");
                        String caption = obj.getString("caption");
                        JSONArray opt = obj.getJSONArray("options");
                        int[] op = new int[opt.length()];

                        for (int j = 0; j < opt.length(); j++) {
                            op[j] = opt.getInt(j);
                        }
                        JSONArray cas = obj.getJSONArray("cases");
                        int[] c = new int[cas.length()];
                        for (int k = 0; k < cas.length(); k++) {
                            c[k] = cas.getInt(k);
                        }
                        Option option = new Option(id, text, age, type, parent, c, op, caption);
                        optionsList.add(option);
                    }


                }
                catch (JSONException e) {
                    Log.v("FUN", e.getMessage());
                    Log.v("FUN","failed json exception");
                }

            }


        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.v("FUN","fail");
                Log.v("FUN", error.getMessage());

            }
        });
//
        Volley.newRequestQueue(this).add(jsonObjectRequest);

        try{
            SQLiteDatabase mydatabase = this.openOrCreateDatabase("LocalData", MODE_PRIVATE, null);
            mydatabase.execSQL("CREATE TABLE IF NOT EXIST age (id INT (4), text VARCHAR");
            int ageNum = ageList.size();
            for (int d = 0;d<ageNum;d++){
                int j = ageList.get(d).getId();
                String k = ageList.get(d).getText();
                mydatabase.execSQL("INSERT INTO age (id,text) VALUES ('"+j+","+k+ ")");
            }
            int caseNum = caseList.size();
            for (int d = 0; d<caseNum; d++){
                mydatabase.execSQL("CREATE TABLE IF NOT EXIST case (id INT (4), age INT(4), description VARCHAR, text VARCHAR, image VARCHAR");
                int a = caseList.get(d).getId();
                int b = caseList.get(d).getAge();
                String c = caseList.get(d).getDesc();
                String e = caseList.get(d).getText();
                String f = caseList.get(d).getImage();
                mydatabase.execSQL("INSERT INTO case (id,age,description,text,image) VALUES ('"+a+","+b+"," +c +","+e +","+f+  ")");

            }
            int optionsNum = optionsList.size();
            for(int d= 0; d<optionsNum;d++){
                mydatabase.execSQL("CREATE TABLE IF NOT EXIST option (id INT (4), age INT(4), type INT(1),  parent INT(1), text VARCHAR, caption VARCHAR");
                int a = optionsList.get(d).getId();
                int age = optionsList.get(d).getAge();
                int type = optionsList.get(d).getType();
                int parent = optionsList.get(d).getParent();
                String text = optionsList.get(d).getText();
                String caption = optionsList.get(d).getCaption();
                mydatabase.execSQL("INSERT INTO option (id,age,type,parent,text,caption) VALUES ('"+a+","+age+"," +type +","+parent +","+text+ ","+ caption + ")");
            }

        }catch (Exception e){

        }



        // Inflate the layout for this fragment
//        RecyclerView recyclerView = (RecyclerView)v.findViewById(R.id.listFragment);
//        recyclerView.setHasFixedSize(true);
//
//        ArrayList<String> list = new ArrayList<>();
//        //list.add();
//
//        //RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rvAnimals);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
//        MyRecyclerViewAdapter adapter = new MyRecyclerViewAdapter(this.getContext(), list);
//        recyclerView.setAdapter(adapter);
//
//        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
//        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
//        recyclerView.setLayoutManager(layoutManager);

        TextView tv = new TextView(this);
        tv.setText(title);

        FragmentManager fm  = getSupportFragmentManager();
        //MainFragment mainFragment = (MainFragment) fm.findFragmentById(R.layout.activity2_button_fragment);

//        if (mainFragment == null){
//
//            mainFragment = MainFragment.newInstance("","");
//            fm.beginTransaction().add(R.id.listFragment,mainFragment).commit();
//
//        }


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



