package com.example.ejercicio_7_7.services;

import java.util.List;

import com.example.ejercicio_7_7.domain.Autor;

public interface AutorService {
    List<Autor> obtenerTodos();
    Autor obtenerPorId(long id);
    Autor añadir(Autor autor);
    Autor editar(Autor autor);
    void borrar (long id);
    Autor obtenerPorNombre(String nombre);
}
