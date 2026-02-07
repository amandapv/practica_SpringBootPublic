package com.example.ejercicio_7_2.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ejercicio_7_2.domain.Autor;
import com.example.ejercicio_7_2.domain.Curso;

public interface AutorRepository extends JpaRepository<Autor, Long> {

    //busca los autores por nombre
    // List<Curso> findByAutor(String nombre); 

    //Devuelve un autor a partir de su nombre
    Autor findByNombre(String nombre);
}
