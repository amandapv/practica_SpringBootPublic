package com.example.myapp.services;

import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;

import com.example.myapp.domain.Departamento;

public interface DepartamentoService {
    Departamento añadir(Departamento d) throws DataIntegrityViolationException;

    List<Departamento> obtenerTodos();

    Departamento obtenerPorId(long id);

    Departamento editar(Departamento d) throws DataIntegrityViolationException;

    void borrar(Long id);

    Departamento obtenerPorNombre(String nombre);

}
