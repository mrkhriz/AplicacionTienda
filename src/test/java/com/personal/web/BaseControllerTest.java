package com.personal.web;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class BaseControllerTest {

    @Test
    public void givenUnModelo_whenInicioAplicacion_thenRegresaHome(){

    }

    @BeforeClass
    public static void beforeClas(){
        System.out.println("Antes de la clase");
    }

    @Before
    public void beforeMethod(){
        System.out.println("Antes del método");
    }

    @AfterClass
    public static void afterClas(){
        System.out.println("Después de la clase");
    }

    @After
    public void afterMethod(){
        System.out.println("Después del método");
    }

}