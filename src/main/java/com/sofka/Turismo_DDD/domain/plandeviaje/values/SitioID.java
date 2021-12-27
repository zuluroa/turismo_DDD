package com.sofka.Turismo_DDD.domain.plandeviaje.values;

import co.com.sofka.domain.generic.Identity;

public class SitioID extends Identity {
    public SitioID() {
    }

    private SitioID(String id){
        super(id);
    }

    public static SitioID of(String id){
        return new SitioID(id);
    }
}
