package com.example.ejercicio_7_5.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ejercicio_7_5.domain.Promocion;
import com.example.ejercicio_7_5.repositories.PromocionRepository;

@Service
public class PromocionServiceImplBD implements PromocionService{
    
    @Autowired
    private PromocionRepository promocionRepositorio;
    
    public List<Promocion> obtenerTodos() {
        return promocionRepositorio.findAll();
    }

    public Promocion obtenerPorId(long id) throws RuntimeException {
        return promocionRepositorio.findById(id).orElseThrow( ()-> new RuntimeException("Promoción no encontrada") );
    }

    public Promocion añadir (Promocion promocion) throws RuntimeException {
        //ver qué no exista previamente
        if (promocion.getId() != null && promocionRepositorio.existsById(promocion.getId())) {
            throw new RuntimeException("Promoción ya existente");
        }
        return promocionRepositorio.save(promocion);
    }

    public Promocion editar (Promocion promocion) throws RuntimeException {
        obtenerPorId(promocion.getId());
        return promocionRepositorio.save(promocion);
    }

    public void borrar (long id) {
        obtenerPorId(id);
        promocionRepositorio.deleteById(id);
    }

    public List<Promocion> mostrarPromocionPorInfluencer(long idInfluencer) throws RuntimeException{
        //primero ver si existe ese influencer
        
        return promocionRepositorio.findByInfluencerId(idInfluencer); //devuelvo un listado de promociones de UN influencer que me viene dado por parámetro
    }

}
