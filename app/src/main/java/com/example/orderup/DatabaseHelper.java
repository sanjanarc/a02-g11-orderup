package com.example.orderup;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "user.db";
    public static final String TABLE_NAME = "usertable";

    public static final String COL_1 = "ID";
    public static final String COL_2 = "EMAIL";
    public static final String COL_3 = "PASSWORD";
    public static final String COL_4 = "FIRSTNAME";
    public static final String COL_5 = "LASTNAME";
    public static final String COL_6 = "CREDITCARD";
    public static final String COL_7 = "CSV";
    public static final String COL_8 = "EXPIRY";
    public static final String COL_9 = "ADDRESS";
    public static final String COL_10 = "BALANCE";



    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_NAME + "(" + COL_1 + " INTEGER PRIMARY KEY AUTOINCREMENT," + COL_2 + " TEXT," + COL_3 + " TEXT," + COL_4 + " TEXT," + COL_5 + " TEXT," + COL_6 + " TEXT," + COL_7 + " TEXT" + COL_8 + " TEXT," + COL_9 + " TEXT," + COL_10 + " FLOAT" + ")");
    //         sqLiteDatabase.execSQL("CREATE TABLE" + TABLE_NAME + "(" + COL_1 + " INTEGER PRIMARY KEY AUTOINCREMENT," + COL_2 + " TEXT" + ,EMAIL TEXT,PASSWORD TEXT,FIRSTNAME TEXT,LASTNAME TEXT,CREDITCARD TEXT,CSV TEXT,EXPIRY TEXT,ADDRESS TEXT, BALANCE FLOAT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
