package com.example.ejercicio_7_4.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ejercicio_7_4.domain.Curso;
import com.example.ejercicio_7_4.domain.Video;
import com.example.ejercicio_7_4.repositories.VideoRepository;

@Service
public class VideoServiceImplBD implements VideoService{

    @Autowired
    private VideoRepository videoRepositorio;

    @Autowired
    private CursoService cursoService;

    //método para obtener todos los vídeos
    public List<Video> obtenerTodos() {
        return videoRepositorio.findAll();
    }

    //método para obtener un vídeo en concreto por su ID
    public Video obtenerPorId(long id) throws RuntimeException{
        return videoRepositorio.findById(id).orElseThrow( ()-> new RuntimeException("Vídeo no encontrado"));
    }

    //método para añadir vídeos
    public Video añadir(Video video, Long idCurso) throws RuntimeException {
        if (video.getId() != null && videoRepositorio.existsById(video.getId())) { //cuando lo guardo antes de añadirlo a la BBDD tiene un null el ID ya que este lo genera la propia BBDD y no java. Entonces si el ID no es null signfica que ya existe en la BD
            throw new RuntimeException("El vídeo ya existe");
        }

        Curso curso = cursoService.obtenerPorId(idCurso); //busco si existe el curso con ese ID (lanzará una excepción si no existe ese curso)
        video.setCurso(curso); //relleno el curso tras buscar el ID del curso para ver si existe, y así sabe a qué curso hace referencia el vídeo a añadir

        return videoRepositorio.save(video);
    } 

    //método para editar vídeos
    public Video editar(Video video, Long idCurso) throws RuntimeException{ //le paso el id del curso (como en el método anterior de añadir vídeo), ya que si no al editarlo no tengo input para rellenar el id del curso así que tengo que decirle previamente qué curso quiero editar. Esto podría cambiarse por añadir directamente un listado de cursos a la hora de la cración de vídeos
        obtenerPorId(video.getId()); //primero buscar el vídeo
        
        Curso curso = cursoService.obtenerPorId(idCurso); //busco si existe ese curso que me pasa por parámetro, si no ya lanza el error ese método
        video.setCurso(curso); //relleno la info del curso en la variable "curso" del Video

        return videoRepositorio.save(video);
    }

    public void borrar(long idVideo) throws RuntimeException {
        Video video = obtenerPorId(idVideo); //primero buscar el vídeo
        Curso curso = video.getCurso(); //obtener el curso asociado

        //eliminar el video de la lista del curso
        curso.getVideos().remove(video); //TENGO QUE ELIMINAR EL VÍDEO DEL CURSO ya que es el padre quien tiene asociado x vídeos (en el arraylist de videos) y aunque borre el vídeo (de la  clase Video, el hijo) no funcionaría (pero tampoco daría error) ya que solo estoy borrando al hijo y se seguirá mostrando los vídeos de x curso (por el arraylist<Video>)

        //borrar el video directamente
        videoRepositorio.delete(video);
    }

    public List<Video> buscarVideosPorIdCurso(long idCurso) throws RuntimeException{
        Curso curso = cursoService.obtenerPorId(idCurso); //buscamos si el ID existe, si no ya lanzará la excepción
        return videoRepositorio.findByCursoId(curso.getId());
    }
    
}
