package com.personal.util.impl;

import com.personal.dto.DtoCliente;
import com.personal.util.interfaz.Utilities;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class ImplUtilities implements Utilities {

    public int validarDatos(DtoCliente dtoCliente) {


        if (validarEmail(dtoCliente.getEmail()) == 1) {

            return 1;

        } else if (dtoCliente.getPassword().length() > 6) {

            System.out.println("Password inconsistente.");
            return 1;

        } else {

            for (int i = 0; i < dtoCliente.getName().length(); i++) {
                char caracter = dtoCliente.getName().toUpperCase().charAt(i);
                int valorASCII = (int) caracter;
                if (valorASCII < 65 || valorASCII > 90)
                    return 1;
            }
        }
        return 0;
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

    public int validarEmail (String email){

        String regexMail = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";

        if (!email.matches(regexMail)) {

            System.out.println("Email inconsistente.");
            return 1;

        }
        return 0;
    }

}
