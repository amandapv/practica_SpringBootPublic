package com.example.myapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.myapp.domain.Cuenta;

public interface CuentaRepository extends JpaRepository<Cuenta, String> {
}
