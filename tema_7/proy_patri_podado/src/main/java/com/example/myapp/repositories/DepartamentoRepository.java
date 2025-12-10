package com.example.myapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.myapp.domain.Departamento;

public interface DepartamentoRepository extends JpaRepository<Departamento, Long> {
    Departamento findByNombre(String nombre);
}
