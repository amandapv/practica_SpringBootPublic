package com.example.form_dates.interfaces;

import java.time.LocalDate;
import java.time.Year;
import java.util.List;

import com.example.form_dates.Formulario;

public interface CalcFechaService {

    long calcularDias(LocalDate fecha1, LocalDate fecha2);
    
    List<Year> calcularBisiestos(Formulario form);

    List<Integer> calcularAnios11Domingo(Formulario form);

    // void verificarFechas(Formulario form);
}
