package com.example.luisfelipe.trabalho2_dcc196;
import android.provider.BaseColumns;

/**
 * Created by luisfelipe on 21/11/17.
 */

public class ReservaContract {
    public static final String TEXT_TYPE = " TEXT";
    public static final String INT_TYPE = " INTEGER";
    public static final String SEP = ",";
    public static final String SQL_CREATE_TABLE = "CREATE TABLE " + Reserva.TABLE_NAME + " (" +
            Reserva._ID + INT_TYPE +" PRIMARY KEY AUTOINCREMENT" + SEP +
            Reserva.COLUMN_NAME_PARTICIPANTE + INT_TYPE+ SEP +
            Reserva.COLUMN_NAME_LIVRO+ INT_TYPE+ SEP +
            " FOREIGN KEY ("+Reserva.COLUMN_NAME_PARTICIPANTE+") REFERENCES "+ParticipanteContract.Participante.TABLE_NAME+"("+ParticipanteContract.Participante._ID+")"+SEP+
            " FOREIGN KEY ("+Reserva.COLUMN_NAME_LIVRO+") REFERENCES "+LivroContract.Livro.TABLE_NAME+"("+LivroContract.Livro._ID+"))";

    public static final String SQL_DROP_RESERVA = "DROP TABLE IF EXISTS " + Reserva.TABLE_NAME;
    public static final String SQL_CONSULTA_RESERVAS = "SELECT participante._id,"+ ParticipanteContract.Participante.COLUMN_NAME_NOME+" FROM "+Reserva.TABLE_NAME+
                                                        " INNER JOIN "+LivroContract.Livro.TABLE_NAME+
                                                            " livro ON ("+Reserva.COLUMN_NAME_LIVRO+"=livro."+LivroContract.Livro._ID+")"+
                                                        " INNER JOIN "+ParticipanteContract.Participante.TABLE_NAME+
                                                            " participante ON ("+Reserva.COLUMN_NAME_PARTICIPANTE+"= participante."+ParticipanteContract.Participante._ID+")";
    public ReservaContract() {
    }

    public static final class Reserva implements BaseColumns {
        public static final String TABLE_NAME = "reservas";
        public static final String COLUMN_NAME_PARTICIPANTE = "participante";
        public static final String COLUMN_NAME_LIVRO = "livro";

    }
}
