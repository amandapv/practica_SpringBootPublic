package com.example.ejercicio_7_5.services;

import java.util.List;

import com.example.ejercicio_7_5.domain.Influencer;

public interface InfluencerService {

    List<Influencer> obtenerTodos();
    
    Influencer obtenerPorId(long id);

    Influencer añadir(Influencer influencer);

    Influencer editar(Influencer influencer);

    void borrar(long id);
    
}
