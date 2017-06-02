package com.oxbow.bazadanych.Data;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by kubap on 02.06.2017.
 */

public class ElementDBDAO {
    protected SQLiteDatabase db;
    private DatabaseHelper dbHelper;
    private Context mContext;

    public ElementDBDAO(Context context) {
        this.mContext = context;
        dbHelper = DatabaseHelper.getHelper(mContext);
        open();
    }

    public void open() throws SQLException {
        if (dbHelper == null)
            dbHelper = DatabaseHelper.getHelper(mContext);
        db = dbHelper.getWritableDatabase();
    }
}
