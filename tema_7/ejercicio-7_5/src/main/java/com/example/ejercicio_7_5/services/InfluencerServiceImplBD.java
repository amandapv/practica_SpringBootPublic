package com.example.ejercicio_7_5.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ejercicio_7_5.domain.Influencer;
import com.example.ejercicio_7_5.repositories.InfluencerRepository;

@Service
public class InfluencerServiceImplBD implements InfluencerService{
    
    @Autowired
    private InfluencerRepository influencerRepositorio;

    public List<Influencer> obtenerTodos() {
        return influencerRepositorio.findAll(); //devuelve todos los influencers
    }
    
    public Influencer obtenerPorId (long id) throws RuntimeException{
        return influencerRepositorio.findById(id).orElseThrow( ()-> new RuntimeException("No se encuentra a ese influencer") );
    }

    public Influencer añadir (Influencer influencer) throws RuntimeException{
        //buscar si el ID del influencer NO es null y si no existe previamente...
        if (influencer.getId() != null && influencerRepositorio.existsById(influencer.getId())) {
            throw new RuntimeException("Influencer ya existente");
        }
        return influencerRepositorio.save(influencer);
    }

    public Influencer editar (Influencer influencer) throws RuntimeException{
        //llamar al método de buscar por id para ver si existe o no, en caso de que NO lo encuentre lanzará una excepción
        obtenerPorId(influencer.getId()); 
        return influencerRepositorio.save(influencer);
    }

    public void borrar (long id) {
        //ver si existe ese influencer previamente
        obtenerPorId(id);
        influencerRepositorio.deleteById(id);
    }
}
