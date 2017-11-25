package com.example.luisfelipe.trabalho2_dcc196;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class EventoDbHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION =3;
    public static final String DATABASE_NAME = "Eventoo.db";

    public EventoDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(LivroContract.SQL_CREATE_TABLE);
        sqLiteDatabase.execSQL(ParticipanteContract.SQL_CREATE_TABLE);
        sqLiteDatabase.execSQL(ReservaContract.SQL_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(LivroContract.SQL_CREATE_TABLE);
        sqLiteDatabase.execSQL(ParticipanteContract.SQL_CREATE_TABLE);
        sqLiteDatabase.execSQL(ReservaContract.SQL_CREATE_TABLE);
        onCreate(sqLiteDatabase);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

}
