package com.example.palmares_act_form.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.palmares_act_form.interfaces.PalmaresService;

@Controller
@RequestMapping("/app")

public class PalmaresController {

    @Autowired
    private PalmaresService palmaresService;

    @GetMapping("/palmares")
    public String palmares(Model model) {

        model.addAttribute("listPalmares", palmaresService.listPalmares());
        return "palmaresView";
    }

    @GetMapping("/galeria-fotos")
    public String galeriaFotos() {
        // proceso
        return "photogallery";
    }

}

