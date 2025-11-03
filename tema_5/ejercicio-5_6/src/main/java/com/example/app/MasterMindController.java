package com.example.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.app.model.FormInfo;

@Controller
@Scope("session")
public class MasterMindController {

    @Autowired
    public MasterMindService masterMindService;

    private String txtError;
    private String txtErroresIntentos;


    @GetMapping("/")
    public String showHome(Model model) {
        if (txtError != null) {
            model.addAttribute("txtError", txtError);
            txtError = null;
        }
        return "indexView";
    }


    @GetMapping("/juego")
    public String showGame(Model model) {

        if (masterMindService.masterMind == null) { //si no empezó el juego...
            return "redirect:/"; //redirigir a /
        }

        if (txtErroresIntentos != null) { //si el método no retorna null, es decir, ha retornado algún error
            model.addAttribute("txtErroresIntentos", txtErroresIntentos);
            txtErroresIntentos = null;
        }

        model.addAttribute("formInfo", new FormInfo());
        model.addAttribute("masterMind", masterMindService.masterMind);
        model.addAttribute("listaIntentos", masterMindService.masterMind.getListaIntentos());
        model.addAttribute("estadoJuego", masterMindService.masterMind.getEstadoJuego());

        // model.addAttribute("numIntRestantesssss", masterMindService.litIntentosRestantes());
        model.addAttribute("numIntRestantes", masterMindService.masterMind.getListaIntentos().size());
        return "juegoView";
    }


    @PostMapping("/juego")
    public String newTry(FormInfo formInfo) {
        txtErroresIntentos = masterMindService.comprobarIntento(formInfo.getIntento()); //llamo al método comprobarIntento() y veo si hay errores en ese método para almacenarlo en la variable txtErroresIntentos
        return "redirect:/juego";
    }


    @GetMapping("/nuevoJuego")
    public String newGame(@RequestParam(required = false) String numInt, @RequestParam(required = false) String tamanoNum, Model model) {
        try { //intento...
            masterMindService.nuevoJuego(tamanoNum); //lanzar el juego
            // System.out.println(tamanoNum);
            masterMindService.numeroIntentosJuego(numInt); //modificar el número de intentos que me introduce el usuario

        } catch (RuntimeException e) { //si no lo consigo...
            txtError = e.getMessage(); //guardo el mensaje de error en una variable
            return "redirect:/"; //redirijo a /
        }
        
        return "redirect:/juego"; //si ha funcionado redirige a /juego
    }
}
