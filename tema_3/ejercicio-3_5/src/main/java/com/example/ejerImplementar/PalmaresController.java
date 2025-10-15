package com.example.ejerImplementar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/app")

public class PalmaresController {
    @RequestMapping({"/", "/index", "/home"})
    public String getList() {
        // proceso
        return "indexView";
    }

    @GetMapping("/palmares")
    public String palmares(Model model) {
        List<String> listaPalmares = new ArrayList<>(Arrays.asList("Segunda División", "Segunda División B", "Tercera División", "Trofeo Ciudad de Vigo", "Trofeo Colombino", "Trofeo Teresa Herrera"));
        // proceso
        model.addAttribute("listPalmares", listaPalmares);
        return "palmaresView";
    }

    @GetMapping("/galeria-fotos")
    public String galeriaFotos() {
        // proceso
        return "photogallery";
    }

}

