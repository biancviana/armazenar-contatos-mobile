package com.example.armazenar_contatos.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "contatos_db";
    private static final int DATABASE_VERSION = 1;
    public static final String TABLE_CONTATOS_NAME = "contatos";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "nome";

    private static final String CREATE_TABLE_CONTATOS =
            "CREATE TABLE " + TABLE_CONTATOS_NAME + "(" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_NAME + " TEXT)";

    public DbHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_CONTATOS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {


    }
}
