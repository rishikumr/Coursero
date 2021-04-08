package com.example.coursero.util;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class StringListConverter {

    @TypeConverter
    public static ArrayList<String> fromString(String value) {
        //Constants.logD("before "+ value);
        Type listType = new TypeToken<ArrayList<String>>() {
        }.getType();
        ArrayList<String> arr = new Gson().fromJson(value, listType);
        //Constants.logD("After "+ arr);
        return arr;
    }

    @TypeConverter
    public static String fromArrayList(ArrayList<String> list) {
        Gson gson = new Gson();
        String json = gson.toJson(list);
        return json;
    }
}