package com.example.cuestionario.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.cuestionario.domain.Curso;

@Service
public class CursoServiceImpl implements CursoService {
    private List<Curso> repositorio = new ArrayList<>();

    public List<Curso> obtenerTodos() {
        return repositorio;
    }

    public Curso obtenerPorId(long id) {
        for (Curso curso : repositorio)
            if (curso.getId() == id)
                return curso;
        return null; // podría lanzar excepción si no encontrado
    }

    public Curso añadir(Curso curso) {
        if (repositorio.contains(curso))
            return null;
        // ver equals curso (mismo id)
        repositorio.add(curso);
        return curso; // podría no devolver nada, o boolean, etc

    }

    public Curso editar(Curso curso) {
        int pos = repositorio.indexOf(curso);
        // if (pos == -1) throw new RuntimeException ("curso no encontrado");
        if (pos == -1)
            return null;
        repositorio.set(pos, curso);
        return curso;
    }

    public void borrar(Long id) {
        Curso curso = this.obtenerPorId(id);
        if (curso != null)
            repositorio.remove(curso);
    }
}
