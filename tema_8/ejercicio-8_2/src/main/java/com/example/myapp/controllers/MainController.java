package com.example.myapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String showList() {
        return "redirect:/cuentas";
    }

    @GetMapping("/accessError")
    public String showAccessErrorPage() {
        return "accessErrorView";
    }
}
