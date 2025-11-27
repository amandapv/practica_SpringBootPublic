package com.example.myapp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.example.myapp.domain.Departamento;
import com.example.myapp.repositories.DepartamentoRepository;

@Service
public class DepartamentoServiceImplBD implements DepartamentoService {

    //Tenemos que inyectar los dos repositorios, porque hay dependencias entre las dos entidades
    @Autowired
    DepartamentoRepository departamentoRepository;
    

    public Departamento añadir(Departamento departamento) throws DataIntegrityViolationException{
        return departamentoRepository.save(departamento);
    }

    public List<Departamento> obtenerTodos() {
        //Método findAll heredado de la interfaz JpaRepository, devuelve una lista con todos los elementos de la tabla
        return departamentoRepository.findAll();
    }

    public Departamento obtenerPorId(long id) {
        //Puede devolver un nulo, de ahí el uso de Optional
        return departamentoRepository.findById(id).orElse(null);
    }

    public Departamento editar(Departamento departamento) throws DataIntegrityViolationException{
        //Método save sirve para añadir y para editar, heredado de la interfaz JpaRepository, a su vez de CrudRepository
        return departamentoRepository.save(departamento);
    }

    /* Método derivado de la interfaz JpaRepository (DepartamentoRepository) deleteByAtributo */
    public void borrar(Long id) {
        //Heredado de la interfaz JpaRepository, a su vez de CrudRepository
        departamentoRepository.deleteById(id);
    }

    public Departamento obtenerPorNombre(String nombre) {
        /* Método derivado de la interfaz JpaRepository (DepartamentoRepository) findByAtributo */
        return departamentoRepository.findByNombre(nombre);
    }
}
