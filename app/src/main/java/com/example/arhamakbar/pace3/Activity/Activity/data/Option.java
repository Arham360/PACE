package com.example.arhamakbar.pace3.Activity.Activity.data;

import java.util.Arrays;

/**
 * Created by arhamakbar on 8/3/17.
 */

public class Option {

    int id;
    String text;
    String caption;
    int age;
    int type;
    Integer parent;
    int[] cases;
    int[] options;

    public Option(int id, String text, int age, int type, Integer parent, int[] cases, int[] options,String caption) {
        this.id = id;
        this.text = text;
        this.age = age;
        this.type = type;
        this.parent = parent;
        this.cases = cases;
        this.options = options;
        this.caption =caption;
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

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getParent() {
        return parent;
    }

    public void setParent(int parent) {
        this.parent = parent;
    }

    public int[] getCases() {
        return cases;
    }

    public void setCases(int[] cases) {
        this.cases = cases;
    }

    public int[] getOptions() {
        return options;
    }

    public void setOptions(int[] options) {
        this.options = options;
    }

    @Override
    public String toString() {
        return "Option{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", age=" + age +
                ", type=" + type +
                ", parent=" + parent +
                ", cases=" + Arrays.toString(cases) +
                ", options=" + Arrays.toString(options) +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Option option = (Option) o;

        if (id != option.id) return false;
        if (age != option.age) return false;
        if (type != option.type) return false;
        if (parent != option.parent) return false;
        if (text != null ? !text.equals(option.text) : option.text != null) return false;
        if (!Arrays.equals(cases, option.cases)) return false;
        return Arrays.equals(options, option.options);

    }


}
