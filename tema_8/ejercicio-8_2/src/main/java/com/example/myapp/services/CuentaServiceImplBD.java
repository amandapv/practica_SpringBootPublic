package com.example.myapp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.myapp.domain.Cuenta;
import com.example.myapp.domain.Movimiento;
import com.example.myapp.repositories.CuentaRepository;

@Service
public class CuentaServiceImplBD implements CuentaService {

    @Autowired
    CuentaRepository cuentaRepository;

    public Cuenta añadir(Cuenta cuenta) {
        cuenta.setSaldo(0);
        return cuentaRepository.save(cuenta);
    }

    public List<Cuenta> obtenerTodos() {
        return cuentaRepository.findAll();
    }

    public Cuenta obtenerPorId(String iban) {
        return cuentaRepository.findById(iban).orElse(null);
    }

    public Cuenta editar(Cuenta cuenta) {
        return cuentaRepository.save(cuenta);
    }

    public void borrar(String iban) {
        Cuenta cuenta = this.obtenerPorId(iban);
        if (cuenta != null && cuenta.getSaldo() == 0) {
            cuentaRepository.delete(cuenta);
        }
    }

    public void actualizarSaldo(Movimiento movimiento) {
        Cuenta cuenta = movimiento.getCuenta();
        if (cuenta != null) {
            cuenta.setSaldo(cuenta.getSaldo() + movimiento.getImporte());
            cuentaRepository.save(cuenta);
        }

    }

}
