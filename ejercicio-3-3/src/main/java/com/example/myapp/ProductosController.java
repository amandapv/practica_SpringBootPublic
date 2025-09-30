package com.example.myapp;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/app") //opcional - afecta a todo lo que esta debajo, es decir que será /app/¨lo que sea¨ - si no se pone sería lo mismo sin /app
public class ProductosController {
    @GetMapping("/productos")
    public String getList() {
        // proceso
        return "productoListView";
    }

    @GetMapping("/eliminar/{id}")
    public String removeItem(@PathVariable Long id) { //este metodo no se volverá a llamar en ningún lado, porque se maneja a través de las rutas - hay que decirle que la variable es de tipo path
        // proceso
        return "productoDeleteView";
    }

    @GetMapping("/nuevo")
    public String newItem() {
        // proceso
        return "productoNewView";
    }
}