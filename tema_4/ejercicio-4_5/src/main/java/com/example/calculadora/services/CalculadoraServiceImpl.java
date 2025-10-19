package com.example.calculadora.services;

import org.springframework.stereotype.Service;

import com.example.calculadora.interfaces.CalculadoraService;

@Service
public class CalculadoraServiceImpl implements CalculadoraService{

    private boolean digitoPulsado = false;
    private Integer operando1;
    private Integer operando2;
    private Integer calcular;

    private enum operaciones{SUMAR, RESTAR};
    private operaciones operacion;


    //getters
    public boolean getDigitoPulsado() {
        return digitoPulsado;
    }

    public Integer getOperando1() {
        return operando1;
    }

    public Integer getOperando2() {
        return operando2;
    }

    public Integer getCalcular() {
        return calcular;
    }


    //método
    public void cogerDigito(Integer numero) {
        if (numero == null) {
            return;
        }

        if (!digitoPulsado) { //si no ha sido pulsado el botón...
            if (operando1 == null) {
                operando1 = numero;
            } else {
                operando1 = operando1 * 10 + numero;
            }
        } else { //si ha sido pulsado el botón...
            if (operando2 == null) {
                operando2 = numero;
            } else {
                operando2 = operando2 * 10 + numero;
            }
            //necesito ambos para no perder el primer número en caso de que si se haya pulsado el botón +
        }
    }


    public void cambiarDigitoSuma() {
        if (operando1 != null) {
            operacion = operaciones.SUMAR;
            digitoPulsado = true;
        }
    }


    public void cambiarDigitoResta() {
        // allow switching to resta only if first operand exists
        if (operando1 != null) {
            operacion = operaciones.RESTAR;
            digitoPulsado = true;
        }

    }
    

    public void hacerOperacion() {
        if (operacion == null) {
            return; // nothing to do
        }

        if (operando1 == null || operando2 == null) {
            // insufficient operands; do nothing or could throw
            return;
        }

        if (operacion == operaciones.SUMAR) {
            calcular = operando1 + operando2;
        } else if (operacion == operaciones.RESTAR) {
            calcular = operando1 - operando2;
        }
    }


    public void limpiarCalculadora() {
        operando1 = null;
        operando2 = null;
        calcular = null;

        //pongo el valor del boleano pulsado a false para que no piense que ya ha sido pulsado
        digitoPulsado = false;
    }

}
