package com.example.miagenda;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    /**
     *
     */

    private static final String DATABASE_NAME= "MiAgenda.db";
    private static final int DATABASE_VERSION=1;

    private static final String SQL_CREATE_ENTRIES = "CREATE TABLE Contactos (id INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT, datos TEXT)";

    /**
     *
     * @param context
     */
    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    /**
     *
     * @param db The database.
     * @param oldVersion The old database version.
     * @param newVersion The new database version.
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
