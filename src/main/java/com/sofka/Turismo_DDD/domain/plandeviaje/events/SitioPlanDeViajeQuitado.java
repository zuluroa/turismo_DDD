package com.sofka.Turismo_DDD.domain.plandeviaje.events;

import co.com.sofka.domain.generic.DomainEvent;
import com.sofka.Turismo_DDD.domain.plandeviaje.values.SitioID;

import java.util.Objects;

public class SitioPlanDeViajeQuitado extends DomainEvent {
    private final SitioID sitioID;

    public SitioPlanDeViajeQuitado(SitioID sitioID) {
        super("sofka.plandeviaje.sitioplandeviajequitado");
        this.sitioID = Objects.requireNonNull(sitioID, "El ID del sitio no puede ser nulo");
    }

    public SitioID getSitioID() {
        return sitioID;
    }
}
