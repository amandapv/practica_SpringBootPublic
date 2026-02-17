package com.example.myapp.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.myapp.domain.Cuenta;
import com.example.myapp.domain.Movimiento;

@Service
public interface CuentaService {
    Cuenta añadir(Cuenta cuenta);

    List<Cuenta> obtenerTodos();

    Cuenta obtenerPorId(String IBAN);

    Cuenta editar(Cuenta cuenta);

    void borrar(String iban);

    void actualizarSaldo(Movimiento movimiento);

}
