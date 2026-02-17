package com.example.myapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.example.myapp.domain.Usuario;
import com.example.myapp.repositories.UsuarioRepository;

@Component
//clase que se encarga de la gestión del usuario que se identifique en la aplicación
public class UserDetailsServiceImpl implements UserDetailsService{
    
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        Usuario usuario = usuarioRepository.findByNombre(username); //método para buscar un nombre de un usuario
        if (usuario == null) {
            throw new UsernameNotFoundException("Usuario no encontrado");
        }
        return User
            .withUsername(username)
            .roles(usuario.getRol().toString())
            .password(usuario.getPassword())
            .build();
    }
    
}
