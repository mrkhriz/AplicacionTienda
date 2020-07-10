package com.personal.util.interfaz;

import com.personal.dto.DtoCliente;
import com.personal.dto.DtoRta;

public interface Utilities {

    public DtoRta validarDatos(DtoCliente dtoCliente);

    public String cifrarMD5(String dato);

    public DtoRta validarEmail(String email);

}

