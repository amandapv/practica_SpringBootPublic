package com.example.ejercicio_7_1.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ejercicio_7_1.domain.Curso;

public interface CursoReporitory extends JpaRepository<Curso, Long> {
    
}
