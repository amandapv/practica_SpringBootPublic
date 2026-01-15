package com.example.crud_basico_pract.services;

import java.util.List;

import com.example.crud_basico_pract.domain.Curso;
import com.example.crud_basico_pract.domain.Tematica;

public interface CursoService {
    Curso añadir(Curso curso);

    List<Curso> obtenerTodos();

    Curso obtenerPorId(long id) ;

    Curso editar(Curso curso) ;

    void borrar(Long id);

    List<Curso> buscarPorNombre(String textoNombre);

    List<Curso> buscarPorTematica(Tematica tematica);
}
