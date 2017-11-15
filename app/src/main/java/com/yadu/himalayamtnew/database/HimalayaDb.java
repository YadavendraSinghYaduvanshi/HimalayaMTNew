package com.yadu.himalayamtnew.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by yadavendras on 14-11-2017.
 */

public class HimalayaDb extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "HIMALAYA_MT_DATABASE1";
    public static final int DATABASE_VERSION = 2;
    private SQLiteDatabase db;

    public HimalayaDb(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void open() {
        try {
            db = this.getWritableDatabase();
        } catch (Exception e) {
        }
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
