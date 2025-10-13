package com.example.numleatorios;

import java.util.LinkedHashSet;
import java.util.Random;
import java.util.Set;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//@SessionScope   --> en el tema siguiente veremos que es esto
@Controller
public class NumerosController {
    Random random = new Random();
    private Set<Integer> lista = new LinkedHashSet<>();

    @GetMapping({ "/", "/list", "" })
    public String showList(Model model) {
        model.addAttribute("cantidadTotal", lista.size());
        model.addAttribute("listaNumeros", lista);
        return "listView";
    }

    @GetMapping("/new")
    public String showNew() {
        boolean añadido;
        do {
            añadido = lista.add(random.nextInt(100) + 1);
        } while (!añadido);
        return "redirect:/list";
    }

    @GetMapping("/delete/{id}")
    public String showDelete(@PathVariable Integer id) {
        lista.remove(id);
        return "redirect:/list";
    }
}
