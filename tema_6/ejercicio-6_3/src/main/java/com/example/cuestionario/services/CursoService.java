package com.example.cuestionario.services;

import java.util.List;

import com.example.cuestionario.domain.Curso;

public interface CursoService {
    Curso añadir(Curso empleado);

    List<Curso> obtenerTodos();

    Curso obtenerPorId(long id) ;

    Curso editar(Curso empleado) ;

    void borrar(Long id);

}
