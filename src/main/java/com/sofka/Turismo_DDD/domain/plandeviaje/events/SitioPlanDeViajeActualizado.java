package com.sofka.Turismo_DDD.domain.plandeviaje.events;

import co.com.sofka.domain.generic.DomainEvent;
import com.sofka.Turismo_DDD.domain.plandeviaje.entity.Sitio;
import com.sofka.Turismo_DDD.domain.plandeviaje.values.SitioPlanDeVIaje;

import java.util.Objects;

public class SitioPlanDeViajeActualizado extends DomainEvent {
    private final Sitio sitio;
    private final SitioPlanDeVIaje sitioPlanDeVIaje;

    public SitioPlanDeViajeActualizado(Sitio sitio, SitioPlanDeVIaje sitioPlanDeVIaje) {
        super("sofka.plandeviaje.sitioplandeviajeactualizado");
        this.sitio = Objects.requireNonNull(sitio, "El  sitio no puede ser nulo");
        this.sitioPlanDeVIaje = Objects.requireNonNull(sitioPlanDeVIaje, "El sitio del plan de viaje no puede ser nulo");
    }

    public Sitio getSitio() {
        return sitio;
    }

    public SitioPlanDeVIaje getSitioPlanDeVIaje() {
        return sitioPlanDeVIaje;
    }
}
