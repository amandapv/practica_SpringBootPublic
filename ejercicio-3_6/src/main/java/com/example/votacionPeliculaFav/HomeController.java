package com.example.votacionPeliculaFav;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller // anotación controlador
public class HomeController {
    

    @GetMapping({"/", "/home"})            // ruta a la que responde por GET
    public String showHome( ) {
               
        return "indexView";    // vista que devuelve
    }

    List<Integer> listadoFotos = new ArrayList<>(Arrays.asList(0, 0, 0)); //inicializo un array que almacenará la votación de cada foto. Lo pongo fuera del siguiente método para que no se inicialice a 0 cada vez que vaya a la votación 


    @GetMapping("/voto")  
    public String votacion(
        @RequestParam(required = false) Integer foto,
        Model model) {
        
        listadoFotos.set(foto, listadoFotos.get(foto) + 1); //cada vez que pinchan el enlace de cada foto, de la posición que tenía la foto que estoy seleccionando, le sumo un voto

        model.addAttribute("listaFotos", listadoFotos); //parseo para poder manejarlo en la vista
       
        // System.out.println(listadoFotos.get(foto));        
        return "indexView";
    }
    
}