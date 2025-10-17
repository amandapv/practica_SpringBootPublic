package com.example.calculo_hipotenusa.services;

import org.springframework.stereotype.Service;

@Service
public class CalcHipoService {
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
      throw new RuntimeException("Algún cateto menor que cero");

    } else if (cateto1 > 1000 || cateto2 > 1000) { //si alguno de esos números es mayor que 1000...
      throw new RuntimeException("Algún cateto mayor que 1000");
    }
    return Math.hypot(cateto1, cateto2);
  }
}
