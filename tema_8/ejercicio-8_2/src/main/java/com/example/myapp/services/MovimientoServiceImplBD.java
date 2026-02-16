package com.example.myapp.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.myapp.domain.Cuenta;
import com.example.myapp.domain.Movimiento;
import com.example.myapp.repositories.CuentaRepository;
import com.example.myapp.repositories.MovimientoRepository;

@Service
public class MovimientoServiceImplBD implements MovimientoService {
    @Autowired
    MovimientoRepository movimientoRepository;

    @Autowired
    CuentaRepository cuentaRepository;

    public boolean añadir(Movimiento movimiento) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            // String currentUserName = authentication.getName(); // admin
            String currentUserRol = authentication.getAuthorities().toString(); // [ROLE_ADMIN]
            if (currentUserRol.equals("[ROLE_USUARIO]") && movimiento.getImporte() < 0)
                return false;
        }
        if (movimiento.getImporte() >= -300 && movimiento.getImporte() <= 1000) {
            movimiento.setFecha(LocalDateTime.now());
            movimientoRepository.save(movimiento);
            return true;
        } else
            return false;
    }

    public List<Movimiento> obtenerTodos() {
        return movimientoRepository.findAll();
    }

    public List<Movimiento> obtenerPorCuenta(Cuenta cuenta) {
        if (cuenta != null) {
            return movimientoRepository.findByCuenta(cuenta);
        } else
            return null;

    }
    // No es necesario ya que Cuenta: cascade = CascadeType.REMOVE
    // public void deleteByIban(String iban) {
    // Cuenta cuenta = cuentaRepository.findById(iban).orElse(null);
    // if (cuenta != null)
    // movimientoRepository.deleteByCuenta(cuenta);
    // }
}
