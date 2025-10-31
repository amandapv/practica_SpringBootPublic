package com.example.palmares_act_form.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.ui.Model;
import jakarta.validation.Valid;

// import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.palmares_act_form.Formulario;
import com.example.palmares_act_form.services.EmailService;
import com.example.palmares_act_form.services.FileStorageService;
import com.example.palmares_act_form.services.FormServiceImpl;


@Controller
public class FormController {
    
    @Autowired
    private FormServiceImpl formServiceImpl;
    @Autowired
    private FileStorageService fileStorageService;
    @Autowired
    private EmailService emailService;
    private String txtError;

    @GetMapping("/contacta")
    public String showForm(Formulario form, Model model) {
        
        if (txtError != null) {
            model.addAttribute("txtErr", txtError);
            txtError = null; // vacía la variable para usarla de nuevo
        }

        model.addAttribute("form", new Formulario()); 

        return "formView";
    }


    @PostMapping("/contacta/submit")
    public String postMethodName(@Valid @ModelAttribute("form") Formulario form, BindingResult bindingResult, @RequestParam MultipartFile file, Model model) {
        
        if (bindingResult.hasErrors()) {
            return "formView";
        }

        try {
            model.addAttribute("producto", formServiceImpl.producto(form)); //intento añadir el producto
            model.addAttribute("nombre", formServiceImpl.nombreMayus(form));
            
            String nuevoArchivo = fileStorageService.store(file, form.getDni()); //devuelve el nombre definitivo con el que se ha guardado. Lo podríamos guardar en algún objeto
            String rutaCompletaArchivo = fileStorageService.getAbsolutePath(nuevoArchivo); //obtener la ruta del archivo que vamos a adjuntar para el envío del correo al usuario
            
            System.out.println("Archivo: " + nuevoArchivo);
            System.out.println("Ruta completa: " + rutaCompletaArchivo);

            form.setArchivo(nuevoArchivo); //modifico el nombre del archivo
            
            String destinatario = form.getEmail();
            String asunto = "Formulario de contacto para el socio " + form.getNombre();
            String cuerpoMensaje = "Ha solicitado la compra del producto:" + form.getProducto() + " el socio " + form.getNombre() + " con DNI: " + form.getDni() + ". Le adjuntamos el archivo con su carnet de socio.";

            emailService.sendEmail(destinatario, asunto, cuerpoMensaje, rutaCompletaArchivo); //enviar el email - si no lo consigue se lanzará un MessagingException
            

        } catch (Exception e) {
            txtError = e.getMessage();
            return "redirect:/contacta";
        }
        
        model.addAttribute("formEnviado", "Formulario procesado correctamente");
        return "formView"; //retorno a la vista del formulario para que así en caso de que se envíe el formulario correctamente, no pierda los datos y los muestre en caso de que se envíe correctamente (lo hago porque la captura del ejercicio se muestra así)
    }
    
}
