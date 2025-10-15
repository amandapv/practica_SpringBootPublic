package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class mainController {

    @GetMapping("/")
    public String saludo() {
        return "helloWorld"; //fichero helloWorld.html
    }
}
