package com.personal.web;

import com.personal.dto.DtoCliente;
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


    @GetMapping("/home")
    public String inicioAplicacion (Model model) {

        DtoCliente dtoCliente = clientController.getClient("mail@mail.com").getBody();
        model.addAttribute("nombreAplicacion", nombreAplicacion);
        model.addAttribute("clientes", dtoCliente);
        return "home";
    }

    @GetMapping("/createClient")
    public String createClient (Model model){

        model.addAttribute("client", new DtoCliente());
        return "create";

    }
}