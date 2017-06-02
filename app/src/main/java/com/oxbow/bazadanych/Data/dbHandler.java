package com.oxbow.bazadanych.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.icu.util.RangeValueIterator;

import java.util.ArrayList;

import static com.oxbow.bazadanych.Data.DatabaseHelper.DATA_ID;
import static com.oxbow.bazadanych.Data.DatabaseHelper.DATA_NAME;
import static com.oxbow.bazadanych.Data.DatabaseHelper.TABLE_NAME;

/**
 * Created by kubap on 02.06.2017.
 */

public class dbHandler extends ElementDBDAO {
    private static final String WHERE_ID_EQUALS = DATA_ID + " =?";

    public dbHandler(Context context) {
        super(context);
    }

    public long addElement(SampleData data) {
        ContentValues values = new ContentValues();
        values.put(DATA_NAME, data.getName());

        return db.insert(TABLE_NAME, null, values);
    }

    public ArrayList<SampleData> getDatas() {
        ArrayList<SampleData> datas = new ArrayList<>();

        Cursor cursor = db.query(TABLE_NAME, new String[]{DATA_ID, DATA_NAME}, null, null, null, null, null);

        while (cursor.moveToNext()) {

            SampleData data = new SampleData();
            data.setId(cursor.getInt(0));
            data.setName(cursor.getString(1));
            datas.add(data);
        }
        return datas;
    }

    public int delete(SampleData data) {
        return db.delete(DatabaseHelper.TABLE_NAME, WHERE_ID_EQUALS, new String[] { data.getId() + "" });
    }
}
