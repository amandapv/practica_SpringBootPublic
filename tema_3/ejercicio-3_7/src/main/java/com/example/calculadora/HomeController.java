package com.example.calculadora;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller // anotación controlador
public class HomeController {
    
    //PENDIENTE A REVISAR --- TIENE QUE PERMITIR METER DOS NUMEROS Y AHORA SOLO PERMITE UNO Y PASA AL SIGUIENTE
    
    boolean sumaPulsado = false;
    Long operando1;
    Long operando2;
    Long calcular;

    @GetMapping({"/", "/home"})            // ruta a la que responde por GET
    public String showHome(Model model) {
        
        model.addAttribute("boleanoDigitoSumaPulsado", sumaPulsado); //lo parseo para poder evaluar que dígito está siendo pulsado y poner la clase focus css según corresponda hacer el foco

        model.addAttribute("numeroOperando1", operando1); //y parseo el operando 1 para manejarlo en la vista
        model.addAttribute("numeroOperando2", operando2); //y parseo el operando 2 para manejarlo en la vista 
        model.addAttribute("calcularSuma", calcular); //lo parseo para mostrarlo en la vista

        return "indexView";    // vista que devuelve
    }


    @GetMapping("/digito/{numero}")  
    public String cogerDigito(
        @PathVariable Long numero, 
        Model model) {

        if(sumaPulsado == false) { //si no ha sido pulsado el botón...
            operando1 = numero; //le asigno a la variable operando1 el dígito que se ha seleccionado, para poder meter en la vista el número que se quiera introducir a cada operando
        
            model.addAttribute("numeroOperando1", operando1); //lo parseo para poder añadirlo a la vista
        
        } else { //si ha sido pulsado el botón...
            operando2 = numero; //asigno ahora al operando2 el nuevo dígito introducido por teclado 

            model.addAttribute("numeroOperando1", operando1); //y parseo el operando 1 para manejarlo en la vista
            model.addAttribute("numeroOperando2", operando2); //y parseo el operando 2 para manejarlo en la vista 
            //necesito ambos para no perder el primer número en caso de que si se haya pulsado el botón +
        }

       return "redirect:/";
    }


    @GetMapping("/suma") //cuando se pulsa suma
    public String sumar(Model model) {

        if (operando1 != null) { //si el número primero no está vacío... así si pulso el botón + no pasa al siguiente dígito
            sumaPulsado = true; //si se ha pulsado el más ponemos el buleano a true
            
            model.addAttribute("numeroOperando1", operando1); //lo parseo para poder añadirlo a la vista y añado el operando 1
        }
        
        return "redirect:/";
    }


    @GetMapping("/igual")
    public String calcular(Model model) {

        if (operando1 != null && operando2 != null ) { //si ambos dígitos NO están sin datos...
            calcular = operando1 + operando2; //sumo ambos dígitos
            model.addAttribute("calcularSuma", calcular); //lo parseo para mostrarlo en la vista

            //vuelvo a meter ambos dígitos para no perder su representación
            model.addAttribute("numeroOperando1", operando1); 
            model.addAttribute("numeroOperando2", operando2); 
        }
        
        return "redirect:/";
    }


    @GetMapping("/clear")
    public String limpiar(Model model) {

        //pongo los valores a null para que pueda al darle a Clear se borren los datos previos
        operando1 = null;
        operando2 = null;
        calcular = null;

        //pongo el valor del boleano pulsado a false para que no piense que ya ha sido pulsado
        sumaPulsado = false;

        return "redirect:/";
    }
    
    
}