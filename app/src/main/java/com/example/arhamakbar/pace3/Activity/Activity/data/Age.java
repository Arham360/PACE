package com.example.arhamakbar.pace3.Activity.Activity.data;

/**
 * Created by arhamakbar on 8/3/17.
 */

public class Age {

    static final String TABLE_NAME = "AGE";
    static final String COLUMN_ID = "ID";
    static final String COLUMN_TEXT = "TEXT";
    static final String COLUMNS = "(" + COLUMN_ID + " INTEGER PRIMARY KEY,"
                                        + COLUMN_TEXT + " TEXT)";


    private int id;
    private String text;

    public Age(int id, String text) {
        this.id = id;
        this.text = text;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
