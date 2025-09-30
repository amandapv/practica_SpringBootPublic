package com.example.myapp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/app")

public class FotosController {
    @RequestMapping({"/", "/index", "home"})
    public String getList() {
        // proceso
        return "indexView";
    }

    @GetMapping("/palmares")
    public String palmares(Model model) {
        List<String> listaPalmares = new ArrayList<>(Arrays.asList("6ª EDICIÓN PREMIO SILOS DE RELATOS CORTOS", "CONCURSO LITERARIO: MACONDOS DEL SIGLO XXI 2025", "I PREMIO UNIR – CÁTEDRA VARGAS LLOSA DE RELATO BREVE"));
        // proceso
        model.addAttribute("listPalmares", listaPalmares);
        return "palmaresView";
    }

    @GetMapping("/galeria-fotos")
    public String galeriaFotos() {
        // proceso
        return "photogallery";
    }

    @GetMapping("/enlaces-externos")
    public String enlacesExternos() {
        // proceso
        return "linksView";
    }

}

