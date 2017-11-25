package com.example.luisfelipe.trabalho2_dcc196;

/**
 * Created by luisfelipe on 24/11/17.
 */

import java.io.Serializable;

/**
 * Created by luisfelipe on 18/10/17.
 */

public class Participante implements Serializable {
    private String nome;
    private String email;
    private String dataEntrada;
    private String dataSaida;

    public Participante() {

    }
    public Participante(String nome, String email) {
        this.nome = nome;
        this.email = email;
        dataEntrada="";
        dataSaida = "";
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDataEntrada() {
        return dataEntrada;
    }

    public String getDataSaida() {
        return dataSaida;
    }

    public void setDataEntrada(String dataEntrada) {
        this.dataEntrada = dataEntrada;
    }

    public void setDataSaida(String dataSaida) {
        this.dataSaida = dataSaida;
    }
}