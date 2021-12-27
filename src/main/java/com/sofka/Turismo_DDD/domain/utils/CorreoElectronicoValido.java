package com.sofka.Turismo_DDD.domain.utils;

import java.util.regex.Pattern;

public class CorreoElectronicoValido {
    public static Boolean emailValido(String email){
        Pattern pattern = Pattern
                .compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        if(!pattern.matcher(email).find()) return true;

        return false;
    }
}
