package com.example.arhamakbar.pace3.Activity.Activity.data;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by arhamakbar on 8/9/17.
 */

//TO DO SETUP LOCAL DATA
public class DatabaseHandler extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    
    public static final String DATABASE_NAME = "LocalData" ;
    
    
    public static final String TABLE_OPTION = "option" ;
    public static final String TABLE_AGE = "age" ;
    public static final String TABLE_CASE = "case" ;



    public DatabaseHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
