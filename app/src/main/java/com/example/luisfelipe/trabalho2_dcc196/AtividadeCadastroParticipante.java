package com.example.luisfelipe.trabalho2_dcc196;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class AtividadeCadastroParticipante extends AppCompatActivity {
    private EditText nomeParticipante;
    private EditText emailParticipante;
    private Button adicionarNovoParticipante;
    private Button voltar;
    private Button concluir;
    private ListView listaRecemCadastrados;
    private ArrayList<Participante> recemCadastrados = new ArrayList();
    private ParticipanteAdapter participanteAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.atividade_cadastro_participante);
        nomeParticipante = (EditText)findViewById(R.id.nomeCadastro);
        emailParticipante = (EditText)findViewById(R.id.emailCadastro);
        adicionarNovoParticipante = (Button)findViewById(R.id.adicionarParticipante);
        voltar = (Button)findViewById(R.id.cancelarCadastro);
        concluir = (Button) findViewById(R.id.concluirCadastro);
        listaRecemCadastrados = (ListView) findViewById(R.id.listaRecemCadastrados);
        participanteAdapter = new ParticipanteAdapter(getBaseContext(),null);
        listaRecemCadastrados.setAdapter(participanteAdapter);

        adicionarNovoParticipante.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Participante p = new Participante(nomeParticipante.getText().toString(),emailParticipante.getText().toString());
                nomeParticipante.setText("");
                emailParticipante.setText("");
                participanteAdapter.inserirParticipante(p);
            }
        });

        voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(RESULT_CANCELED);
                finish();
            }
        });

        concluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(RESULT_OK);
                finish();
            }
        });

    }

}
