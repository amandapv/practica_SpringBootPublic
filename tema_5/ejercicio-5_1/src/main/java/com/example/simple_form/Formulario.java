package com.example.simple_form;

public class Formulario {
    private Integer numero1;
    private Integer numero2;

    //getters
    public Integer getNumero1() {
        return numero1;
    }
    public Integer getNumero2() {
        return numero2;
    }

    //opción por si se quiere usar el método getSuma en vez de crear una variable suma en el controlador directamente, así estaría más encapsulado y usando la lógica de negocio
    // public Integer getSuma() {
    //     return getNumero1() + getNumero2(); 
    // }

    //setters
    public void setNumero1(Integer numero1) {
        this.numero1 = numero1;
    }
    public void setNumero2(Integer numero2) {
        this.numero2 = numero2;
    }
}
