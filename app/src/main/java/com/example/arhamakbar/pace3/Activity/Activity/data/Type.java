package com.example.arhamakbar.pace3.Activity.Activity.data;

/**
 * Created by arhamakbar on 8/3/17.
 */

public class Type {

    int id;
    String text;

    public Type(int id, String text) {
        this.id = id;
        this.text = text;
    }

    @Override
    public String toString() {
        return "Type{" +
                "id=" + id +
                ", text='" + text + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Type type = (Type) o;

        if (id != type.id) return false;
        return text != null ? text.equals(type.text) : type.text == null;

    }


}
