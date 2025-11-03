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

    private String txtError = "";

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
        model.addAttribute("formInfo", new FormInfo());
        model.addAttribute("listaIntentos", masterMindService.masterMind.getListaIntentos());
        model.addAttribute("estadoJuego", masterMindService.masterMind.getEstadoJuego());

        // model.addAttribute("numIntRestantesssss", masterMindService.litIntentosRestantes());
        model.addAttribute("numIntRestantes", masterMindService.masterMind.getListaIntentos().size());
        return "juegoView";
    }

    @PostMapping("/juego")
    public String newTry(FormInfo formInfo) {
        masterMindService.comprobarIntento(formInfo.getIntento());
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
