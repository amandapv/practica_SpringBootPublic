package com.example.calc_matemat_con_interfaz.services;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.example.calc_matemat_con_interfaz.interfaces.CalculosService;

@Service
public class CalculosServiceImpl implements CalculosService {

  //método para saber si un número es primo
  public boolean calcularPrimo(String numPrimo) throws RuntimeException {
    int numeroPrimo;

    //intentamos parsear el valor de la String numPrimo y meterlo en la variable numérica numeroPrimo
    try {
      numeroPrimo = Integer.parseInt(numPrimo);
    } catch (Exception e) {
      throw new RuntimeException("El número introducido no es numérico");
    }
    if (numeroPrimo <= 0) { // si el número es menor que 0...
      throw new RuntimeException("El número debe ser mayor a 0"); //lanza a RuntimeException la excepción que acabo de crear
    }

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
  public Double calcularHipotenusa(String cat1, String cat2) throws RuntimeException {  //convierto los catetos a string para que cuando llame a la función del servicio que calcula la hipotenusa, se pueda manejar la excepción de si es o no un número. Si defino directamente un Double, salta directamente una Whitelabel Error Page, ya que le estoy diciendo que es ese tipo de dato por lo que al intentar introducir una String (por ej) saltaría dicha Whitelabel Error Page. 
    Double cateto1, cateto2;     
    
    try {
      cateto1 = Double.parseDouble(cat1);
      cateto2 = Double.parseDouble(cat2);
    } catch (Exception ex) {
      throw new RuntimeException("Algún cateto no numérico");
    }
    
    if (Double.isNaN(cateto1) || Double.isNaN(cateto2)) { //si alguno de esos valores es un número...
      throw new RuntimeException("Algún cateto no numérico");
    }
    else if (cateto1 <= 0 || cateto2 <= 0) { //si alguno de esos números es menor o igual que cero...
      throw new RuntimeException("Los catetos deben ser mayores que 0");

    } else if (cateto1 > 1000 || cateto2 > 1000) { //si alguno de esos números es mayor que 1000...
      throw new RuntimeException("Algún cateto mayor que 1000");
    }
    return Math.hypot(cateto1, cateto2);
  }


  //método para calcular los divisores de un número
  public ArrayList<Integer> calcularDivisores(String numDiv) throws RuntimeException {
    ArrayList<Integer> listaDivisores = new ArrayList<>();
    int numDivisor;

    try {
      numDivisor = Integer.parseInt(numDiv);
    } catch (Exception e) {
      throw new RuntimeException("El número introducido no es numérico");
    }
    if (numDivisor <= 0) { // si el número es menor que 0...
      throw new RuntimeException("El número debe ser mayor a 0"); //lanza a RuntimeException la excepción que acabo de crear
    }

    for (int i = 1; i <= numDivisor; i++) {
      if (numDivisor % i == 0) {
        listaDivisores.add(i);
      }
    }

    return listaDivisores;
  }
  
}
