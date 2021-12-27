package com.sofka.Turismo_DDD.domain.plandeviaje.values;

import co.com.sofka.domain.generic.Identity;

public class FechaPlanDeViajeID extends Identity {
    public FechaPlanDeViajeID() {
    }

    private FechaPlanDeViajeID(String id){
        super(id);
    }

    public static FechaPlanDeViajeID of(String id){
        return new FechaPlanDeViajeID(id);
    }
}
