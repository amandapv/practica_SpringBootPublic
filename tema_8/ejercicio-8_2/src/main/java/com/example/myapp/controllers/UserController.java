package com.example.myapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.myapp.domain.Usuario;
import com.example.myapp.services.UserService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/user")

public class UserController {
    
    @Autowired
    private UserService userService;

    @GetMapping({"/", ""})
    public String showList(Model  model) {
        model.addAttribute("listaUsuarios", userService.obtenerTodos());
        return "user/userListView";
    }

    @GetMapping("/new")
    public String showNew(Model model) {
        model.addAttribute("usuarioDto", new Usuario());
        return "user/userNewView";
    }

    @PostMapping("/new/submit")
    public String showNewSubmit(@Valid @ModelAttribute("usuarioDto") Usuario nuevoUsuario, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            userService.añadir(nuevoUsuario);
        }
        return "redirect:/user/";
    }

    @GetMapping("/edit/{id}")
    public String showEdit(@PathVariable long id, Model model) {
        Usuario usuario = userService.obtenerPorId(id);
        if (usuario != null) { //si existe el usuario (técnicamente podría hacer un ifExistidById y a correr en vez de obtenerPorId y después comprobar si existe o no, y hacerlo mejor en el service)
            model.addAttribute("usuarioDto", usuario);
            return "user/userEditView";
        }
        return "redirect:/user/";
    }

    @PostMapping("/edit/submit")
    public String showEditSubmit(@Valid @ModelAttribute("usuarioDto") Usuario usuario, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            // if (usuario != null) {
                userService.editar(usuario);
            // }
        }
        return "redirect:/user/";
    }

    @GetMapping("/delete/{id}")
    public String showDelete(@PathVariable long id) {
        userService.borrar(id);
        return "redirect:/user/";
    }
}