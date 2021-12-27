package com.sofka.Turismo_DDD.domain.reserva.values;

import co.com.sofka.domain.generic.Identity;

public class VendedorID extends Identity {
    public VendedorID() {
    }

    private VendedorID(String id) {
        super(id);
    }

    public static VendedorID of(String id) {
        return new VendedorID(id);
    }
}
