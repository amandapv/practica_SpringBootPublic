package com.example.ejercicio_8_1.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;

import com.example.ejercicio_8_1.domain.Curso;
import com.example.ejercicio_8_1.domain.Tematica;
import com.example.ejercicio_8_1.repositories.CursoReporitory;


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
        return repositorio.findById(id).orElseThrow( ()-> new RuntimeException("Curso no encontrado")); //findById devuelve el curso con ese ID en concreto (si lo ha encontrado; en este caso si no lo encuentra devuelve un RuntimeException)
    }


    public Curso añadir (Curso curso) throws RuntimeException{

        //Valida que el ID no exista previamente para asegurar que estamos creando un registro nuevo y no sobrescribiendo uno existente
        if (curso.getId() != null) {
            if (repositorio.existsById(curso.getId())) { //si ya hay un curso con ese ID -- existsById devuelve true o false, para confirmar si existe o no el ID
                throw new RuntimeException("Curso ya existente con ese ID");
            }
        }
        return repositorio.save(curso);
    }


    //paso por parámetro un curso porque normalmente, cuando un usuario está en una página web para editar, ya ha rellenado un formulario, por eso no paso por parámetro el ID
    public Curso editar (Curso curso) throws RuntimeException{ //no hace falta poner RuntimeException, pero queda más claro para saber que sí se maneja si existe o no ese ID de curso (se maneja en el método obtenerPorId que llamamos aquí dentro)
        obtenerPorId(curso.getId()); //obtenemos el curso llamando al método definido anteriormente para buscar un curso por su ID (este lanzará una excepción si no lo encuentra)... para buscar un curso por su ID (este lanzará una excepción si no lo encuentra)... ¿Qué devuelve?: Un Optional<T>. Esto es una "caja" que puede contener el objeto o estar vacía.
        return repositorio.save(curso); //y lo guardamos
    }


    public void borrar (Long id) {
        obtenerPorId(id); //buscamos el curso a eliminar bajo el ID proporcionado... como dentro del método "obtenerPorId" se maneja si existe o no ese ID, lanzará un error en caso de que no lo encuentre
        repositorio.deleteById(id); //eliminamos el ID en concreto
    }


    //este método se define en la interfaz CursoService, que retorna a su vez el método que se definió en el repositorio CursoRepository para buscar por nombre
    public List<Curso> buscarPorNombre(String textoNombre) {
        return repositorio.findByNombreContainingIgnoreCase(textoNombre);
    }
    

    public List<Curso> buscarPorTematica(Tematica tematica) {
        return repositorio.findByTematica(tematica);
    }


    public List<Curso> filtrarImporteMenorIgualPrecio(Double precio) {
        return repositorio.findByPrecioLessThanEqual(precio);
    }
}
