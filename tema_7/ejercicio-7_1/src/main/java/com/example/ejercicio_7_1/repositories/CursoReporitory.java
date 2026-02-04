package com.example.ejercicio_7_1.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.ejercicio_7_1.domain.Curso;
import com.example.ejercicio_7_1.domain.Tematica;

public interface CursoReporitory extends JpaRepository<Curso, Long> {

    //método para buscar cursos por nombre introducido
    List<Curso> findByNombreContainingIgnoreCase(String nombreCurso); //containing para que busque esa palabra en cualquier parte del nombre


    //método para buscar cursos por temática
    List<Curso> findByTematica(Tematica tematica);


    //método para filtrar los cursos cuyo importe sea menor o igual al seleccionado
    List <Curso> findByPrecioLessThanEqual(Double precio);
}
