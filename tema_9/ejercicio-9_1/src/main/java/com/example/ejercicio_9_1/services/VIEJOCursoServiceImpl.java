package com.example.ejercicio_9_1.services;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.ejercicio_9_1.domain.Curso;
import com.example.ejercicio_9_1.domain.Tematica;

@Service
public class VIEJOCursoServiceImpl  {
    private List<Curso> repositorio = new ArrayList<>();

    public List<Curso> obtenerTodos() {

        repositorio.sort(
            Comparator.comparing(Curso::getNombre)
        );

        return repositorio;
    }

    public Curso obtenerPorId(long id) throws RuntimeException{
        for (Curso curso : repositorio)
            if (curso.getId() == id)
                return curso;
        // return null; // podría lanzar excepción si no encontrado
        throw new RuntimeException("No se ha encontrado un curso con ese ID");
    }

    public Curso añadir(Curso curso) throws RuntimeException{
        if (repositorio.contains(curso))
            // return null;
            throw new RuntimeException("Curso ya existente");
        // ver equals Curso (mismo id)
        repositorio.add(curso);
        return curso; // podría no devolver nada, o boolean, etc

    }

    public Curso editar(Curso curso) throws RuntimeException{
        int pos = repositorio.indexOf(curso);
        // if (pos == -1) throw new RuntimeException ("Curso no encontrado");
        if (pos == -1)
            // return null;
            throw new RuntimeException ("Curso no encontrado");
        repositorio.set(pos, curso);
        return curso;
    }

    public void borrar(Long id) throws RuntimeException{
        Curso curso = this.obtenerPorId(id);
        if (curso != null)
            repositorio.remove(curso);
        else {
            throw new RuntimeException("No se ha podido encontrar el curso");
        }
    }

    public List<Curso> buscarPorNombre(String textoNombre) {
        // textoNombre = textoNombre.toLowerCase();
        List<Curso> encontrados = new ArrayList<>();

        for(Curso curso : repositorio) {
            if (curso.getNombre().toLowerCase().contains(textoNombre.toLowerCase())) {
                encontrados.add(curso);
            }
        }
        return encontrados;
    }

    public List<Curso> buscarPorTematica(Tematica tematica) {
        List<Curso> encontrados = new ArrayList<>();

        for(Curso curso : repositorio) {
            if (curso.getTematica() == tematica) {
                encontrados.add(curso);
            }
        }
        return encontrados;
    }

}
