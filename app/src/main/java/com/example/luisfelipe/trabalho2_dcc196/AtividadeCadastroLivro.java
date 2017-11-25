package com.example.luisfelipe.trabalho2_dcc196;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class AtividadeCadastroLivro extends AppCompatActivity {
    private EditText tituloLivro;
    private EditText editoraLivro;
    private EditText anoLivro;
    private Button adicionarNovoLivro;
    private Button cancelar;
    private Button concluir;
    private ListView listaRecemCadastrados;
    LivroAdapter livroAdapter;
    private ArrayList<Livro> recemCadastrados = new ArrayList();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.atividade_cadastro_livro);
        tituloLivro = (EditText)findViewById(R.id.titulo);
        editoraLivro = (EditText)findViewById(R.id.editora);
        anoLivro = (EditText)findViewById(R.id.ano);
        adicionarNovoLivro = (Button)findViewById(R.id.adicionarLivro);
        cancelar = (Button)findViewById(R.id.cancelarCadastro);
        concluir = (Button) findViewById(R.id.concluirCadastro);
        livroAdapter = new LivroAdapter(getBaseContext(),null);


        listaRecemCadastrados = (ListView) findViewById(R.id.listaRecemCadastrados);
        listaRecemCadastrados.setAdapter(livroAdapter);

        adicionarNovoLivro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Livro l = new Livro(tituloLivro.getText().toString(),editoraLivro.getText().toString(),anoLivro.getText().toString());
                tituloLivro.setText("");
                editoraLivro.setText("");
                anoLivro.setText("");
                livroAdapter.inserirLivro(l);
            }
        });

        cancelar.setOnClickListener(new View.OnClickListener() {
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
