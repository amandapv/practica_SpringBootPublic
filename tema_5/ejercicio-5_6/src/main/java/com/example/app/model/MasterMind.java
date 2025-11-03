package com.example.app.model;

import java.util.ArrayList;

public class MasterMind {
    private String numeroSecreto;
    private ArrayList<Intento> listaIntentos;
    private EstadoJuego estadoJuego;
    public int TAM_NUMERO;
    public int MAX_INTENTOS;

    public MasterMind() {
        listaIntentos = new ArrayList<>();
    }

    public String getNumeroSecreto() {
        return numeroSecreto;
    }

    public void setNumeroSecreto(String numeroSecreto) {
        this.numeroSecreto = numeroSecreto;
    }

    public ArrayList<Intento> getListaIntentos() {
        return listaIntentos;
    }

    public void setListaIntentos(ArrayList<Intento> listaIntentos) {
        this.listaIntentos = listaIntentos;
    }

    public EstadoJuego getEstadoJuego() {
        return estadoJuego;
    }

    public void setEstadoJuego(EstadoJuego estadoJuego) {
        this.estadoJuego = estadoJuego;
    }

    public int getTAM_NUMERO() {
        return TAM_NUMERO;
    }

    public void setTAM_NUMERO(int TAM_NUMERO) {
        this.TAM_NUMERO = TAM_NUMERO;
    }

    public int getMAX_INTENTOS() {
        return MAX_INTENTOS;
    }

    public void setMAX_INTENTOS(int MAX_INTENTOS) {
        this.MAX_INTENTOS = MAX_INTENTOS;
    }
}
