package com.example.arhamakbar.pace3.Activity.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
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
import com.example.arhamakbar.pace3.Activity.Activity.data.DatabaseHandler;
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
    DatabaseHandler databaseHandler;
    public static final String MY_PREFS_NAME = "MyPrefsFile";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        databaseHandler = new DatabaseHandler(this);


        SharedPreferences sharedPreferences = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        boolean first = sharedPreferences.getBoolean("first", true);
        if (first) {
            init();
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("first", false);
            editor.commit();
        }



        String title = "P.A.C.E ";
        ActionBar ab = getSupportActionBar();
        ab.setTitle(title);
        ab.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FF497FFD")));

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
                       try {
                           SQLwrite();
                       }catch (Exception e) {

                       }
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

        Volley.newRequestQueue(this).add(jsonObjectRequest);

    }

    protected void SQLwrite (){
        try{
            for (int i = 0; i<ageList.size();i++){

                Log.v("fun",ageList.get(i).getId() +" " + ageList.get(i).getText());

            }
            Log.v("SQL","starting database");

            databaseHandler.addAges(ageList);

            Log.v("OPTIONS","ADDING TO DATABASE");
            databaseHandler.addOptions(optionsList);
            Log.v("OPTIONS","ADDED TO DATABASE");
            databaseHandler.addCases(caseList);
            Log.v("Cases","ADDED TO DATABASE");

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
