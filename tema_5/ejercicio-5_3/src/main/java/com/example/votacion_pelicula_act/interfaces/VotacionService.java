package com.example.votacion_pelicula_act.interfaces;

import java.util.ArrayList;

import com.example.votacion_pelicula_act.Formulario;

public interface VotacionService {
    void votacion(Formulario form) throws RuntimeException; 

    ArrayList<String> getListadoEmail();
    ArrayList<Integer> getListadoVoto();
}
