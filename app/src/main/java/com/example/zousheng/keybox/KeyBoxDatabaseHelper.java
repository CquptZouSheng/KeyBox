package com.example.zousheng.keybox;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Zou Sheng on 2016/2/17.
 */
public class KeyBoxDatabaseHelper extends SQLiteOpenHelper{
    public static final String CREATE_BOOK="create table KeyBox(" +
            "id integer primary key autoincrement," +
            "name text ," +
            "account text," +
            "password text," +
            "remark text," +
            "time text)";
    public KeyBoxDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_BOOK);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
