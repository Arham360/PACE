package com.example.arhamakbar.pace3.Activity.Activity.data;

/**
 * Created by arhamakbar on 8/3/17.
 */

public class Age {

    int id;
    String text;


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
