package com.example.duong_ph50748_asm_2.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.duong_ph50748_asm_2.database.DbHelper;
import com.example.duong_ph50748_asm_2.models.BietOn;
import com.example.duong_ph50748_asm_2.models.Users;

import java.util.ArrayList;
import java.util.List;

public class UsersDAO {
    private DbHelper dbHelper;
    private SQLiteDatabase database;

    public UsersDAO(Context context){
        dbHelper=new DbHelper(context);
        database=dbHelper.getWritableDatabase();
    }
    public boolean insertUsers(Users obj){
        ContentValues values=new ContentValues();
        values.put("username",obj.getUsername());
        values.put("password",obj.getPassword());
        long check= database.insert("USERS",null,values);
        return check!=-1;
    }
    //
    public List<Users> getAllDataUsers(){
        List<Users> list=new ArrayList<>();
        Cursor cursor=database.rawQuery("select *from USERS",null);
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {
                Users users=new Users();
                users.setUsername(cursor.getString(0));
                users.setPassword(cursor.getString(1));
                list.add(users);
            }while (cursor.moveToNext());
        }
        return list;
    }
    public boolean insertBietOn(BietOn obj){
        ContentValues values=new ContentValues();
        values.put("loibieton",obj.getLoiBietOn());
        long check=database.insert("BIETON",null,values);
        return check!=-1;
    }
    public List<BietOn> getAllDataBietOn(){
        List<BietOn> bietOnList=new ArrayList<>();
        Cursor cursor=database.rawQuery("select *from BIETON",null);
        if (cursor.getCount()>0){
            cursor.moveToFirst();
            do {
                BietOn bietOn=new BietOn();
                bietOn.setIdbieton(cursor.getInt(0));
                bietOn.setLoiBietOn(cursor.getString(1));
                bietOnList.add(bietOn);
            }
            while (cursor.moveToNext());
        }
        return bietOnList;
    }
    public boolean deleteDataBietOn(int id){
        int check=database.delete("BIETON","idbieton=?",new String[]{String.valueOf(id)});
        return check!=-1;
    }
}
