package com.example.calculadora.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.calculadora.interfaces.CalculadoraService;

@Controller // anotación controlador
public class CalculadoraController {
    
    //PENDIENTE A REVISAR --- TIENE QUE PERMITIR METER DOS NUMEROS Y AHORA SOLO PERMITE UNO Y PASA AL SIGUIENTE
    @Autowired
    private CalculadoraService calculadoraService; //llamo a la interfaz
 

    @GetMapping({"/", "/home"})            // ruta a la que responde por GET
    public String showHome(Model model) {
        
        model.addAttribute("boleanoDigitoSumaPulsado", calculadoraService.getDigitoPulsado()); //lo parseo para poder evaluar que dígito está siendo pulsado y poner la clase focus css según corresponda hacer el foco

        model.addAttribute("numeroOperando1", calculadoraService.getOperando1()); //y parseo el operando 1 para manejarlo en la vista
        model.addAttribute("numeroOperando2", calculadoraService.getOperando2()); //y parseo el operando 2 para manejarlo en la vista 
        model.addAttribute("calcularSuma", calculadoraService.getCalcular()); //lo parseo para mostrarlo en la vista

        return "indexView";    // vista que devuelve
    }


    @GetMapping("/digito/{numero}")  
    public String showDigito(
        @PathVariable Integer numero, 
        Model model) {
        calculadoraService.cogerDigito(numero);

        model.addAttribute("numeroOperando1", calculadoraService.getOperando1()); //lo parseo para poder añadirlo a la vista
        model.addAttribute("numeroOperando2", calculadoraService.getOperando2()); //y parseo el operando 2 para manejarlo en la vista 
        //necesito ambos para no perder el primer número en caso de que si se haya pulsado el botón +

       return "redirect:/";
    }


    @GetMapping("/suma") //cuando se pulsa suma
    public String showSumar(Model model) {

        // if (calculadoraService.getOperando1() != null) { //si el número primero no está vacío... así si pulso el botón + no pasa al siguiente dígito
        calculadoraService.cambiarDigitoSuma(); //si se ha pulsado el más ponemos el buleano a true
            
            // model.addAttribute("numeroOperando1", calculadoraService.getOperando1()); //lo parseo para poder añadirlo a la vista y añado el operando 1
        // }
        return "redirect:/";
    }


    @GetMapping("/resta") //cuando se pulsa resta
    public String showRestar(Model model) {

        // if (calculadoraService.getOperando1() != null) { //si el número primero no está vacío... así si pulso el botón + no pasa al siguiente dígito
        calculadoraService.cambiarDigitoResta(); //si se ha pulsado el más ponemos el buleano a true
            
            // model.addAttribute("numeroOperando1", calculadoraService.getOperando1()); //lo parseo para poder añadirlo a la vista y añado el operando 1
        // }
        return "redirect:/";
    }


    @GetMapping("/igual")
    public String showCalcular(Model model) {

        // if (calculadoraService.getOperando1() != null && calculadoraService.getOperando2() != null ) { //si ambos dígitos NO están sin datos...
        calculadoraService.hacerOperacion(); //sumo ambos dígitos
        model.addAttribute("calcularSuma", calculadoraService.getCalcular()); //lo parseo para mostrarlo en la vista

        //vuelvo a meter ambos dígitos para no perder su representación
        model.addAttribute("numeroOperando1", calculadoraService.getOperando1()); 
        model.addAttribute("numeroOperando2", calculadoraService.getOperando2()); 
        // }
        
        return "redirect:/";
    }


    @GetMapping("/clear")
    public String showLimpiar(Model model) {

        //pongo los valores a null para que pueda al darle a Clear se borren los datos previos
        calculadoraService.limpiarCalculadora();
        return "redirect:/";
    }
    
    
}