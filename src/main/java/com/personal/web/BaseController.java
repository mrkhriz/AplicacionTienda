package com.personal.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class BaseController {
    @Value("${spring.application.name}")
    String nombreAplicacion;

    @Autowired
    ClientController clientController;

    /*
    @GetMapping("/home")
    public String inicioAplicacion (Model model){

        List client = clientController.findByEmail("mail@mail.com");
        model.addAttribute("nombreAplicacion", nombreAplicacion);
        model.addAttribute("clientes", client);
        return "home";

    }*/

}