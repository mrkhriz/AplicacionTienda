package com.personal.business.impl;

import com.personal.business.intefaz.ClienteBusiness;
import com.personal.dto.DtoCliente;
import com.personal.persistence.model.Client;
import com.personal.persistence.repo.ClientRepository;
import com.personal.util.impl.ImplUtilities;
import com.personal.util.interfaz.Utilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ImplClienteBusiness implements ClienteBusiness {

    private static Logger LOG = LoggerFactory.getLogger(ImplClienteBusiness.class);

    @Autowired
    ClientRepository clientRepository;

    Utilities utilities = new ImplUtilities();

    /*
    public DtoCliente consultarUsuario (String email){

        if (funciones.validarEmail(email)==0){
            ImplPostgreSQLJDBC conexion  = new ImplPostgreSQLJDBC();
            DtoCliente dtoClienteCons = conexion.consultarCliente(email);
            return dtoClienteCons;
        }
        return null;
    }

    public int crearUsuario (DtoCliente dtoCliente){

       if (this.funciones.validarDatos(dtoCliente) == 0){

           ImplPostgreSQLJDBC conexion  = new ImplPostgreSQLJDBC();
           DtoCliente dtoClienteCons = conexion.consultarCliente(dtoCliente.getEmail());

           if (dtoClienteCons == null || dtoClienteCons.getId() > 0 ){

               return 3;
           }

           dtoCliente.setPassword(funciones.cifrarMD5(dtoCliente.getPassword()));
           return conexion.insertarCliente(dtoCliente);
       }
       return 2;
   }

    public boolean loguearUsuario (DtoLogin dtoLogin){

        if (this.funciones.validarEmail(dtoLogin.getEmail()) == 0){

            PostgreSQLJDBC postgreSQLJDBC = new ImplPostgreSQLJDBC();
            DtoCliente dtoClienteLog = postgreSQLJDBC.consultarCliente(dtoLogin.getEmail());

            if ( dtoClienteLog.getPassword().equals(this.funciones.cifrarMD5(dtoLogin.getPassword()))
                    && dtoClienteLog.getEmail().equals(dtoLogin.getEmail())){
                System.out.println("Login Exitoso.");
                return true;
            }
        }
        System.out.println("Login No Exitoso.");
        return false;
    }*/


    public DtoCliente crearUsuario (DtoCliente dtoCliente){

        LOG.info("Crear Usuario");

        if (this.utilities.validarDatos(dtoCliente) == 0){

            Client cliente = new Client();
            System.out.println("dtoC: "+dtoCliente.getEmail());
            List<Client> listaCliente = clientRepository.findByEmail(dtoCliente.getEmail());

            if (!listaCliente.isEmpty()){

                return dtoCliente;
            }

            dtoCliente.setPassword(utilities.cifrarMD5(dtoCliente.getPassword()));
            cliente.setName(dtoCliente.getName());
            cliente.setEmail(dtoCliente.getEmail());
            cliente.setPassword(dtoCliente.getPassword());
            cliente = clientRepository.save(cliente);
            if (cliente.getId() != null && cliente.getId() > 0){
                dtoCliente.setId(cliente.getId());
                return dtoCliente;
            }
        }
        return dtoCliente;
    }
}
