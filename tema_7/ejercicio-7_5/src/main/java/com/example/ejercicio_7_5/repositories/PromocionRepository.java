package com.example.ejercicio_7_5.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ejercicio_7_5.domain.Promocion;
import java.util.List;


public interface PromocionRepository extends JpaRepository<Promocion, Long>{
    List<Promocion> findByInfluencerId(long idInfluencer); //método para buscar influencers por un id

    Promocion findByCursoId(long idCurso); //método para buscar cursos por un id


}
