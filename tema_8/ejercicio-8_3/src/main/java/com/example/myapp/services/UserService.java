package com.example.myapp.services;

import java.util.List;

import com.example.myapp.domain.Usuario;

public interface UserService {
    List<Usuario> obtenerTodos();
    Usuario obtenerPorId(long id);
    Usuario añadir(Usuario usuario);
    Usuario editar(Usuario usuario);
    void borrar(long id);
}
