package com.example.ejercicio_7_4.services;

import java.util.List;

import com.example.ejercicio_7_4.domain.Video;

public interface VideoService {
    List<Video> obtenerTodos();
    Video obtenerPorId(long id);
    Video añadir(Video video, Long idCurso);
    Video editar(Video video, Long idCurso);
    void borrar (long id);
    List<Video>buscarVideosPorIdCurso (long idCurso);
}
