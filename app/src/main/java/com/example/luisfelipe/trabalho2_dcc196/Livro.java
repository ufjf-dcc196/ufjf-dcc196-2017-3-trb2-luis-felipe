package com.example.luisfelipe.trabalho2_dcc196;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by luisfelipe on 24/11/17.
 */
public class Livro implements Serializable {
    private String titulo;
    private String editora;
    private String ano;
    public Livro() {

    }
    public Livro(String titulo, String editora, String ano) {
        this.titulo = titulo;
        this.editora = editora;
        this.ano = ano;

    }

    public String getTitulo() {
        return titulo;
    }

    public String getEditora() {
        return editora;
    }

    public String getAno() {
        return ano;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

}