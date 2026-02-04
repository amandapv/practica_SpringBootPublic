package com.example.ejercicio_7_1.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;

import com.example.ejercicio_7_1.domain.Curso;
import com.example.ejercicio_7_1.repositories.CursoReporitory;


@Service
@Primary //como hay dos clases que implementan CursoService, hay que indicarle cuál queremos que (al inyectar en el controlador) coja y por tanto, use por defecto para su implementación
public class CursoServiceImplBD implements CursoService{
    
    @Autowired
    private CursoReporitory repositorio; //Ahora "repositorio" es un objeto de Acceso a Datos (DAO) (antes era un ArrayList)

    public List<Curso> obtenerTodos() {
        //devuelve todos los cursos que haya encontrado
        //return repositorio.findAll(); 

        //devuelve todos los cursos ordenados por nombre del curso
        return repositorio.findAll(Sort.by(Sort.Direction.ASC, "nombre"));
    }


    public Curso obtenerPorId(long id) throws RuntimeException{
        //devuelve un curso en concreto. Por defecto este método devuelve un Optional, para que siga devolviendo Curso y no Optional<Curso> hago que si no lo encuentra lance una excepción, podría devolver null
        return repositorio.findById(id).orElseThrow( ()-> new RuntimeException("Curso no encontrado"));
    }


    public Curso añadir (Curso curso) throws RuntimeException{
        if (repositorio.existsById(curso.getId())) { //si ya hay un curso con ese ID
            throw new RuntimeException("Curso ya existente con ese ID");
        }
        return repositorio.save(curso);
    }


    public Curso editar (Curso curso) throws RuntimeException{
        if (repositorio.existsById(curso.getId())) { //si ya hay un curso con ese ID
            
        }
        obtenerPorId(curso.getId());
        return repositorio.save(curso);
    }
     
}
