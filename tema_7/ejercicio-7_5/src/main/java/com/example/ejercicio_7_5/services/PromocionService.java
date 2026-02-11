package com.example.ejercicio_7_5.services;

import java.util.List;

import com.example.ejercicio_7_5.domain.Promocion;

public interface PromocionService {
    
    List<Promocion> obtenerTodos();

    Promocion obtenerPorId(long id);

    Promocion añadir(Promocion promocion);

    Promocion editar(Promocion promocion);

    void borrar(long id);

    List<Promocion> mostrarPromocionPorInfluencer(long idInfluencer);
}
