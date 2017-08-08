package com.example.arhamakbar.pace3.Activity.Activity;

import android.content.Intent;
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

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       // String final t;
//        FragmentManager fragmentManager = getSupportFragmentManager();
//        Fragment fragment = fragmentManager.findFragmentById(R.id.fragment_container);
//        Fragment fragment2 = fragmentManager.findFragmentById(R.id.fragment_container2);
//        if(fragment == null){
//            fragment = new BlankFragment();
//            fragment2 = new ListFragment();
//            fragmentManager.beginTransaction().add(R.id.fragment_container,fragment).commit();
//            fragmentManager.beginTransaction().add(R.id.fragment_container,fragment2).commit();



        final List<Option> optionsList = null;
        final List<Age> ageList = null;
        // public List<Type> typeList;
        final List<Case> caseList = null;

        //final String url = "https://appbrewerydev.uwm.edu/pace/api/v1/init";
        final String url ="https://www.google.com";

        Volley.newRequestQueue(this).add(new JsonObjectRequest(Request.Method.GET, url ,null, new Response.Listener<JSONObject>() {
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
        }));


        String title;
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
                intent.putExtra("id","1");
                startActivity(intent);
            }
        });
        b2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Activity2.class);
                intent.putExtra("title","3 months to 12 months");
                intent.putExtra("id","2");
                startActivity(intent);
            }
        });
        b3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Activity2.class);
                intent.putExtra("title","12 months to 3 years");
                intent.putExtra("id","3");
                startActivity(intent);
            }
        });
        b4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Activity2.class);
                intent.putExtra("title","3 years to 12 years");
                intent.putExtra("id","4");
                startActivity(intent);
            }
        });
        b5.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Activity2.class);
                intent.putExtra("title","12 years to 18 years male");
                intent.putExtra("id","5");
                startActivity(intent);
            }
        });
        b6.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Activity2.class);
                intent.putExtra("title","12 years to 18 years female");
                intent.putExtra("id","6");
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
