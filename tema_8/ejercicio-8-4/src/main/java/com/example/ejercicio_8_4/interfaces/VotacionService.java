package com.example.ejercicio_8_4.interfaces;

import java.util.ArrayList;

import com.example.ejercicio_8_4.domain.Voto;

public interface VotacionService {
    void votacion(Voto form) throws RuntimeException; 

    ArrayList<String> getListadoEmail();
    ArrayList<Integer> getListadoVoto();
}
