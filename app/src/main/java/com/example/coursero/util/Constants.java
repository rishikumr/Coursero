package com.example.coursero.util;

import android.util.Log;

import com.example.coursero.BuildConfig;

public class Constants {
    public static final String MyPref = BuildConfig.APPLICATION_ID + ".SharedPref";
    public static final String COURSE_TABLE_NAME = "course_table";
    public static final String COURSE_DB_NAME = "course_database";
    public static final String TAG = "coursera";


    public static final String PRIMARY_KEY = "courseId";
    public static final int COURSE_COUNT = 10;
    public static final String DIFFERENTIATORS_SIGN = ":::";

    public static void logD(String log) {
        Log.d(TAG, log);

    }
}
