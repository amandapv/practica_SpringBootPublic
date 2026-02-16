package com.example.myapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.myapp.domain.Usuario;


public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
    Usuario findByNombre(String username);
}
