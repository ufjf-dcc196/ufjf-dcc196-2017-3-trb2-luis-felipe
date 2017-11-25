package com.example.luisfelipe.trabalho2_dcc196;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import java.util.Random;

/**
 * Created by luisfelipe on 24/11/17.
 */
public class LivroAdapter extends CursorAdapter {
    private EventoDbHelper evendoDbHelper;
    public LivroAdapter(Context context, Cursor c) {
        super(context, c,0);
        evendoDbHelper = new EventoDbHelper(context);
    }


    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        return LayoutInflater.from(context).inflate(R.layout.layout_lista,viewGroup,false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView textNome = (TextView)view.findViewById(R.id.nome);
        String nome = cursor.getString(cursor.getColumnIndexOrThrow(LivroContract.Livro.COLUMN_NAME_TITULO));
        textNome.setText(nome);
    }

    public void atualizar() {
        try {
            SQLiteDatabase db = evendoDbHelper.getReadableDatabase();
            String[] visao = {
                    LivroContract.Livro._ID,
                    LivroContract.Livro.COLUMN_NAME_TITULO,
                    LivroContract.Livro.COLUMN_NAME_EDITORA,
                    LivroContract.Livro.COLUMN_NAME_ANO,
            };
            Cursor c = db.query(LivroContract.Livro.TABLE_NAME, visao, null, null, null, null, null);
            this.changeCursor(c);
        } catch (Exception e) {
            Log.e("LIVRO", e.getLocalizedMessage());
            Log.e("LIVRO", e.getStackTrace().toString());
        }
    }
    public void inserirLivro(Livro l){
        try {
            SQLiteDatabase db = evendoDbHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put( LivroContract.Livro.COLUMN_NAME_TITULO, l.getTitulo());
            values.put(LivroContract.Livro.COLUMN_NAME_EDITORA, l.getEditora());
            values.put(LivroContract.Livro.COLUMN_NAME_ANO, l.getAno());
            long id = db.insert(LivroContract.Livro.TABLE_NAME, null, values);
            atualizar();
        } catch (Exception e) {
            Log.e("LIVRO_INSERSAO", e.getLocalizedMessage());
            Log.e("LIVRO_INSERSAO", e.getStackTrace().toString());
        }

    }
    public Livro getLivro(String id){
        Livro l= new Livro();
        try {
            SQLiteDatabase db = evendoDbHelper.getReadableDatabase();
            String[] visao = {
                    LivroContract.Livro.COLUMN_NAME_TITULO,
                    LivroContract.Livro.COLUMN_NAME_EDITORA,
                    LivroContract.Livro.COLUMN_NAME_ANO,
            };
            String selecao = LivroContract.Livro._ID+" = "+id;
            Cursor c = db.query(LivroContract.Livro.TABLE_NAME, visao, selecao, null, null, null, null);
            c.moveToFirst();
            l.setTitulo(c.getString(c.getColumnIndex(LivroContract.Livro.COLUMN_NAME_TITULO)));
            l.setEditora(c.getString(c.getColumnIndex(LivroContract.Livro.COLUMN_NAME_EDITORA)));
            l.setAno(c.getString(c.getColumnIndex(LivroContract.Livro.COLUMN_NAME_ANO)));

        } catch (Exception e) {
            Log.e("BUSCA_LIVRO", e.getLocalizedMessage());
            Log.e("BUSCA_LIVRO", e.getStackTrace().toString());
        }
        return l;
    }
    public String getId(int i){
        Cursor c = getCursor();
        c.moveToPosition(i);
        return c.getString(c.getColumnIndex(LivroContract.Livro._ID));
    }
}
