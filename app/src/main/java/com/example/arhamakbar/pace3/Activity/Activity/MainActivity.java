package com.example.arhamakbar.pace3.Activity.Activity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

//import com.example.arhamakbar.pace.Fragments.BlankFragment;
//import com.example.arhamakbar.pace.Fragments.ListFragment;
//import com.example.arhamakbar.pace.R;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.arhamakbar.pace3.Activity.Activity.data.Age;
import com.example.arhamakbar.pace3.Activity.Activity.data.Case;
import com.example.arhamakbar.pace3.Activity.Activity.data.Option;
import com.example.arhamakbar.pace3.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    final List<Option> optionsList = new ArrayList<Option>();
    final List<Age> ageList = new ArrayList<Age>();
    // public List<Type> typeList;
    final List<Case> caseList = new ArrayList<Case>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        //todo make init into boolean so you can check if it passed or not and display toast if not
        //todo put conditions around init to check if you wanna update it soon.
        //save date into systems preferences and then compare if you wanna update it or not i guess
        //ask joel or chris

        String title = "P.A.C.E ";
        ActionBar ab = getSupportActionBar();
        ab.setTitle(title);
        ab.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FF497FFD")));
        //ab.setBackgroundDrawable(new ColorDrawable(0x0000ff));
        Button b1,b2,b3,b4,b5,b6;
        b1 = (Button)findViewById(R.id.b1);
        b2 = (Button)findViewById(R.id.b2);
        b3 = (Button)findViewById(R.id.b3);
        b4 = (Button)findViewById(R.id.b4);
        b5 = (Button)findViewById(R.id.b5);
        b6 = (Button)findViewById(R.id.b6);
        Button imageBank = (Button)findViewById(R.id.imageBank);

        b1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Activity2.class);
                intent.putExtra("title","0 months to 3 months");
                intent.putExtra("id",1);
                startActivity(intent);
            }
        });
        b2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Activity2.class);
                intent.putExtra("title","3 months to 12 months");
                intent.putExtra("id",2);
                startActivity(intent);
            }
        });
        b3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Activity2.class);
                intent.putExtra("title","12 months to 3 years");
                intent.putExtra("id",3);
                startActivity(intent);
            }
        });
        b4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Activity2.class);
                intent.putExtra("title","3 years to 12 years");
                intent.putExtra("id",4);

                startActivity(intent);
            }
        });
        b5.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Activity2.class);
                intent.putExtra("title","12 years to 18 years male");
                intent.putExtra("id",5);
                startActivity(intent);
            }
        });
        b6.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Activity2.class);
                intent.putExtra("title","12 years to 18 years female");
                intent.putExtra("id",6);
                startActivity(intent);
            }
        });
        imageBank.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ImageBank.class);
                //intent.putExtra(title,"Image Bank");
                startActivity(intent);
            }
        });

    }




    private void init() {

        final String url = "https://appbrewerydev.uwm.edu/pace/api/v1/init";
        final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url ,null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.v("FUN", "Success getting request" + response.toString());
                try {
                    JSONObject data = response.getJSONObject("data");
                    try {
                        JSONArray ages = data.getJSONArray("ages");
                        Log.v("FUN", ages.toString() );
                        for (int a = 0; a < ages.length(); a++) {
                            JSONObject ag = ages.getJSONObject(a);
                            int id = ag.getInt("id");
                            String text = ag.getString("text");
                            Age age = new Age(id, text);
                            ageList.add(age);

                        }
                        Log.v("FUN", "Success ages" );

                    } catch (Exception e){
                        Log.v("FUN", "Failed ages" + e.getLocalizedMessage() );

                    }


                    try {
                        JSONArray cases = data.getJSONArray("cases");
                        Log.v("FUN", cases.toString() );
                        for (int b = 0; b < cases.length(); b++) {

                            JSONObject ca = cases.getJSONObject(b);
                            int id = ca.getInt("id");
                            String text = ca.getString("text");
                            //String description = ca.getString("description");
                            int age = ca.getInt("age");
                            JSONArray img = ca.getJSONArray("images");
                            String image;
                            if (img.length()==0){
                                image= "";
                            }else {
                                image = img.getString(0);
                            }
                            Case c = new Case(id, text, age, image);
                            caseList.add(c);


                        }   Log.v("FUN","Success cases");
                    }catch (Exception e){
                        Log.v("FUN", "Failed cases"  + e.getLocalizedMessage());

                    }

                    try {
                        JSONArray options = data.getJSONArray("options");
                        for (int i = 0; i < options.length(); i++) {
                            JSONObject obj = options.getJSONObject(i);

                            int id = obj.getInt("id");
                            int age = obj.getInt("age");
                            int type = obj.getInt("type");
                            //Integer parent = obj.getInt("parent");

                            int parent = -1;
                            String b =null;
                            try {
                                if (!obj.getString("parent").equals(b)) {
                                    parent = obj.getInt("parent");
                                    //Log.v("FUN", "Value initialized");
                                }
                            }catch (Exception e){
                                //Log.v ("FUN",e.getLocalizedMessage());
                                parent = 0;
                            }
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
                            //Log.v("FUN", "Success option" + id + "    " + text);


                        }Log.v("FUN", "Success option");
                        SQLwrite();
                    }catch (Exception e) {
                        Log.v("FUN", "Failed options"+ e.getLocalizedMessage());

                    }



                } catch (JSONException e) {
                    Log.v("FUN", e.getMessage());
                    Log.v("FUN", "failed json exception");
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

    }

    protected void SQLwrite (){

        try{
            Log.v("SQL","starting database");
            SQLiteDatabase mydatabase = this.openOrCreateDatabase("LocalData", MODE_PRIVATE, null);
            mydatabase.execSQL("CREATE TABLE IF NOT EXISTS age (id INTEGER, t TEXT);");

            try {
                int ageNum = ageList.size();

                for (int d = 0; d < ageNum; d++) {
                    //open transaction stream
                    mydatabase.beginTransaction();
                    int j = ageList.get(d).getId();
                    String k = ageList.get(d).getText();
                    mydatabase.execSQL("INSERT INTO age (id,t) VALUES (" + j + ",'" +  k + "');");
                   // Log.v("SQL", "inserting ages to database");
                }Log.v("SQL", "success ages database");
            }catch (Exception e){
                Log.v("SQL","failed ages");
            }
            mydatabase.endTransaction();
            //close transaction stream

            try{
                int caseNum = caseList.size();
                mydatabase.execSQL("CREATE TABLE IF NOT EXISTS ca (id INTEGER, age INTEGER, text TEXT, image TEXT);");
                for (int d = 0; d<caseNum; d++){
                    mydatabase.beginTransaction();

                    int a = caseList.get(d).getId();
                    int b = caseList.get(d).getAge();
                    String c = caseList.get(d).getDesc();
                    String e = caseList.get(d).getText();
                    String f = caseList.get(d).getImage();
                    mydatabase.execSQL("INSERT INTO ca (id,age,text,image) VALUES ("+a+","+b+",'"+e +"','"+f+  "');");
                   // Log.v("SQL", "inserting cases to database");
                }Log.v("SQL", "success cases DB");
            }catch (Exception e){
                Log.v("SQL","failed cases");
            }

            mydatabase.endTransaction();

            try {

                int optionsNum = optionsList.size();
                mydatabase.execSQL("CREATE TABLE IF NOT EXISTS option (id INTEGER, age INTEGER, type INTEGER,  parent INTEGER, text TEXT, caption TEXT, options TEXT, cases TEXT);");
                for (int d = 0; d < optionsNum; d++) {
                    mydatabase.beginTransaction();
                    int a = optionsList.get(d).getId();
                    int age = optionsList.get(d).getAge();
                    int type = optionsList.get(d).getType();
                    int parent = optionsList.get(d).getParent();
                    String t = optionsList.get(d).getText();
                    String caption = optionsList.get(d).getCaption();
                    String options = "";
                    int [] op = optionsList.get(d).getOptions();
                    for (int i = 0; i < op.length; i++){
                        options += op[i] + ",";
                        //Log.v("OPTIONS","currently on " + options + t + a);

                    }
                    Log.v("OPTIONS","success options");
                    String cas = "";
                    int [] ca = optionsList.get(d).getCases();
                    for (int i = 0; i < ca.length; i++){
                        cas += ca[i] + ",";
                        //Log.v("OPTIONS","adding" + cas);
                    }
                    Log.v("OPTIONS","success cases");

                    mydatabase.execSQL("INSERT INTO option (id,age,type,parent,text,caption,options,cases) VALUES (" + a + "," + age + "," + type + "," + parent + ",'" + t + "','" + caption + "','"+ options + "','"+ cas + "' );");
                    //Log.v("SQL", "inserting options to database");
                }
                Log.v("DB","Ending transaction");
                mydatabase.endTransaction();
                Log.v("DB","CLosing DB");
                mydatabase.close();

                Log.v("DB","Db closed!");

            }catch (Exception e){
                Log.v("SQL","failed options");
            }

        }catch (Exception e){
            Log.v("Prob",""+e.getLocalizedMessage());
        }

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
