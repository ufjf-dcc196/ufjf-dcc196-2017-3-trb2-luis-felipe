package com.example.luisfelipe.trabalho2_dcc196;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class AtividadeConsultaLivro extends AppCompatActivity {
    private EditText tituloLivro;
    private EditText editoraLivro;
    private EditText anoLivro;
    private ListView listaReservas;
    private  ReservaAdapter reservaAdapter;
    private LivroAdapter livroAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.atividade_consulta_livro);

        tituloLivro = (EditText)findViewById(R.id.titulo);
        editoraLivro = (EditText)findViewById(R.id.editora);
        anoLivro = (EditText)findViewById(R.id.ano);
        listaReservas = (ListView)findViewById(R.id.reservados);


        String idLivro = getIntent().getStringExtra("id");
        reservaAdapter= new ReservaAdapter(getBaseContext(),null);
        livroAdapter = new LivroAdapter(getBaseContext(),null);
        reservaAdapter.atualizar(idLivro);
        Livro l = livroAdapter.getLivro(idLivro);
        listaReservas.setAdapter(reservaAdapter);
        listaReservas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent novaAtividade= new Intent(AtividadeConsultaLivro.this,AtividadeConsultaParticipante.class);
                novaAtividade.putExtra("id",reservaAdapter.getId(i));
                startActivity(novaAtividade);
            }
        });
                tituloLivro.setText(l.getTitulo());
        editoraLivro.setText(l.getEditora());
        anoLivro.setText(l.getAno());


        tituloLivro.setEnabled(false);
        editoraLivro.setEnabled(false);
        anoLivro.setEnabled(false);
    }
}
