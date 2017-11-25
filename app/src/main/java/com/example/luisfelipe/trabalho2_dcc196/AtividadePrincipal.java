package com.example.luisfelipe.trabalho2_dcc196;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class AtividadePrincipal extends AppCompatActivity {

    private SimpleDateFormat formatoData;
    private Date data;
    private Calendar calendario;

    private ArrayList<Participante> participantes = new ArrayList();
    public  static ArrayList<Livro> livros = new ArrayList();
    private ListView listaParticipantes;
    private ListView listaLivros;
    private Button cadastroParticipante;
    private Button cadastroLivro;
    private Button cadastroReserva;
    private EventoDbHelper evendoDbHelper;
    ParticipanteAdapter participanteAdapter;
    LivroAdapter livroAdapter;

    public static final int CADASTRA_PARTICIPANTE = 1;
    public static final int CADASTRA_LIVRO = 2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.atividade_principal);

        formatoData = new SimpleDateFormat("dd-MM-yyyy-HH:mm:ss");


        listaParticipantes=(ListView) findViewById(R.id.listaParticipantes);
        listaLivros=(ListView) findViewById(R.id.listaLivros);
        cadastroParticipante=(Button) findViewById(R.id.cadastroParticipante);
        cadastroLivro = (Button) findViewById(R.id.cadastrarNovoLivro);
        cadastroReserva = (Button) findViewById(R.id.cadastrarReserva);
        Ouvinte ouvinte = new Ouvinte();
        cadastroParticipante.setOnClickListener(ouvinte);
        cadastroLivro.setOnClickListener(ouvinte);
        cadastroReserva.setOnClickListener(ouvinte);

        evendoDbHelper = new EventoDbHelper(getApplicationContext());
        livroAdapter= new LivroAdapter(getBaseContext(),null);
        participanteAdapter = new ParticipanteAdapter(getBaseContext(),null);
        livroAdapter.atualizar();
        participanteAdapter.atualizar();

        listaLivros.setAdapter(livroAdapter);
        listaParticipantes.setAdapter(participanteAdapter);


        listaParticipantes.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                String data = getData() ;

                if(participanteAdapter.getParticipante(i).getDataEntrada().equals("")){
                    participanteAdapter.clickLongo(i,ParticipanteContract.Participante.COLUMN_NAME_ENTRADA,data);
                } else if(participanteAdapter.getParticipante(i).getDataSaida().equals("")){
                    participanteAdapter.clickLongo(i,ParticipanteContract.Participante.COLUMN_NAME_SAIDA,data);
                } else{
                    participanteAdapter.removeDatas(i);
                }
                return  true;

            }
        });
        listaParticipantes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent novaAtividade= new Intent(AtividadePrincipal.this,AtividadeConsultaParticipante.class);
                novaAtividade.putExtra("id",participanteAdapter.getId(i));
                startActivity(novaAtividade);
            }
        });

        listaLivros.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent novaAtividade= new Intent(AtividadePrincipal.this,AtividadeConsultaLivro.class);
                novaAtividade.putExtra("id",livroAdapter.getId(i));
                startActivity(novaAtividade);
            }
        });

    }


    private String getData(){
        data = new Date();
        calendario = Calendar.getInstance();
        calendario.setTime(data);
        Date data_atual = calendario.getTime();
        String dataCompleta = formatoData.format(data_atual);
        return  dataCompleta;
    }
    private class Ouvinte implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            Intent novaAtiviadade;
            switch (view.getId()){
                case R.id.cadastroParticipante:
                    novaAtiviadade = new Intent(AtividadePrincipal.this,AtividadeCadastroParticipante.class);
                    startActivityForResult(novaAtiviadade,CADASTRA_PARTICIPANTE);
                    break;
                case R.id.cadastrarNovoLivro:
                    novaAtiviadade = new Intent(AtividadePrincipal.this,AtividadeCadastroLivro.class);
                    startActivityForResult(novaAtiviadade,CADASTRA_LIVRO);
                    break;
                case R.id.cadastrarReserva:
                    novaAtiviadade = new Intent(AtividadePrincipal.this,AtividadeCadastroReserva.class);
                    startActivity(novaAtiviadade);
                    break;
            }

        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==CADASTRA_PARTICIPANTE&&resultCode==RESULT_OK){
            participanteAdapter.atualizar();
        }

        if(requestCode==CADASTRA_LIVRO&&resultCode==RESULT_OK){
           livroAdapter.atualizar();
        }
    }
}
