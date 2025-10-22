package com.example.form_dates;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

public class Formulario {
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate fecha1;
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate fecha2;

    //getters
    public LocalDate getFecha1() {
        return fecha1;
    }
    public LocalDate getFecha2() {
        return fecha2;
    }

    //setters
    public void setFecha1(LocalDate fecha1) {
        this.fecha1 = fecha1;
    }
    public void setFecha2(LocalDate fecha2) {
        this.fecha2 = fecha2;
    }
}
