package com.sofka.Turismo_DDD.domain.hospedaje.values;

import co.com.sofka.domain.generic.Identity;

public class HospedajeID extends Identity {
    public HospedajeID() {
    }

    private HospedajeID(String id){
        super(id);
    }

    public static HospedajeID of(String id){
        return new HospedajeID(id);
    }
}
