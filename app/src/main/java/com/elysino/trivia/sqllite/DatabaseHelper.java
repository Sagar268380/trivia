package com.elysino.trivia.sqllite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static  final  String dbname="HistoryDb";
    private static  final int version = 1;
    private SQLiteDatabase sqLiteDatabase;

    public DatabaseHelper(Context context) {
        super(context, dbname, null, version);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        this.sqLiteDatabase = sqLiteDatabase;
        String sql="CREATE TABLE HISTORY(_id INTEGER PRIMARY KEY AUTOINCREMENT,QUESTION1 TEXT,ANSWER1 TEXT,QUESTION2 TEXT,ANSWER2 TEXT,NAME TEXT,DATE TEXT)";
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
