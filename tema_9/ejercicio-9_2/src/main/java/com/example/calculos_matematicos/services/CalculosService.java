package com.example.calculos_matematicos.services;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

@Service
public class CalculosService {

  //método para saber si un número es primo
  public boolean calcularPrimo(Integer numeroPrimo){

    if (numeroPrimo <= 1 || numeroPrimo % 2 == 0) { // si el número es menor o igual a 1.. Y.. el número es par...
      return false; //no es primo
    }
      
    for (int i = 3; i*i <= numeroPrimo; i+=2) { //solo se recorreran los impares (porque antes ya hemos quitado de la lista los pares y menores a 2)

      if (numeroPrimo % i == 0) { //si el número es divisible entre "i"...
        return false;
      }
    }
    return true;
  }



  //método para calcular una hipotenusa
  public Double calcularHipotenusa(Double cateto1, Double cateto2) {  //convierto los catetos a string para que cuando llame a la función del servicio que calcula la hipotenusa, se pueda manejar la excepción de si es o no un número. Si defino directamente un Double, salta directamente una Whitelabel Error Page, ya que le estoy diciendo que es ese tipo de dato por lo que al intentar introducir una String (por ej) saltaría dicha Whitelabel Error Page. 
    return Math.hypot(cateto1, cateto2);
  }


  //método para calcular los divisores de un número
  public ArrayList<Integer> calcularDivisores(Integer numDivisor) {
    ArrayList<Integer> listaDivisores = new ArrayList<>();

    for (int i = 1; i <= numDivisor; i++) {
      if (numDivisor % i == 0) {
        listaDivisores.add(i);
      }
    }

    return listaDivisores;
  }
  
}
