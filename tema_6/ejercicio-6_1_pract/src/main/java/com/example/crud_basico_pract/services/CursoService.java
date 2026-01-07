package com.example.crud_basico_pract.services;

import java.util.List;

import com.example.crud_basico_pract.domain.Curso;

public interface CursoService {
    Curso añadir(Curso curso);

    List<Curso> obtenerTodos();

    Curso obtenerPorId(long id) ;

    Curso editar(Curso curso) ;

    void borrar(Long id);

}
