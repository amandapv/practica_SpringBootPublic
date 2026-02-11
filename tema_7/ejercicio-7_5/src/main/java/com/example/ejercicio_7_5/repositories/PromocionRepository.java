package com.example.ejercicio_7_5.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ejercicio_7_5.domain.Promocion;

public interface PromocionRepository extends JpaRepository<Promocion, Long>{
    
}
