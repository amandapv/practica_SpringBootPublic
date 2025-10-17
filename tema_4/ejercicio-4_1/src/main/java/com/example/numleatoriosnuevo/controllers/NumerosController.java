package com.example.numleatoriosnuevo.controllers;

import java.util.LinkedHashSet;
import java.util.Random;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.numleatoriosnuevo.services.NumAleatoriosService;

//@SessionScope   --> en el tema siguiente veremos que es esto
@Controller
public class NumerosController {
    @Autowired
    private NumAleatoriosService numAleatoriosService;

    private String txtStatus = null;

    // Random random = new Random();
    // private Set<Integer> lista = new LinkedHashSet<>();

    @GetMapping({ "/", "/list", "" })
    public String showList(Model model) {
        model.addAttribute("cantidadTotal", numAleatoriosService.getList().size());
        model.addAttribute("listaNumeros", numAleatoriosService.getList());

        if (txtStatus != null) {
            model.addAttribute("txtErrores", txtStatus); //añadir al modelo la variable de error SI no está vacía
            txtStatus = null; //vaciar la variable para poder usarla de nuevo
        }
        return "listView";
    }


    @GetMapping("/new")
    public String showNew() {
        
        // boolean añadido;
        // do {
        //     añadido = lista.add(random.nextInt(100) + 1);
        // } while (!añadido);
        numAleatoriosService.addNum(); //la lista almacena el listado que retorna mi método showNew()
        return "redirect:/list";
    }


    @GetMapping("/delete/{id}")
    public String showDelete(@PathVariable String id) {
        Integer idLista;

        try {
            idLista = Integer.parseInt(id); //lo mejor es hacer una String previamente del dato que recibo, para poder parsear al tipo de dato que requiero y así comprobar siempre que el dato que estoy recibiendo sea del tipo correcto
            numAleatoriosService.deleteNum(idLista);
        } catch (Exception ex) {
            txtStatus = ex.getMessage();
        }
        
        // lista.remove(id);
        return "redirect:/list"; //redirect a la home /list
    }
}
