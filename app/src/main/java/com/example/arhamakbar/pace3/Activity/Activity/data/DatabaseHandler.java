package com.example.arhamakbar.pace3.Activity.Activity.data;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by arhamakbar on 8/9/17.
 */


public class DatabaseHandler extends SQLiteOpenHelper {

    static final int DATABASE_VERSION = 1;
    private final String TAG = "DB";
    static final String DATABASE_NAME = "PaceLocalData" ;

    SQLiteDatabase myDatabase;


    public DatabaseHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }
    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);}

    //initialize the database
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + Age.TABLE_NAME + Age.COLUMNS + ";");
        db.execSQL("CREATE TABLE " + Option.TABLE_NAME + Option.COLUMNS + ";");
        db.execSQL("CREATE TABLE " + Case.TABLE_NAME + Case.COLUMNS + ";");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void addAges(List<Age> ageList){
        myDatabase = getWritableDatabase();

        for (int i  = 0; i< ageList.size(); i++){
            ContentValues contentValues = new ContentValues();
            int id = ageList.get(i).getId();
            String text = ageList.get(i).getText();
            contentValues.put(Age.COLUMN_ID, id);
            contentValues.put(Age.COLUMN_TEXT,text);
            myDatabase.insert(Age.TABLE_NAME,null, contentValues);

        }
    }

    public void addCases(List<Case> caseList){
        myDatabase = getWritableDatabase();

        for (int i  = 0; i< caseList.size(); i++){
            ContentValues contentValues = new ContentValues();
            int id = caseList.get(i).getId();
            String text = caseList.get(i).getText();
            int age = caseList.get(i).getAge();
            String image = caseList.get(i).getImage();
            contentValues.put(Case.COLUMN_ID, id);
            contentValues.put(Case.COLUMN_TEXT,text);
            contentValues.put(Case.COLUMN_AGE,age);
            contentValues.put(Case.COLUMN_IMAGE,image);

            myDatabase.insertOrThrow(Case.TABLE_NAME,null, contentValues);

        }
    }


    public void addOptions(List<Option> optionList) {
        myDatabase = getWritableDatabase();


        int optionsNum = optionList.size();
        for (int d = 0; d < optionsNum; d++) {

            int a = optionList.get(d).getId();
            int age = optionList.get(d).getAge();
            int type = optionList.get(d).getType();
            int parent = optionList.get(d).getParent();
            String t = optionList.get(d).getText();
            String caption = optionList.get(d).getCaption();
            String options = "";
            int[] op = optionList.get(d).getOptions();
            for (int i = 0; i < op.length; i++) {
                options += op[i] + ",";
                //Log.v("OPTIONS","currently on " + options + t + a);

            }
            Log.v("OPTIONS", "success options");
            String cas = "";
            int[] ca = optionList.get(d).getCases();
            for (int i = 0; i < ca.length; i++) {
                cas += ca[i] + ",";
                //Log.v("OPTIONS","adding" + cas);
            }

            ContentValues contentValues = new ContentValues();
            contentValues.put(Option.COLUMN_ID, a);
            contentValues.put(Option.COLUMN_TEXT,t);
            contentValues.put(Option.COLUMN_TYPE, type);
            contentValues.put(Option.COLUMN_AGE,age);
            contentValues.put(Option.COLUMN_PARENT, parent);
            contentValues.put(Option.COLUMN_CAPTION,caption);
            contentValues.put(Option.COLUMN_OPTIONS, options);
            contentValues.put(Option.COLUMN_CASES, cas);
            Log.v("OPTIONS", "success cases");

            myDatabase.insert(Option.TABLE_NAME, null, contentValues);
        }
    }

    public List<Age> getAges (){
        myDatabase = getReadableDatabase();
        String selectQuery = "SELECT * FROM " + Age.TABLE_NAME;
        Cursor cursor = myDatabase.rawQuery(selectQuery,null);
        List<Age> retList = new ArrayList<>();
        if (cursor.moveToFirst()){
            do{
                Age age = new Age(cursor.getInt(cursor.getColumnIndexOrThrow(Age.COLUMN_ID)), cursor.getString(cursor.getColumnIndexOrThrow(Age.COLUMN_TEXT)));

                retList.add(age);

            }while(cursor.moveToNext());
        }
        cursor.close();
        myDatabase.close();
        return retList;
    }



    public ArrayList<Integer> getInitialOptionByAge(int age){
        myDatabase = getReadableDatabase();
        String selectQuery = "SELECT * FROM " + Option.TABLE_NAME + " WHERE " +Option.COLUMN_AGE + " = " + age + " AND " + Option.COLUMN_TYPE+ " = 1"  ;
        Cursor cursor = myDatabase.rawQuery(selectQuery,null);
        ArrayList<Integer> retlist = new ArrayList<Integer>();
        if (cursor.moveToFirst()) {
            do {

                retlist.add(cursor.getInt(cursor.getColumnIndexOrThrow(Option.COLUMN_ID)));


            }while (cursor.moveToNext());

        }
        cursor.close();
        myDatabase.close();
        return retlist;
    }

    public ArrayList<Integer> getOptionById(int id){
        myDatabase = getReadableDatabase();
        String selectQuery = "SELECT * FROM " + Option.TABLE_NAME + " WHERE "+ Option.COLUMN_ID + " = " + id ;
        Cursor cursor = myDatabase.rawQuery(selectQuery,null);
        ArrayList<Integer> retlist = new ArrayList<Integer>();
        if (cursor.moveToFirst()) {
            String[] arr = cursor.getString(cursor.getColumnIndexOrThrow(Option.COLUMN_OPTIONS)).split(",");
            if (!arr[0].equals("")) {
                for (int i = 0; i < arr.length; i++) {
                    retlist.add(Integer.parseInt(arr[i]));
                }
            }
        }
        cursor.close();
        myDatabase.close();
        return retlist;
    }


    public ArrayList<Integer> getCaseFromOption(int id){
        myDatabase = getReadableDatabase();
        String selectQuery = "SELECT * FROM " + Option.TABLE_NAME + " WHERE "+ Option.COLUMN_ID + " = " + id ;
        Cursor cursor = myDatabase.rawQuery(selectQuery,null);
        ArrayList<Integer> retlist = new ArrayList<Integer>();
        if (cursor.moveToFirst()) {
            String[] arr = cursor.getString(cursor.getColumnIndexOrThrow(Option.COLUMN_CASES)).split(",");
            if (!arr[0].equals("")) {
                for (int i = 0; i < arr.length; i++) {

                    retlist.add(Integer.parseInt(arr[i]));
                }
            }
        }
        cursor.close();
        myDatabase.close();
        return retlist;
    }

    public String getOptionTextById(int id){
        myDatabase = getReadableDatabase();
        String selectQuery = "SELECT * FROM " + Option.TABLE_NAME + " WHERE "+ Option.COLUMN_ID + " = " + id ;
        Cursor cursor = myDatabase.rawQuery(selectQuery,null);
        String ret = "";
        if (cursor.moveToFirst()) {
            ret = cursor.getString(cursor.getColumnIndexOrThrow(Option.COLUMN_TEXT));

        }
        cursor.close();
        myDatabase.close();
        return ret;
    }

    public String getCaptionTextById(int id){
        myDatabase = getReadableDatabase();
        String selectQuery = "SELECT * FROM " + Option.TABLE_NAME + " WHERE "+ Option.COLUMN_ID + " = " + id ;
        Cursor cursor = myDatabase.rawQuery(selectQuery,null);
        String ret = "";
        if (cursor.moveToFirst()) {
            ret = cursor.getString(cursor.getColumnIndexOrThrow(Option.COLUMN_CAPTION));
            if (ret.equals("")){
                ret = "What is the patient's primary symptom?";
            }
        }
        cursor.close();
        myDatabase.close();
        return ret;
    }

    public String getCaseText(int i){
        myDatabase = getReadableDatabase();
        String selectQuery = "SELECT * FROM " + Case.TABLE_NAME + " WHERE "+ Case.COLUMN_ID + " = " + i ;
        Cursor cursor = myDatabase.rawQuery(selectQuery,null);
        String ret = " ";
        if (cursor.moveToFirst()) {
            ret = cursor.getString(cursor.getColumnIndexOrThrow(Case.COLUMN_TEXT));
        }
        cursor.close();
        myDatabase.close();
        return ret;
    }

    public String getCaseImage(int i){
        myDatabase = getReadableDatabase();
        String selectQuery = "SELECT * FROM " + Case.TABLE_NAME + " WHERE "+ Case.COLUMN_ID + " = " + i ;
        Cursor cursor = myDatabase.rawQuery(selectQuery,null);
        String ret = " ";
        if (cursor.moveToFirst()) {
            ret = cursor.getString(cursor.getColumnIndexOrThrow(Case.COLUMN_IMAGE));
        }
        cursor.close();
        myDatabase.close();
        return ret;
    }


}
