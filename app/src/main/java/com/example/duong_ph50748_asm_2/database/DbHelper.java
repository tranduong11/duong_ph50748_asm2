package com.example.duong_ph50748_asm_2.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
    public static final String DataBase_Name="HEALTHYY.db";
    public static final int DataBase_Version=2;
    public DbHelper(@Nullable Context context) {
        super(context, DataBase_Name, null, DataBase_Version);
    }

    private static final String Users="create table USERS(" +
            "username text primary key," +
            "password text)";
    private static final String Bieton="create table BIETON(" +
            "idbieton integer primary key autoincrement," +
            "loibieton text)";

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(Users);
        sqLiteDatabase.execSQL(Bieton);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists USERS");
        sqLiteDatabase.execSQL("drop table if exists BIETON");
        onCreate(sqLiteDatabase);
    }
}
