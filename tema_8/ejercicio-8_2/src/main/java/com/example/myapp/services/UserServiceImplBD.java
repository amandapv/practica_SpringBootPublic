package com.example.myapp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.myapp.domain.Usuario;
import com.example.myapp.repositories.UsuarioRepository;

@Service
public class UserServiceImplBD implements UserService{
    
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<Usuario> obtenerTodos() {
        return usuarioRepository.findAll();
    }

    public Usuario obtenerPorId(long id) throws RuntimeException{
        return usuarioRepository.findById(id).orElseThrow( ()-> new RuntimeException());
    }

    public Usuario añadir(Usuario usuario) throws DataIntegrityViolationException {
        //no me hace falta este if si manejo la excepción siguiente que se produciría en caso de que ya existiera un usuario
        // if (usuarioRepository.findByNombre(usuario.getNombre()) != null) { //si el nombre del usuario no es nulo
        //     return  null; //ya existe ese nombre
        // }
        String passCrypted = passwordEncoder.encode(usuario.getPassword());
        usuario.setPassword(passCrypted);
        try {
            return usuarioRepository.save(usuario);
        } catch (DataIntegrityViolationException e) { 
            e.printStackTrace();
            return null;
        }
    }

    public Usuario editar(Usuario usuario) throws DataIntegrityViolationException {
        String passCrypted = passwordEncoder.encode(usuario.getPassword());
        usuario.setPassword(passCrypted);
        try {
            return usuarioRepository.save(usuario);
        } catch (DataIntegrityViolationException e) {  
            e.printStackTrace();
            return null;
        }
    }

    public void borrar(long id) throws RuntimeException {
        Usuario usuario = obtenerPorId(id);
        if (usuario != null) {
            usuarioRepository.deleteById(id);
        }
    }
}
