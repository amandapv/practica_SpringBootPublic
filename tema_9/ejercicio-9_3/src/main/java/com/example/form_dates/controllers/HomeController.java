package com.example.form_dates.controllers;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.example.form_dates.Formulario;
import com.example.form_dates.interfaces.CalcFechaService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController // anotación controlador
public class HomeController {

    @Autowired
    private CalcFechaService calcFechaService;
    
    @PostMapping("/calcularDias")
    public ResponseEntity<?> postMethodName(@Valid @RequestBody Formulario form, String valorBoton) {

        Map<String, String> mapa = new LinkedHashMap<>();
        mapa.put("fechaInicial", form.getFecha1().toString());
        mapa.put("fechaInicial", form.getFecha1().toString());
        mapa.put("cantidadDias", calcFechaService.calcularDias(form.getFecha1(), form.getFecha2()) + ""); //le sumo una cadena vacía para que me deje convertir un long a una string 
        return ResponseEntity.ok(mapa);
    }
    

}