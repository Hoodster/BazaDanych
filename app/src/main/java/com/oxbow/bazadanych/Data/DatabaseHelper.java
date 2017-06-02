package com.oxbow.bazadanych.Data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by kubap on 02.06.2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "elements";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_NAME = "Element";

    public static final String DATA_ID = "id";
    public static final String DATA_NAME = "name";

    private static DatabaseHelper instance;

    public static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "(" + DATA_ID + " INTEGER PRIMARY KEY, " + DATA_NAME + " TEXT)";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static synchronized DatabaseHelper getHelper(Context context) {
        if (instance == null)
            instance = new DatabaseHelper(context);
        return instance;
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
