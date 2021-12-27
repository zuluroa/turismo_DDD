package com.sofka.Turismo_DDD.domain.utils;

public class mayorOIgual {
    public static Boolean mayorOIgualQue(int longitud, int comparador) {
        if (longitud < comparador) return true;
        return false;
    }
}
