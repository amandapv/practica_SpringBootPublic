package com.example.paises_csv.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.paises_csv.Pais;
import com.example.paises_csv.interfaces.ListPaisesService;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class HomeController {

    @Autowired
    // ListPaisesServiceImpl listPaisesServiceImpl;
    ListPaisesService listPaisesService; //implementamos la interfaz que tiene la definición de los servicios

    private String txtError;
    private Pais paisEncontrado;

    @GetMapping("/")
    public String showHome(Model model) {

        if (txtError != null) {
            model.addAttribute("txtError", txtError);
            // System.out.println(txtError);
            txtError = null;
        }

        if (paisEncontrado != null) { //si el pais existe, es decir, si encontré el nombre del pais que seleccionó el usuario (en el post relleno este pais mediante el método de mi servicio para buscarlo)
            model.addAttribute("capEncontrada", paisEncontrado.getCapital()); //añado al modelo el nombre de la capital de ese pais
            model.addAttribute("poblEncontrada", paisEncontrado.getPoblacion()); //añado al modelo el nombre de la población de ese pais

            paisEncontrado = null; //vacío la informaación del pais para poder seguir buscando más países y que no me muestre los datos del anterior
        }

        model.addAttribute("formPais", new Pais("", "", 0)); //le paso al modelo un objeto vacío de tipo Pais para poder usarla en el formulario. No puede estar rellenada, ya que el usuario todavía no ha rellenado la información (interactuado)

        try { 
            List<String> archivoCSV = listPaisesService.cargarPaisesDesdeFichero(); //guardo la ejecución del método anterior en una lista que será lo que me retorna este método con el fichero CSV metido en una lista de Strings. Lanza una excepción si no encuentra el archivo con el csv
            
            model.addAttribute("listadoPaises", listPaisesService.mostrarPaises(archivoCSV)); //guardo los paises que me he traído del csv en el modelo
        }
        catch (RuntimeException e) {
            txtError = e.getMessage(); //si no encuentra el archivo cvs me mostrará el error en cuestión
            model.addAttribute("txtError", txtError); //añado al modelo el error, ya que como redirijo a index necesito tener esa variable (en post no porque redirijo a "/" y pasa por la evaluación de la condición de si está a null la variable de error)
            return "indexView";
        }
        return "indexView";
    }


    @PostMapping("/submit")
    public String showDatosPais(Pais paisForm) {
        // System.out.println("Pais seleccionado: " + paisForm.getNomPais());
        // System.out.println("Pais seleccionado: " + formPais.getCapital());
        try {
            
            paisEncontrado = listPaisesService.buscarPaisDatos(paisForm.getNomPais()); // le asigno el pais encontrado a mi variable global de tipo Pais
            
            // List<String> archivoCSV = listPaisesServiceImpl.cargarPaisesDesdeFichero(); //guardo la ejecución del método anterior en una lista que será lo que me retorna este método con el fichero CSV metido en una lista de Strings
            // model.addAttribute("listadoPaises", listPaisesServiceImpl.mostrarPaises(archivoCSV));
        }
        catch (RuntimeException e) { //si no encuentra el pais, me mostrará el error en cuestión
            txtError = e.getMessage();
            return "redirect:/";
        }
        return "redirect:/";
    }

}