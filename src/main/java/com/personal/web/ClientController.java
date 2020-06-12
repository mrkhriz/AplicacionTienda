package com.personal.web;

import com.personal.business.impl.ImplClienteBusiness;
import com.personal.business.intefaz.ClienteBusiness;
import com.personal.dto.DtoCliente;
import com.personal.persistence.model.Client;
import com.personal.persistence.repo.ClientRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.RequestScope;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

    private static Logger LOG = LoggerFactory.getLogger(ClientController.class);

    /*
    @GetMapping("/client/{email}")
    public List findByEmail(@PathVariable String email){

        return clientRepository.findByEmail(email);

    }*/


    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public DtoCliente create(@RequestBody DtoCliente dtoCliente){
        LOG.info("POST---");

        ClienteBusiness clienteBusiness = new ImplClienteBusiness();
        return clienteBusiness.crearUsuario(dtoCliente);

    }

}