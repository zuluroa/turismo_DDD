package com.sofka.Turismo_DDD.domain.hospedaje.events;

import co.com.sofka.domain.generic.DomainEvent;
import com.sofka.Turismo_DDD.domain.hospedaje.entity.Localizacion;

import java.util.Objects;

public class LocalizacionCreada extends DomainEvent {
    private final Localizacion localizacion;

    public LocalizacionCreada(Localizacion localizacion) {
        super("sofka.hospedaje.localizacioncreada");
        this.localizacion = Objects.requireNonNull(localizacion, "La localizacion no puede ser nula");
    }

    public Localizacion getLocalizacionID() {
        return localizacion;
    }

}
