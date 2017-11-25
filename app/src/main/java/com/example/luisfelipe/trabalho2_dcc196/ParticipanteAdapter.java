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
import android.widget.Toast;

import java.util.Random;
import java.util.regex.Pattern;

/**
 * Created by luisfelipe on 21/11/17.
 */

public class ParticipanteAdapter extends CursorAdapter {
    private EventoDbHelper evendoDbHelper;
    public ParticipanteAdapter(Context context, Cursor c) {
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

    public void atualizar() {
        try {
            SQLiteDatabase db = evendoDbHelper.getReadableDatabase();
            String[] visao = {
                    ParticipanteContract.Participante._ID,
                    ParticipanteContract.Participante.COLUMN_NAME_NOME,
                    ParticipanteContract.Participante.COLUMN_NAME_EMAIL,
                    ParticipanteContract.Participante.COLUMN_NAME_ENTRADA,
                    ParticipanteContract.Participante.COLUMN_NAME_SAIDA,
            };
            Cursor c = db.query(ParticipanteContract.Participante.TABLE_NAME, visao, null, null, null, null, null);
            this.changeCursor(c);
        } catch (Exception e) {
            Log.e("PARTICIPANTE", e.getLocalizedMessage());
            Log.e("PARTICIPANTE", e.getStackTrace().toString());
        }
    }
    public void inserirParticipante(Participante p){
        try {
            Random rand= new Random();
            SQLiteDatabase db = evendoDbHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put( ParticipanteContract.Participante.COLUMN_NAME_NOME, p.getNome());
            values.put(ParticipanteContract.Participante.COLUMN_NAME_EMAIL, p.getEmail());
            values.put(ParticipanteContract.Participante.COLUMN_NAME_ENTRADA, p.getDataEntrada());
            values.put(ParticipanteContract.Participante.COLUMN_NAME_SAIDA,p.getDataSaida());

            long id = db.insert(ParticipanteContract.Participante.TABLE_NAME, null, values);
            atualizar();
        } catch (Exception e) {
            Log.e("PARTICIPATE_INSERCAO", e.getLocalizedMessage());
            Log.e("PARTICIPATE_INSERCAO", e.getStackTrace().toString());
        }

    }
    public void clickLongo(int i,String campo,String data){
        Cursor c = getCursor();
        c.moveToPosition(i);
        String id = c.getString(c.getColumnIndex(ParticipanteContract.Participante._ID));
        ContentValues newData = new ContentValues();
        newData.put(campo,data);
        try {
            SQLiteDatabase db = evendoDbHelper.getWritableDatabase();
            db.update(ParticipanteContract.Participante.TABLE_NAME, newData, ParticipanteContract.Participante._ID+"="+id, null);
            atualizar();
        } catch (Exception e) {
            Log.e("PART_ATUALIZACAO_DATA", e.getLocalizedMessage());
            Log.e("PART_ATUALIZACAO_DATA", e.getStackTrace().toString());
        }
    }
    public void removeDatas(int i){
        Cursor c = getCursor();
        c.moveToPosition(i);
        String id = c.getString(c.getColumnIndex(ParticipanteContract.Participante._ID));
        ContentValues newData = new ContentValues();
        newData.put(ParticipanteContract.Participante.COLUMN_NAME_ENTRADA,"");
        newData.put(ParticipanteContract.Participante.COLUMN_NAME_SAIDA,"");
        try {
            SQLiteDatabase db = evendoDbHelper.getWritableDatabase();
            db.update(ParticipanteContract.Participante.TABLE_NAME, newData, "_id="+id, null);
            atualizar();
        } catch (Exception e) {
            Log.e("PART_RETIRA_DATA", e.getLocalizedMessage());
            Log.e("PART_RETIRA_DATA", e.getStackTrace().toString());
        }
    }
    public Participante getParticipante(String id){
        Participante p= new Participante();
        try {
            SQLiteDatabase db = evendoDbHelper.getReadableDatabase();
            String[] visao = {
                    ParticipanteContract.Participante.COLUMN_NAME_NOME,
                    ParticipanteContract.Participante.COLUMN_NAME_EMAIL,
                    ParticipanteContract.Participante.COLUMN_NAME_ENTRADA,
                    ParticipanteContract.Participante.COLUMN_NAME_SAIDA
            };
            String selecao = ParticipanteContract.Participante._ID+"= "+id;
            Cursor c = db.query(ParticipanteContract.Participante.TABLE_NAME, visao, selecao, null, null, null, null);
            c.moveToFirst();
            p.setNome(c.getString(c.getColumnIndex(ParticipanteContract.Participante.COLUMN_NAME_NOME)));
            p.setEmail(c.getString(c.getColumnIndex(ParticipanteContract.Participante.COLUMN_NAME_EMAIL)));
            p.setDataEntrada(c.getString(c.getColumnIndex(ParticipanteContract.Participante.COLUMN_NAME_ENTRADA)));
            p.setDataSaida(c.getString(c.getColumnIndex(ParticipanteContract.Participante.COLUMN_NAME_SAIDA)));

        } catch (Exception e) {
            Log.e("BUSCA_PARTICIPANTE", e.getLocalizedMessage());
            Log.e("BUSCA_PARTICIPANTE", e.getStackTrace().toString());
        }
        return p;
    }
    public String getId(int i){
        Cursor c = getCursor();
        c.moveToPosition(i);
        return c.getString(c.getColumnIndex(ParticipanteContract.Participante._ID));
    }
    public Participante getParticipante(int i){
        Participante p= new Participante();
        Cursor c = getCursor();
        c.moveToPosition(i);
        p.setNome(c.getString(c.getColumnIndex(ParticipanteContract.Participante.COLUMN_NAME_NOME)));
        p.setEmail(c.getString(c.getColumnIndex(ParticipanteContract.Participante.COLUMN_NAME_EMAIL)));
        p.setDataEntrada(c.getString(c.getColumnIndex(ParticipanteContract.Participante.COLUMN_NAME_ENTRADA)));
        p.setDataSaida(c.getString(c.getColumnIndex(ParticipanteContract.Participante.COLUMN_NAME_SAIDA)));
        return p;
    }
}
