package com.personal.util.impl;

import com.personal.dto.DtoCliente;
import com.personal.dto.DtoRta;
import com.personal.util.interfaz.Utilities;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class ImplUtilities implements Utilities {

    public DtoRta validarDatos(DtoCliente dtoCliente) {

        DtoRta dtoRta = new DtoRta();


        if (validarEmail(dtoCliente.getEmail()).getCodigo() == 0) {

            dtoRta.setCodigo(6);
            dtoRta.setDescripcion("Email inválido.");

            return dtoRta;

        } else if (dtoCliente.getPassword().length() > 6) {

            dtoRta.setCodigo(7);
            dtoRta.setDescripcion("Password inválido");

            return dtoRta;

        } else {

            for (int i = 0; i < dtoCliente.getName().length(); i++) {
                char caracter = dtoCliente.getName().toUpperCase().charAt(i);
                int valorASCII = (int) caracter;
                if (valorASCII < 65 || valorASCII > 90)

                    dtoRta.setCodigo(0);
                    dtoRta.setDescripcion("Nombre inválido.");

                return dtoRta;
            }
        }
        dtoRta.setCodigo(0);
        dtoRta.setDescripcion("Validación datos exitosa.");

        return dtoRta;
    }

    public String cifrarMD5(String dato) {

        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(dato.getBytes());
            BigInteger number = new BigInteger(1, messageDigest);
            String hashtext = number.toString(16);

            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public DtoRta validarEmail (String email){

        DtoRta dtoRta = new DtoRta();

        String regexMail = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";

        if (!email.matches(regexMail)) {

            dtoRta.setCodigo(1);
            dtoRta.setDescripcion("Email inválido.");

            return dtoRta;

        }
        dtoRta.setCodigo(0);
        dtoRta.setDescripcion("Email válido.");

        return dtoRta;
    }

}
