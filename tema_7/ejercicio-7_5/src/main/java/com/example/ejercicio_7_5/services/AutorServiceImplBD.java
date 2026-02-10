package com.example.ejercicio_7_5.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ejercicio_7_5.domain.Autor;
import com.example.ejercicio_7_5.repositories.AutorRepository;

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
        if (autor.getId() != null && repositorio.existsById(autor.getId())) { //si el ID NO es null... (recordemos que el valor ID a la hora de crearlo es null ya que quien lo gestiona el JPA y no Spring Boot, por lo que JPA es quien ya le asignará un número)
            //si ya hay un autor con ese ID -- existsById devuelve true o false, para confirmar si existe o no el ID
            throw new RuntimeException("Autor con ID ya existente");
        }
        if (repositorio.existsByNombre(autor.getNombre())) { //si existe ese nombre en la BBDD...
            throw new RuntimeException("Autor con nombre ya existente");
        }
        
        if (repositorio.existsByEmail(autor.getEmail())) { //si existe ese email en la BBDD...
            throw new RuntimeException("Autor con email ya existente");
        }

        return repositorio.save(autor);
    }

    public Autor editar(Autor autor) throws RuntimeException { //no hace falta poner RuntimeException, pero queda más claro para saber que sí se maneja si existe o no ese ID de curso (se maneja en el método obtenerPorId que llamamos aquí dentro)
        Autor autorAntiguo = obtenerPorId(autor.getId()); //Obtenemos el autor ORIGINAL de la base de datos. Si el ID no existe, el método lanza la excepción (RuntimeException) y el programa se detiene ahí mismo. Nunca llega a la línea del save.
 
        if (!autorAntiguo.getNombre().equals(autor.getNombre())) { //Si el nombre NO es igual al nuevo dato, es decir ha cambiado ...
            if (repositorio.existsByNombre(autor.getNombre())) { //Si ha cambiado, verificar que el nuevo nombre (el que aún no hemos añadido a la BBDD y pasamos por parámetro) no esté en uso...
                throw new RuntimeException("Autor con nombre ya existente");
            }
        }
        
        if (!autorAntiguo.getEmail().equals(autor.getEmail())) { //Si el email NO es igual al nuevo dato, es decir ha cambiado ...
            if (repositorio.existsByEmail(autor.getEmail())) { //Si ha cambiado, verificar que el nuevo email (el que aún no hemos añadido a la BBDD y pasamos por parámetro) no esté en uso...
                throw new RuntimeException("Autor con email ya existente");
            }
        }

        return repositorio.save(autor);
    }

    public void borrar(long id) throws RuntimeException{
        Long cantidadCursosAutor = repositorio.cantidadCursosAutor(id);
        if (cantidadCursosAutor == 0) {
            repositorio.deleteById(id);
        } else {
            throw new RuntimeException("Hay cursos asignados a este autor, por favor, elimine los cursos previamente");
        }
        //esto no sirve porque se produce una excepción debido a que si intentamos eliminar un autor que tiene cursos asignados, quedarán cursos con un autor inexistente asignado
        // obtenerPorId(id);
        // repositorio.deleteById(id);
    }

    public Autor obtenerPorNombre(String nombre) {
        return repositorio.findByNombre(nombre);
    }

}
