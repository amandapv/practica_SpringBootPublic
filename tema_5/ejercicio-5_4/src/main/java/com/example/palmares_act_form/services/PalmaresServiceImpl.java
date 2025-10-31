package com.example.palmares_act_form.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.palmares_act_form.interfaces.PalmaresService;

@Service
public class PalmaresServiceImpl implements PalmaresService{
    
    public List<String> listPalmares() {

        List<String> listadoPalmares = new ArrayList<>(Arrays.asList("Segunda División", "Segunda División B", "Tercera División", "Trofeo Ciudad de Vigo", "Trofeo Colombino", "Trofeo Teresa Herrera"));

        //otra opción para añadir contenido al arraylist si en vez de tener "Arrays.asList(.........)" le digo .add al arraylist y ya
        //listadoPalmares.add(" Segunda División");

        return listadoPalmares;
    }
}