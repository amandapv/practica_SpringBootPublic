package com.example.calculadora.interfaces;

public interface CalculadoraService {
    
    //getters
    boolean getDigitoPulsado();
    Integer getOperando1();
    Integer getOperando2();
    Integer getCalcular();


    //metodos
    void cogerDigito(Integer numero);

    void cambiarDigitoSuma();

    void cambiarDigitoResta();

    void hacerOperacion();

    void limpiarCalculadora();
}
