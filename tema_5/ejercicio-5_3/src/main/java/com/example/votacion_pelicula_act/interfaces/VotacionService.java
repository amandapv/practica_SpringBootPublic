package com.example.votacion_pelicula_act.interfaces;

import com.example.votacion_pelicula_act.Formulario;

public interface VotacionService {
    void votacion(Formulario form) throws RuntimeException; 
}
