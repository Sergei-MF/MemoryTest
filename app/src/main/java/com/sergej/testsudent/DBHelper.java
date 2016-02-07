package com.sergej.testsudent;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by sergej on 02.02.16.
 */
public class DBHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABSE_NAME  = "statisticDB";
    public static final String TABLE_STATISTIC  = "statistic";

    public static final String KEY_ID  = "_ID";
    public static final String KEY_NUM  = "num";
    public static final String KEY_WORD  = "word";
    public static final String KEY_ALLSTAT   = "allstat";

    public DBHelper(Context context) {
        super(context,DATABSE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_STATISTIC + " (" + KEY_ID + "integer primary key,"
                + KEY_NUM + " integer," + KEY_WORD + " integer," + KEY_ALLSTAT + " integer" +")" );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVerion, int newVersion) {
        db.execSQL("drop table if exist " + TABLE_STATISTIC);
        onCreate(db);
    }
}
