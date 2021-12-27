package com.sofka.Turismo_DDD.domain.reserva.values;

import co.com.sofka.domain.generic.Identity;

public class ReservaID extends Identity {
    public ReservaID() {
    }

    private ReservaID(String id){
        super(id);
    }

    public static ReservaID of(String id){
        return new ReservaID(id);
    }
}
