package com.example.myapp.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.myapp.domain.Cuenta;
import com.example.myapp.domain.Movimiento;

@Service
public interface MovimientoService {
    boolean añadir(Movimiento movimiento);

    List<Movimiento> obtenerTodos();

    List<Movimiento> obtenerPorCuenta(Cuenta cuenta);

    // No es necesario ya que Cuenta: cascade = CascadeType.REMOVE
    // void deleteByIban(String IBAN);
}
