package com.sofka.Turismo_DDD.domain.plandeviaje.values;

import co.com.sofka.domain.generic.Identity;

public class PlanDeViajeID extends Identity {
    public PlanDeViajeID() {
    }

    private PlanDeViajeID(String id) {
        super(id);
    }

    public static PlanDeViajeID of(String id) {
        return new PlanDeViajeID(id);
    }
}
