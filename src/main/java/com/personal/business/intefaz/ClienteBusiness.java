package com.personal.business.intefaz;

import com.personal.dto.DtoCliente;
import com.personal.dto.DtoLogin;
import org.springframework.stereotype.Component;

@Component
public interface ClienteBusiness {

    //public DtoCliente consultarUsuario (String email);

    public DtoCliente crearUsuario (DtoCliente dtoCliente);

    //public boolean loguearUsuario (DtoLogin dtoLogin);

}