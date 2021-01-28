package com.example.listas.utulidades;

public class Utilidades {
    //************ TABLA COSAS ********************************************************************
    public static final String NOMBRE_TABLA = "cosas";

    public static final String CAMPO_NOMBRE="nombre";
    public static final String CAMPO_CANTIDAD="cantidad";
    public static final String CAMPO_PRECIO="precio";
    public static final String CAMPO_SUBTOTAL="subtotal";

    public static final String CREAR_TABLA =
           "CREATE TABLE "+NOMBRE_TABLA+"("+CAMPO_NOMBRE+" TEXT,"+CAMPO_CANTIDAD+" INT,"
                                +CAMPO_PRECIO+" FLOAT,"+CAMPO_SUBTOTAL+" FLOAT)";

   //**********************************************************************************************

    //******************* TABLA LISTAS ************************************************************
    public static final String NOMBRE_TABLA_LISTA = "listas";

    public static final String CAMPO_NOMBRE_LISTA ="nombre";
    public static final String CAMPO_COSA = "cosa";

    public static final String CREAR_TABLA_LISTA ="CREATE TABLE listas(nombre TEXT,cosa ARRAY)";

    //*********************************************************************************************
}
