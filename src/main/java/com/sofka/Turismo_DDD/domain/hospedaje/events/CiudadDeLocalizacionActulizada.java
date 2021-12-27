package com.sofka.Turismo_DDD.domain.hospedaje.events;

import co.com.sofka.domain.generic.DomainEvent;
import com.sofka.Turismo_DDD.domain.hospedaje.entity.Localizacion;
import com.sofka.Turismo_DDD.domain.hospedaje.values.Ciudad;

import java.util.Objects;

public class CiudadDeLocalizacionActulizada extends DomainEvent {
    private final Localizacion localizacion;
    private final Ciudad ciudad;

    public CiudadDeLocalizacionActulizada(Localizacion localizacion, Ciudad ciudad) {
        super("sofka.hospedaje.ciudaddelocalizacionactulizada");
        this.localizacion = Objects.requireNonNull(localizacion, "La localizacion no puede ser nula");
        this.ciudad = Objects.requireNonNull(ciudad, "La ciudad no puede ser nula");
    }

    public Localizacion getLocalizacion() {
        return localizacion;
    }

    public Ciudad getCiudad() {
        return ciudad;
    }
}
