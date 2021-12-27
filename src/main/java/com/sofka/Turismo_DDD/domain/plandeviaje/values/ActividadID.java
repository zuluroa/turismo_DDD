package com.sofka.Turismo_DDD.domain.plandeviaje.values;

import co.com.sofka.domain.generic.Identity;

public class ActividadID extends Identity {
    public ActividadID() {
    }

    private ActividadID(String id) {
        super(id);
    }

    public static ActividadID of(String id) {
        return new ActividadID(id);
    }
}
