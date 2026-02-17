package com.example.form_dates.services;

import java.time.LocalDate;
import java.time.Year;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.form_dates.Formulario;
import com.example.form_dates.interfaces.CalcFechaService;


@Service // anotación servicio
public class CalcFechaServiceImpl implements CalcFechaService{

    //método para verificar que la fecha 1 es anterior a la fecha 2...
    // public void verificarFechas(Formulario form) throws RuntimeException {
    //     if (form.getFecha1().isAfter(form.getFecha2())) { //si la fecha1 es posterior a la fecha2...
    //         throw new RuntimeException("La primera fecha ha de ser anterior a la segunda fecha.");
    //     }
    // }


    //método para calcular cuántos días hay entre ambas fechas
    public long calcularDias(LocalDate fecha1, LocalDate fecha2) {
        // ChronoUnit.DAYS.between() devuelve la diferencia en días como un valor long
        long dias = ChronoUnit.DAYS.between(fecha1, fecha2); 
        return dias;
    }


    //método para listar los años bisiestos comprendidos entre las fechas introducidas (ambas incluídas)
    public List<Year> calcularBisiestos(Formulario form) {
        List<Year> listadoBisiestos = new ArrayList<>();

        for (int i = form.getFecha1().getYear(); i <= form.getFecha2().getYear(); i++) { //recorrer las fechas
            Year anio = Year.of(i); //le digo que el entero actual es un año

            if (anio.isLeap()) { //verifica si el año actual es bisiesto
                listadoBisiestos.add(anio); 
            }
        }
        return listadoBisiestos;
    }

    
    //método para saber cuántas veces coincidió que el 1 de enero fuera domingo
    public List<Integer> calcularAnios11Domingo(Formulario form) {
        List<Integer> listadoAniosDom = new ArrayList<>();

        for (int i = form.getFecha1().getYear(); i <= form.getFecha2().getYear(); i++) {
            LocalDate fechaAComprobar = LocalDate.of(i, 01, 01); //creo una fecha con el año actual que me recorro, el mes de enero y el día 1

            if (fechaAComprobar.getDayOfWeek().getValue() == 7) { //si el día de la semana es el 7 (domingo)
                listadoAniosDom.add(fechaAComprobar.getYear());
            }
        }
        return listadoAniosDom;
    }

}
