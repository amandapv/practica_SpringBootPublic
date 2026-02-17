package com.example.myapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
    
    @GetMapping("/login")
    public String showLogin() {
        return "loginView";
    }

    @GetMapping("/logout")
    public String showLogout() {
        return "logoutView";
    }
}
