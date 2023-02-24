package com.example.orderup.persistance;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
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
        super(context, DATABASE_NAME, null, 2);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_NAME + "(" + COL_1 + " INTEGER PRIMARY KEY AUTOINCREMENT," + COL_2 + " TEXT," + COL_3 + " TEXT," + COL_4 + " TEXT," + COL_5 + " TEXT," + COL_6 + " TEXT," + COL_7 + " TEXT," + COL_8 + " TEXT," + COL_9 + " TEXT," + COL_10 + " FLOAT" + ");");
    //         sqLiteDatabase.execSQL("CREATE TABLE" + TABLE_NAME + "(" + COL_1 + " INTEGER PRIMARY KEY AUTOINCREMENT," + COL_2 + " TEXT" + ,EMAIL TEXT,PASSWORD TEXT,FIRSTNAME TEXT,LASTNAME TEXT,CREDITCARD TEXT,CSV TEXT,EXPIRY TEXT,ADDRESS TEXT, BALANCE FLOAT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public boolean insertData(String email, String password, String firstname, String lastname, String credit, String csv, String expiry, String address, float balance) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        if(null != email) {
            contentValues.put(COL_2, email);
        }

        if(null != password) {
            contentValues.put(COL_3, password);
        }

        if(null != firstname) {
            contentValues.put(COL_4, firstname);
        }

        if(null != lastname) {
            contentValues.put(COL_5, lastname);
        }

        if(null != credit) {
            contentValues.put(COL_6, credit);
        }

        if(null != csv) {
            contentValues.put(COL_7, csv);
        }

        if(null != expiry) {
            contentValues.put(COL_8, expiry);
        }

        if(null != address) {
            contentValues.put(COL_9, address);
        }

        if(balance >= 0.00F) {
            contentValues.put(COL_10, balance);
        }

        long result = sqLiteDatabase.insert(TABLE_NAME,null,contentValues);
        if(result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Cursor getAllData() {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor res = sqLiteDatabase.rawQuery("select * from " + TABLE_NAME, null);
        return res;
    }

    public boolean updateData(String id, String email, String password, String firstname, String lastname, String credit,
                              String csv, String expiry, String address, float balance) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,id);
        contentValues.put(COL_2,email);
        contentValues.put(COL_3,password);
        contentValues.put(COL_4,firstname);
        contentValues.put(COL_5,lastname);
        contentValues.put(COL_6,credit);
        contentValues.put(COL_7,csv);
        contentValues.put(COL_8,expiry);
        contentValues.put(COL_9,address);
        contentValues.put(COL_10,balance);
        sqLiteDatabase.update(TABLE_NAME, contentValues, "id = ?",new String[]{id});
        return true;

    }
}
