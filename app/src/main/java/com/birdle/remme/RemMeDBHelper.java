package com.birdle.remme;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by pranaygp on 14/08/15.
 */
public class RemMeDBHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "rems.db";

    private static final String CREATE_TABLE_SQL = "CREATE TABLE " +
            remsContract.remSchema.TABLE_NAME + " (" +
                remsContract.remSchema._ID + " INTEGER PRIMARY KEY, " +
                remsContract.remSchema.COLUMN_NAME_REM_DATA + " TEXT " +
            ");";

    private static final String DELETE_TABLE_SQL = "DROP TABLE IF EXISTS " + remsContract.remSchema.TABLE_NAME;

    public RemMeDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_SQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DELETE_TABLE_SQL);
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
