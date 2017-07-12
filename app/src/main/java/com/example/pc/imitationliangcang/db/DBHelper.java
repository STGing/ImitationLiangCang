package com.example.pc.imitationliangcang.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * 数据库
 */

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(Context context) {
        super(context, "goodsInfo.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //建表
        db.execSQL(DBWord.CREATETABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
