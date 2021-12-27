package com.sofka.Turismo_DDD.domain.reserva.values;

import co.com.sofka.domain.generic.Identity;

public class ClienteID extends Identity {
    public ClienteID() {
    }

    private ClienteID(String id){
        super(id);
    }

    public static ClienteID of(String id){
        return new ClienteID(id);
    }
}
