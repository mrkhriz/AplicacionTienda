package com.personal.web;

import com.personal.business.impl.ImplClienteBusiness;
import com.personal.business.intefaz.ClienteBusiness;
import com.personal.dto.DtoChangePass;
import com.personal.dto.DtoCliente;
import com.personal.dto.DtoLogin;
import com.personal.dto.DtoRta;
import com.personal.persistence.model.Client;
import com.personal.persistence.repo.ClientRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Path;
import java.util.List;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

    private static Logger LOG = LoggerFactory.getLogger(ClientController.class);

    @Autowired
    private ImplClienteBusiness clienteBusiness;


    @GetMapping("/{email}")
    public ResponseEntity<DtoCliente> getClient(@PathVariable String email){

        DtoCliente dtoCliente = clienteBusiness.consultarUsuario(email);

        return ResponseEntity.ok().body(dtoCliente);
    }

    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE)
    public DtoCliente create(@RequestBody DtoCliente dtoCliente){

        return clienteBusiness.crearUsuario(dtoCliente);
    }

    @PostMapping(value = "/createWeb")
    public DtoCliente createFromModel(@ModelAttribute DtoCliente dtoCliente){

        return clienteBusiness.crearUsuario(dtoCliente);
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginClient(@RequestBody DtoLogin dtoLogin){

        clienteBusiness.loguearUsuario(dtoLogin);

        return ResponseEntity.ok().build();
    }

    @PutMapping("/changePassword")
    public DtoRta  changePassword(@RequestBody DtoChangePass dtoChangePass){

        return clienteBusiness.cambiarPassword(dtoChangePass);
    }
}