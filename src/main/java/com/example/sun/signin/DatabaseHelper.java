package com.example.sun.signin;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Owner on 2017/05/10.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    Context context;
    SQLiteDatabase sqLiteDatabase;
    public static String DBNAME = "misao";

    public DatabaseHelper(Context context) {
        super(context, DBNAME, null, 1);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("Create table Account(Name varchar(30), Birthday varchar(20), Phone varchar(20), Email varchar(100), Password varchar(20))");
        this.sqLiteDatabase = db;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        this.onCreate(db);
    }

//    public int signIn(String name, String password){
//        sqLiteDatabase = getWritableDatabase();
//        ContentValues cv = new ContentValues();
//
//    }

    public int insert(String name, String birthday, String phone, String email, String password) {
        sqLiteDatabase = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put("Name", name);
        cv.put("Birthday", birthday);
        cv.put("Phone", phone);
        cv.put("Email", email);
        cv.put("Password", password);

        sqLiteDatabase.insert("Account", null, cv);
        return 1;
    }

    public Cursor show(String name) {
        sqLiteDatabase = getReadableDatabase();
        Cursor c = sqLiteDatabase.rawQuery("Select * from Account where Name = '" + name + "'", null);
        return c;
    }

    public Cursor showall() {
        sqLiteDatabase = getReadableDatabase();
        Cursor c = sqLiteDatabase.rawQuery("Select * from Account", null);
        return c;
    }
}
