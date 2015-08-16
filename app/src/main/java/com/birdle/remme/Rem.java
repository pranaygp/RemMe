package com.birdle.remme;

import android.app.Application;
import android.content.ContentValues;
import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by pranaygp on 14/08/15.
 */
public class Rem {
    private long id;
    private String data;
    private static RemMeDBHelper mDBHelper;

    public Rem(Context context){
        mDBHelper = new RemMeDBHelper(context);
    }
    public static Rem init(String in, Context context){
        Rem record = new Rem(context);
        record.data = parseInput(in, context);
        return record;
    }
    public Rem delete(){
        SQLiteDatabase db = mDBHelper.getWritableDatabase();
        String[] idArg = {String.valueOf(id)};
        db.delete(remsContract.remSchema.TABLE_NAME, remsContract.remSchema._ID + " = ?", idArg);
        return this;
    }
    public Rem saveToDB(){
        SQLiteDatabase db = mDBHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(remsContract.remSchema.COLUMN_NAME_REM_DATA, data);
        id = db.insert(remsContract.remSchema.TABLE_NAME, null, values);
        return this;
    }
    public String getData(){
        return data;
    }

    private static Rem cursorToRem(Cursor c, Context context){
        Rem rem = new Rem(context);
        rem.id = c.getLong(c.getColumnIndexOrThrow(remsContract.remSchema._ID));
        rem.data = c.getString(c.getColumnIndexOrThrow(remsContract.remSchema.COLUMN_NAME_REM_DATA));
        return rem;
    }


    public static Rem find (long id, Context context){
        SQLiteDatabase db = mDBHelper.getReadableDatabase();

        String TestColumns = remsContract.remSchema._ID + " = ?";
        String[] TestColumnsArgs = {
                String.valueOf(id)
        };

        Cursor c = db.query(remsContract.remSchema.TABLE_NAME, remsContract.remSchema.COLUMN_NAMES, TestColumns, TestColumnsArgs, null, null, null);
        c.moveToFirst();
        return cursorToRem(c, context);
    }
    public static Cursor all(Context context){
        //TODO: return Array of all rems from db
        SQLiteDatabase db = mDBHelper.getReadableDatabase();
        Cursor c = db.query(remsContract.remSchema.TABLE_NAME, remsContract.remSchema.COLUMN_NAMES, null, null, null, null, null);
        Rem[] results = {};
        return c;
    }
    public static ArrayList<Rem> all(Date d){
        //TODO: return list of all rems from given day
        return null;
    }
    public static String parseInput(String in, Context context){
        Resources res = context.getResources();
        String result = "";
        for (int i = 0; i <in.length(); i+=4){
            int parsedID = Integer.parseInt(in.substring(i, (i+4)), 2);
            result += (res.getStringArray(R.array.words)[parsedID] + " ");
        }
        return result;
    }
}
