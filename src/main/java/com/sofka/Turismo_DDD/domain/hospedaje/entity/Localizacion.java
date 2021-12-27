package com.sofka.Turismo_DDD.domain.hospedaje.entity;

import co.com.sofka.domain.generic.Entity;
import com.sofka.Turismo_DDD.domain.hospedaje.values.Ciudad;
import com.sofka.Turismo_DDD.domain.hospedaje.values.LocalizacionID;

import java.util.Objects;

public class Localizacion extends Entity<LocalizacionID> {

    private final LocalizacionID entityId;
    private  Ciudad ciudad;

    public Localizacion(LocalizacionID entityId, Ciudad ciudad) {
        super(entityId);
        this.entityId = Objects.requireNonNull(entityId, "El ID de localizacion no puede ser nulo");
        this.ciudad = Objects.requireNonNull(ciudad, "Ciudad no puede ser nulo");
    }

    public void actualizarCiudad(Ciudad ciudad){
        this.ciudad = Objects.requireNonNull(ciudad, "Ciudad no puede ser nulo");
    }

    public LocalizacionID getEntityId() {
        return entityId;
    }

    public Ciudad getCiudad() {
        return ciudad;
    }
}
