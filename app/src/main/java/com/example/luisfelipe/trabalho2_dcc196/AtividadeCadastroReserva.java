package com.example.luisfelipe.trabalho2_dcc196;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class AtividadeCadastroReserva extends AppCompatActivity {
    private ArrayList<Participante> participantes;
    private ArrayList<Livro> livros = new ArrayList();
    private Spinner spLivros;
    private Spinner spParticipantes;
    private Button adicionarReserva;
    private TextView aviso;
    private Button voltar;
    private LivroAdapter livroAdapter;
    private ParticipanteAdapter participanteAdapter;
    private ReservaAdapter reservaAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.atividade_cadastro_reserva);
        spParticipantes = (Spinner) findViewById(R.id.spParticipantes);
        spLivros = (Spinner) findViewById(R.id.spLivros);
        adicionarReserva = (Button) findViewById(R.id.adicionarReserva);
        voltar = (Button) findViewById(R.id.voltar);
        aviso = (TextView) findViewById(R.id.aviso);
        livroAdapter = new LivroAdapter(getBaseContext(),null);
        participanteAdapter = new ParticipanteAdapter(getBaseContext(),null);
        reservaAdapter = new ReservaAdapter(getBaseContext(),null);
        livroAdapter.atualizar();
        participanteAdapter.atualizar();
        spLivros.setAdapter(livroAdapter);
        spParticipantes.setAdapter(participanteAdapter);


        adicionarReserva.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reservaAdapter.inserirReserva(livroAdapter.getId(spLivros.getSelectedItemPosition()),participanteAdapter.getId(spParticipantes.getSelectedItemPosition()));
                Toast.makeText(getApplicationContext(),"Reserva adicionada com sucesso", Toast.LENGTH_SHORT).show();
            }
        });
        voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

   /* void carregaAdaptes(){
        ArrayList<String> itensLivros = new ArrayList();
        for(Livro l : livros){
            itensLivros.add(l.getTitulo());
        }
        ArrayAdapter<String> ad = new ArrayAdapter(this, android.R.layout.simple_spinner_item, itensLivros);
        spLivros.setAdapter(ad);

        ArrayList<String> itensParticipantes = new ArrayList();
        for(Participante p: participantes){
            if(p.getDataEntrada()!=null&&p.getDataSaida()==null)
                itensParticipantes.add(p.getNome());
        }
        ArrayAdapter<String> ad2 = new ArrayAdapter(this, android.R.layout.simple_spinner_item, itensParticipantes);
        spParticipantes.setAdapter(ad2);
    }*/
}
