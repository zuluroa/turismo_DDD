package com.sofka.Turismo_DDD.domain.hospedaje.values;

import co.com.sofka.domain.generic.Identity;

public class ServicioID extends Identity {
    public ServicioID() {
    }

    private ServicioID(String id) {
        super(id);
    }

    public static ServicioID of(String id) {
        return new ServicioID(id);
    }
}
