package com.example.form_dates.interfaces;

import java.time.Year;
import java.util.List;

import com.example.form_dates.Formulario;

public interface CalcFechaService {

    long calcularDias(Formulario form);
    
    List<Year> calcularBisiestos(Formulario form);

    List<Integer> calcularAnios11Domingo(Formulario form);
}
