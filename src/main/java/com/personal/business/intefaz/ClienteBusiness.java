package com.personal.business.intefaz;

import com.personal.dto.DtoCliente;
import com.personal.dto.DtoLogin;
import com.personal.dto.DtoRta;
import org.springframework.stereotype.Component;

@Component
public interface ClienteBusiness {

    public DtoCliente consultarUsuario (String email);

    public DtoCliente crearUsuario (DtoCliente dtoCliente);

    public DtoRta loguearUsuario (DtoLogin dtoLogin);

}