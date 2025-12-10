package com.example.crud_basico.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.crud_basico.domain.Curso;

@Service
public class CursoServiceImpl implements CursoService {
    private List<Curso> repositorio = new ArrayList<>();

    public List<Curso> obtenerTodos() {
        return repositorio;
    }

    public Curso obtenerPorId(long id) throws RuntimeException {
        for (Curso curso : repositorio)
            if (curso.getId() == id)
                return curso;
        throw new RuntimeException("No se encuentra el curso");
        //return null; // podría lanzar excepción si no encontrado
    }

    public Curso añadir(Curso curso) throws RuntimeException {
        if (repositorio.contains(curso))
            //return null;
            throw new RuntimeException ("No se puede añadir el curso");
        // ver equals curso (mismo id)
        repositorio.add(curso);
        return curso; // podría no devolver nada, o boolean, etc

    }

    public Curso editar(Curso curso) throws RuntimeException {
        int pos = repositorio.indexOf(curso);
        if (pos == -1)
            //return null;
            throw new RuntimeException ("Curso no encontrado");
        repositorio.set(pos, curso);
        return curso;
    }

    public void borrar(Long id) throws RuntimeException {
        Curso curso = this.obtenerPorId(id);
        if (curso != null) {
            repositorio.remove(curso); 
        } else {
            throw new RuntimeException ("Curso no encontrado");
        }
    }

    public List<Curso> buscarPorNombre(String txtNomCursos) {
        txtNomCursos = txtNomCursos.toLowerCase();
        List<Curso> encontrados = new ArrayList<>();
        for (Curso curso : repositorio) {
            if (curso.getNombre().toLowerCase().contains(txtNomCursos)) {
                encontrados.add(curso);
            }
        }
        return encontrados;
    }
}
