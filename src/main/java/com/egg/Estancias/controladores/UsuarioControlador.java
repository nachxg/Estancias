package com.egg.Estancias.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.egg.Estancias.excepciones.MiExcepcion;
import com.egg.Estancias.servicios.UsuarioServicio;

@Controller
@RequestMapping("/usuario")
public class UsuarioControlador {

    @Autowired
    private UsuarioServicio usuarioServicio;

    @GetMapping("/registrarse")
    public String registrarse() {
        return "registro.html";
    }

    @PostMapping("/registro")
    public String registro(@RequestParam String botonRegistro, @RequestParam String username, @RequestParam String email, @RequestParam String password,
            @RequestParam String password2, ModelMap modelo) {

        try {

            usuarioServicio.crearUsuario(username, email, password, password2);
            modelo.put("exito", "Usuario registrado correctamente.");

            if ("cliente".equals(botonRegistro)) {
                return "registroCliente.html";
            } else {
                return "registroFamilia.html";
            }

        } catch (MiExcepcion e) {

            modelo.put("error", e.getMessage());
            return "redirect:/";
        }

        

    }

}
