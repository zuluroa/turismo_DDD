package com.sofka.Turismo_DDD.domain.utils;

public class CompararVacioONulo {
    public static Boolean compararSiNuloOEsVacio(String valor){
        if(valor == null || valor.isBlank()) return true;
        return false;
    }
}
