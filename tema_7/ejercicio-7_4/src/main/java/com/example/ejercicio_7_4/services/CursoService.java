package com.example.ejercicio_7_4.services;

import java.util.List;

import com.example.ejercicio_7_4.domain.Curso;
import com.example.ejercicio_7_4.domain.Tematica;

public interface CursoService {
    Curso añadir(Curso curso);

    List<Curso> obtenerTodos();

    Curso obtenerPorId(long id) ;

    Curso editar(Curso curso) ;

    void borrar(Long id);

    List<Curso> buscarPorNombre(String textoNombre);

    List<Curso> buscarPorTematica(Tematica tematica);

    List<Curso> filtrarImporteMenorIgualPrecio(Double precio);

    List<Curso> buscarPorAutorId(long autor);
}
