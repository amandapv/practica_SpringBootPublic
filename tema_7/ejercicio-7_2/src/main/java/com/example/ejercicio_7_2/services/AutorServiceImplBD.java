package com.example.ejercicio_7_2.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ejercicio_7_2.domain.Autor;
import com.example.ejercicio_7_2.repositories.AutorRepository;

@Service
public class AutorServiceImplBD implements AutorService {
    
    @Autowired
    AutorRepository repositorio; //Ahora "repositorio" es un objeto de Acceso a Datos (DAO) (antes era un ArrayList)

    public List<Autor> obtenerTodos() {
        return repositorio.findAll(); //devuelve todos los autores
    }

    public Autor obtenerPorId(long id) throws RuntimeException{
        //devuelve un autor en concreto. Por defecto este método devuelve un Optional, para que siga devolviendo Autor y no Optional<Autor> hago que si no lo encuentra lance una excepción, podría devolver null
        return repositorio.findById(id).orElseThrow( () -> new RuntimeException("Autor no encontrado"));
    }

    public Autor añadir(Autor autor) throws RuntimeException {
        if (autor.getId() != null) { //si el ID NO es null... (recordemos que el valor ID a la hora de crearlo es null ya que quien lo gestiona el JPA y no Spring Boot, por lo que JPA es quien ya le asignará un número)
            if (repositorio.existsById(autor.getId())) { //si ya hay un autor con ese ID -- existsById devuelve true o false, para confirmar si existe o no el ID
                throw new RuntimeException("Autor ya existente");
            }
        }
        return repositorio.save(autor);
    }

    public Autor editar(Autor autor) throws RuntimeException { //no hace falta poner RuntimeException, pero queda más claro para saber que sí se maneja si existe o no ese ID de curso (se maneja en el método obtenerPorId que llamamos aquí dentro)
        obtenerPorId(autor.getId()); //si el ID no existe, el método lanza la excepción (RuntimeException) y el programa se detiene ahí mismo. Nunca llega a la línea del save.
        return repositorio.save(autor);
    }

    public void borrar(long id) {
        obtenerPorId(id);
        repositorio.deleteById(id);
    }

    public Autor obtenerPorNombre(String nombre) {
        return repositorio.findByNombre(nombre);
    }
}
