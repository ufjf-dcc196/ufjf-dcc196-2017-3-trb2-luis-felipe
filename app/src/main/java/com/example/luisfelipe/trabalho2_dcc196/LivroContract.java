package com.example.luisfelipe.trabalho2_dcc196;

import android.provider.BaseColumns;

/**
 * Created by luisfelipe on 24/11/17.
 */


public class LivroContract {
    public static final String TEXT_TYPE = " TEXT";
    public static final String INT_TYPE = " INTEGER";
    public static final String SEP = ",";
    public static final String SQL_CREATE_TABLE = "CREATE TABLE " + Livro.TABLE_NAME + " (" +
            Livro._ID + INT_TYPE +" PRIMARY KEY AUTOINCREMENT" + SEP +
            Livro.COLUMN_NAME_TITULO+TEXT_TYPE +  SEP+
            Livro.COLUMN_NAME_EDITORA + TEXT_TYPE + SEP +
            Livro.COLUMN_NAME_ANO + TEXT_TYPE +")";

    public static final String SQL_DROP_LIVRO= "DROP TABLE IF EXISTS " + Livro.TABLE_NAME;

    public LivroContract() {
    }

    public static final class Livro implements BaseColumns {
        public static final String TABLE_NAME = "livros";
        public static final String COLUMN_NAME_TITULO = "titulo";
        public static final String COLUMN_NAME_EDITORA = "editora";
        public static final String COLUMN_NAME_ANO = "ano";

    }
}
