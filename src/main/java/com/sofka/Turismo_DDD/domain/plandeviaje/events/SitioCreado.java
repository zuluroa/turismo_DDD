package com.sofka.Turismo_DDD.domain.plandeviaje.events;

import co.com.sofka.domain.generic.DomainEvent;
import com.sofka.Turismo_DDD.domain.plandeviaje.values.SitioID;
import com.sofka.Turismo_DDD.domain.plandeviaje.values.SitioPlanDeVIaje;

import java.util.Objects;

public class SitioCreado extends DomainEvent {
    private final SitioID sitioID;
    private final SitioPlanDeVIaje sitio;

    public SitioCreado(SitioID sitioID, SitioPlanDeVIaje sitio) {
        super("sofka.plandeviaje.sitiocreado");
        this.sitioID = Objects.requireNonNull(sitioID, "El ID de sitio no puede ser nulo");
        this.sitio = Objects.requireNonNull(sitio, "El sitio del plan de viaje no puede ser nulo");
    }

    public SitioID getSitioID() {
        return sitioID;
    }

    public SitioPlanDeVIaje getSitio() {
        return sitio;
    }
}
