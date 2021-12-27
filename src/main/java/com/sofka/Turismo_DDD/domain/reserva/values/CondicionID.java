package com.sofka.Turismo_DDD.domain.reserva.values;

import co.com.sofka.domain.generic.Identity;

public class CondicionID extends Identity {
    public CondicionID() {
    }

    private CondicionID(String id) {
        super(id);
    }

    public static CondicionID of(String id) {
        return new CondicionID(id);
    }
}
