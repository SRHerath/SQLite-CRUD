package com.example.testing.Sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;


import com.example.testing.RUI_Activity;

import java.util.ArrayList;

public class DBHandler extends SQLiteOpenHelper {
    private ArrayList<ItemModel> list = new ArrayList<>();
    private ContentValues contentValues;
    public DBHandler(Context context) {
        super(context,"DataBaseItem",null,1);
    }



    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("Create table ItemData(id VARCHAR primary key, code VARCHAR, des VARCHAR, quan TEXT)");
    }
    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {
        DB.execSQL("drop Table if exists ItemData");
    }
    public Boolean insertData(String id,String code,String des,String quan){
        SQLiteDatabase DBW = this.getWritableDatabase();
        contentValues = new ContentValues();
        contentValues.put("id",id);
        contentValues.put("code",code);
        contentValues.put("des",des);
        contentValues.put("quan",quan);
        long l = DBW.insert("ItemData",null,contentValues);
        if (l == -1){
            return false;
        }else {
            return true;
        }
    }
    public Boolean updateData(String id,String code,String des,String quan){
        SQLiteDatabase DBW = this.getWritableDatabase();
        contentValues = new ContentValues();
        contentValues.put("id",id);
        contentValues.put("code",code);
        contentValues.put("des",des);
        contentValues.put("quan",quan);
        Cursor cursor = DBW.rawQuery("Select * from ItemData where id = ?",new String[]{id});
        cursor.moveToFirst();

        if (cursor.getCount()>0){
            long l = DBW.update("ItemData",contentValues,"id=?",new String[]{id});
            if (l == -1) {
                return false;
            }else {
                return true;
            }
        }else {
            return false;
        }
    }
    public Boolean deleteData(String id){
        SQLiteDatabase DBW = this.getWritableDatabase();
        Cursor cursor = DBW.rawQuery("Select * from ItemData where id = ?",new String[]{id});
        if (cursor.getCount()>0){
            long l =DBW.delete("ItemData","id = ?",new String[]{id});
            if (l == -1){
                return false;
            }else {
                return true;
            }
        }else {
            return false;
        }
    }
    public ArrayList<ItemModel> readData(){
        String id,code,des,quan;
        SQLiteDatabase DBR = this.getReadableDatabase();
        Cursor cursor = DBR.rawQuery("Select * from ItemData",null);
        cursor.moveToFirst();
        if (cursor.moveToFirst()){
            do {
                id =cursor.getString(0);
                code = cursor.getString(1);
                des = cursor.getString(2);
                quan = cursor.getString(3);
                list.add(new ItemModel(id,code,des,quan));
            }while (cursor.moveToNext());
        }
        DBR.close();
        return list;
    }
}
