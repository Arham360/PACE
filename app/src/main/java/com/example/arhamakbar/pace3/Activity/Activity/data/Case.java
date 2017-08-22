package com.example.arhamakbar.pace3.Activity.Activity.data;

/**
 * Created by arhamakbar on 8/3/17.
 */

public class Case {

    static final String TABLE_NAME = "CAS";
    static final String COLUMN_ID = "ID";
    static final String COLUMN_TEXT = "TEXT";
    static final String COLUMN_DESC = "DESC";
    static final String COLUMN_AGE = "AGE";
    static final String COLUMN_IMAGE = "IMAGE";
    static final String COLUMNS = "(" + COLUMN_ID + " INTEGER PRIMARY KEY,"
                                        + COLUMN_TEXT + " TEXT,"
                                        + COLUMN_DESC + " TEXT,"
                                        + COLUMN_AGE + " INTEGER,"
                                        + COLUMN_IMAGE + " TEXT)";


    private int id;
    private String text;
    private String desc;
    private int age;
    private String image;

    public Case(int id, String text,int age, String image) {
        this.id = id;
        this.text = text;
        this.age = age;
        this.image = image;
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

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Case{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", desc='" + desc + '\'' +
                ", image='" + image + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Case)) return false;

        Case aCase = (Case) o;

        if (id != aCase.id) return false;
        if (text != null ? !text.equals(aCase.text) : aCase.text != null) return false;
        if (desc != null ? !desc.equals(aCase.desc) : aCase.desc != null) return false;
        return image != null ? image.equals(aCase.image) : aCase.image == null;

    }



}
