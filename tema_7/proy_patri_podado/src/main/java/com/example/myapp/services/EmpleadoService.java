package com.example.myapp.services;

import java.util.List;

import com.example.myapp.domain.Departamento;
import com.example.myapp.domain.Empleado;
import com.example.myapp.domain.Genero;

public interface EmpleadoService {

    List<Empleado> obtenerTodos();

    // Empleado obtenerPorId(long id) throws RuntimeException;

    Empleado añadir(Empleado empleado);

    Empleado editar(Empleado empleado) throws RuntimeException;

    void borrar(Long id) throws RuntimeException;

    List<Empleado> buscarPorNombre(String nombre);

    List<Empleado> buscarPorGenero(Genero genero);

    List<Empleado> obtenerPorDepartamento(Long idDepto);

    List<Empleado> obtenerEmpleadosSalarioMayor(double salario);

    List<Empleado> obtenerEmpleadoSalarioMayorMedia();

   /*Codigo aquí a escribir */
}
