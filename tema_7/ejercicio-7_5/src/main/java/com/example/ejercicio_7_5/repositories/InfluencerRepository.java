package com.example.ejercicio_7_5.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ejercicio_7_5.domain.Influencer;

public interface InfluencerRepository extends JpaRepository<Influencer, Long>{
    
}
