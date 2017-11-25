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
 * Created by luisfelipe on 21/11/17.
 */

public class ReservaAdapter extends CursorAdapter {
    private EventoDbHelper evendoDbHelper;
    public ReservaAdapter(Context context, Cursor c) {
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
        String nome = cursor.getString(cursor.getColumnIndexOrThrow(ParticipanteContract.Participante.COLUMN_NAME_NOME));
        textNome.setText(nome);
    }
    public void atualizar(String idLivro) {
        String rawQuery = ReservaContract.SQL_CONSULTA_RESERVAS;//+"WHERE"+LivroContract.Livro._ID +"="+idLivro;


        try {
            SQLiteDatabase db = evendoDbHelper.getReadableDatabase();
            String[] visao = {
                    ReservaContract.Reserva.COLUMN_NAME_LIVRO,
                    ReservaContract.Reserva.COLUMN_NAME_PARTICIPANTE,

            };
            Cursor c = db.rawQuery(rawQuery,null);

            this.changeCursor(c);
        } catch (Exception e) {
            Log.e("RESERVA", e.getLocalizedMessage());
            Log.e("RESERVA", e.getStackTrace().toString());
        }
    }
    public void inserirReserva(String idLivro , String idParticipante){
        try {
            Random rand= new Random();
            SQLiteDatabase db = evendoDbHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(ReservaContract.Reserva.COLUMN_NAME_PARTICIPANTE, idParticipante);
            values.put(ReservaContract.Reserva.COLUMN_NAME_LIVRO, idLivro);
            long id = db.insert(ReservaContract.Reserva.TABLE_NAME, null, values);
        } catch (Exception e) {
            Log.e("PARTICIPATE_INSERCAO", e.getLocalizedMessage());
            Log.e("PARTICIPATE_INSERCAO", e.getStackTrace().toString());
        }

    }
    public String getId(int i){
        Cursor c = getCursor();
        c.moveToPosition(i);
        return c.getString(c.getColumnIndex("_id"));
    }
}

