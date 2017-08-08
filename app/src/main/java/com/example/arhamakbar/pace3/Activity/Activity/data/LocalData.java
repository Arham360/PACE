package com.example.arhamakbar.pace3.Activity.Activity.data;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by arhamakbar on 8/3/17.
 */

public class LocalData {

    public List<Option> optionsList;
    public List<Age> ageList;
   // public List<Type> typeList;
    public List<Case> caseList;



    LocalData(){

        //{}
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

                    for (int a = 0;a<ages.length();a++){
                        JSONObject ag = ages.getJSONObject(a);
                        int id = ag.getInt("id");
                        String text = ag.getString("text");
                        Age age = new Age(id,text);
                        ageList.add(age);
                    }

                    for (int b = 0; b<cases.length(); b++){

                        JSONObject ca = ages.getJSONObject(b);
                        int id = ca.getInt("id");
                        String text = ca.getString("text");
                        String description = ca.getString("description");
                        int age = ca.getInt("ages");
                        JSONArray img = ca.getJSONArray("images");
                        String image = img.getString(0);
                        Case c = new Case(id,text,description,age,image);
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



                } catch (JSONException e) {

                }

            }


        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.v("FUN","fail");
            }
        });


    }

}
