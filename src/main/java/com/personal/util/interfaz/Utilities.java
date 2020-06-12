package com.personal.util.interfaz;

import com.personal.dto.DtoCliente;

public interface Utilities {

    public int validarDatos(DtoCliente dtoCliente);

    public String cifrarMD5(String dato);

    public int validarEmail(String email);

}

