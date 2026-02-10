package com.example.ejercicio_7_4.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ejercicio_7_4.domain.Video;

public interface VideoRepository extends JpaRepository<Video, Long>{
    List<Video> findByCursoId(Long idCurso); //busca vídeos donde el atributo "curso" tenga ese id
}
