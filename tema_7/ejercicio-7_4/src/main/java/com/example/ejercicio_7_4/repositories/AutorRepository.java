package com.example.ejercicio_7_4.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.ejercicio_7_4.domain.Autor;
import com.example.ejercicio_7_4.domain.Curso;

public interface AutorRepository extends JpaRepository<Autor, Long> {

    //busca los autores por nombre
    // List<Curso> findByAutor(String nombre); 

    //Devuelve un autor a partir de su nombre
    Autor findByNombre(String nombre);

    //método para saber si existe un nombre
    boolean existsByNombre(String nombre);

    //método para saber si existe un email
    boolean existsByEmail(String email);
    
    //método para saber cuantos cursos hay
    @Query("select count (curso) from Curso curso where curso.autor.id = ?1")
    Long cantidadCursosAutor(Long idAutor);

    //método para obtener el precio total de los cursos de un autor
    @Query("select sum (curso.precio) from Curso curso where curso.autor.id = :idAutor")
    Double sumaPrecioTotalCursosAutor(Long idAutor);
 
    //versión 2 del método anterior -- para obtener el precio total de los cursos de un autor (se detalla en los comentarios el por qué) 
    //para quitarnos las líneas del service en las que se evalúa si el total de los cursos está a null (ya sea porque aún no se había creado o porque no tienen precio). COALESCE le dice a la BBDD: "Si la suma es nula, devuélveme un 0.0"
    @Query("select coalesce(sum (curso.precio), 0.0) from Curso curso where curso.autor.id = :idAutor") //OALESCE en tu AutorRepository. Esto le dice a la base de datos: "Si la suma es nula, devuélveme un 0.0"
    //a veces, al compilar, Java "olvida" el nombre de los parámetros (el idAutor). Para que la @Query siempre encuentre el valor :idAutor, es una buena práctica añadir la anotación @Param.:
    Double sumaPrecioTotalCursosAutorV2(@Param("idAutor") Long idAutor);

}
