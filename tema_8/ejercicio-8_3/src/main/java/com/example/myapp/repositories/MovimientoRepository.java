package com.example.myapp.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.myapp.domain.Cuenta;
import com.example.myapp.domain.Movimiento;

public interface MovimientoRepository extends JpaRepository<Movimiento, Long> {

    // @Query("select m from Movimiento m where m.iban=?1")
    // List<Movimiento> findByCuenta(String iban);

    List<Movimiento> findByCuenta(Cuenta cuenta);

    // No es necesario borrado de mov. de una cuenta ya que Cuenta: cascade =
    // CascadeType.REMOVE
    // @Query("delete from Movimiento m where m.cuenta = ?1")
    // void deleteByCuenta(Cuenta cuenta);

}