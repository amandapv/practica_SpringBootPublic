package com.example.calculos_matematicos.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.calculos_matematicos.dto.Divisor;
import com.example.calculos_matematicos.dto.Hipotenusa;
import com.example.calculos_matematicos.dto.Primo;
import com.example.calculos_matematicos.services.CalculosService;

@RestController
@RequestMapping("/calculos")
public class CalculosController {
    @Autowired
    private CalculosService calcularServicio;

    @GetMapping("/primo") //pasar los parámetros en la parte de la query, después del path. Para rescatar los valores proporcionados en la parte query usaremos @RequestParam
    public ResponseEntity<?> showPrimo(@RequestParam (required = false) Integer numCalcPrimo) {
        if (numCalcPrimo <= 0) { //si el número es menor o igual a 0...
            return ResponseEntity.badRequest().build(); //devuelvo un error 400
        }
        Primo primo = new Primo(numCalcPrimo, calcularServicio.calcularPrimo(numCalcPrimo));
        return ResponseEntity.ok(primo);
    }

    @GetMapping("/calcularHipotenusa/{cateto1}/{cateto2}") //pasar los parámetros en el "path" de la URL. Para rescatar los valores proporcionados en el path usaremos @PathVariable
    public ResponseEntity<?> showHipot(@PathVariable Double cateto1, @PathVariable Double cateto2) {
        if (Double.isNaN(cateto1) || Double.isNaN(cateto2)) { //si alguno de esos valores NO es un número...
            return ResponseEntity.badRequest().build(); //devuelvo un error 400
        }
        if (cateto1 <= 0 || cateto2 <= 0) { //si alguno de esos números es menor o igual que cero...
            return ResponseEntity.badRequest().build(); //devuelvo un error 400
        }
        Hipotenusa hipotenusa = new Hipotenusa(cateto1, cateto2, calcularServicio.calcularHipotenusa(cateto1, cateto2));
        return ResponseEntity.ok(hipotenusa);
    }

    @GetMapping("/divisores/{numDiv}") 
    public ResponseEntity<?> showDivisores(@PathVariable Integer numDiv) {
        if (numDiv <= 0) { // si el número es menor que 0...
            return ResponseEntity.badRequest().build(); //devuelvo un error 400
        }
        Divisor divisor = new Divisor(numDiv, calcularServicio.calcularDivisores(numDiv));
        return ResponseEntity.ok(divisor);
    }

}
