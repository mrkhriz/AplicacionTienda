package com.personal.business.impl;

import com.personal.business.intefaz.ClienteBusiness;
import com.personal.dto.DtoChangePass;
import com.personal.dto.DtoCliente;
import com.personal.dto.DtoLogin;
import com.personal.dto.DtoRta;
import com.personal.persistence.model.Client;
import com.personal.persistence.repo.ClientRepository;
import com.personal.util.impl.ImplUtilities;
import com.personal.util.interfaz.Utilities;
import com.personal.web.exception.ClientException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.xml.ws.http.HTTPException;
import java.util.List;

@Component
public class ImplClienteBusiness implements ClienteBusiness {

    private static Logger LOG = LoggerFactory.getLogger(ImplClienteBusiness.class);

    @Autowired
    private ClientRepository clientRepository;

    Utilities utilities = new ImplUtilities();


    public DtoCliente consultarUsuario (String email){

        DtoCliente dtoCliente = new DtoCliente();
        List<Client> listaCliente = clientRepository.findByEmail(email);

        if (listaCliente.isEmpty()){

            throw new ClientException(HttpStatus.NOT_FOUND,"Cliente no encontrado.");
        }

        for (Client client : listaCliente){

            dtoCliente.setId(client.getId());
            dtoCliente.setName(client.getName());
            dtoCliente.setEmail(client.getEmail());
            dtoCliente.setPassword(client.getPassword());

        }

        return dtoCliente;
    }

    public DtoRta loguearUsuario (DtoLogin dtoLogin){

        DtoRta dtoRta = new DtoRta();

        if (this.utilities.validarEmail(dtoLogin.getEmail()).getCodigo() == 0){

            DtoCliente dtoCliente = this.consultarUsuario(dtoLogin.getEmail());

            if ( this.utilities.cifrarMD5(dtoLogin.getPassword()).equals(dtoCliente.getPassword())
                    && dtoLogin.getEmail().equals(dtoCliente.getEmail())){

                return null;

            }
        }
        throw new RuntimeException("Login no exitoso") ;
        //throw new ClientException(new HTTPException(HttpStatus.UNAUTHORIZED.value()),"Login No Exitoso.");

        //dtoRta.setCodigo(1);
        //dtoRta.setDescripcion("Login No Exitoso.");
        //return dtoRta;
    }


    public DtoCliente crearUsuario (DtoCliente dtoCliente){

        LOG.info("Crear Usuario");

        if (this.utilities.validarDatos(dtoCliente).getCodigo() == 0){

            Client cliente = new Client();
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

    public DtoRta cambiarPassword(DtoChangePass dtoChangePass){

        DtoRta dtoRta = new DtoRta();

        if (this.utilities.validarEmail(dtoChangePass.getEmail()).getCodigo() == 0){

            DtoCliente dtoCliente = this.consultarUsuario(dtoChangePass.getEmail());
            String passwordCifrado = this.utilities.cifrarMD5(dtoChangePass.getPassword());
            String newPasswordCifrado= this.utilities.cifrarMD5(dtoChangePass.getNewPassword());

            if ( dtoChangePass.getEmail().equals(dtoCliente.getEmail())
                && passwordCifrado.equals(dtoCliente.getPassword())){

                if(newPasswordCifrado.equals(dtoCliente.getPassword())){

                    dtoRta.setCodigo(3);
                    dtoRta.setDescripcion("El nuevo password no puede ser igual al anterior.");

                    return dtoRta;

                }

                Client cliente = new Client();
                cliente.setId(dtoCliente.getId());
                cliente.setName(dtoCliente.getName());
                cliente.setEmail(dtoCliente.getEmail());
                cliente.setPassword(newPasswordCifrado);
                clientRepository.save(cliente);
                dtoRta.setCodigo(0);
                dtoRta.setDescripcion("Cambio password exitoso.");

                return dtoRta;

            } else {

                dtoRta.setCodigo(4);
                dtoRta.setDescripcion("Usuario o clave inválidos.");

                return dtoRta;

            }
        }

        dtoRta.setCodigo(5);
        dtoRta.setDescripcion("Usuario o clave inválidos.");

        return dtoRta;
    }
}