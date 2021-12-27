package com.sofka.Turismo_DDD.domain.hospedaje.entity;

import co.com.sofka.domain.generic.Entity;
import com.sofka.Turismo_DDD.domain.hospedaje.values.ServicioID;
import com.sofka.Turismo_DDD.domain.hospedaje.values.TipoDeServicio;

import java.util.Objects;

public class Servicio extends Entity<ServicioID> {
    private final ServicioID entityId;
    private TipoDeServicio tipoDeServicio;

    public Servicio(ServicioID entityId, TipoDeServicio tipoDeServicio) {
        super(entityId);
        this.entityId = Objects.requireNonNull(entityId, "El ID de servicio no puede ser nulo");
        this.tipoDeServicio = Objects.requireNonNull(tipoDeServicio, "El tipo de servicio no puede ser nulo");
    }

    public void ActualizarTipoDeServicio(TipoDeServicio tipoDeServicio){
        this.tipoDeServicio = Objects.requireNonNull(tipoDeServicio, "El tipo de servicio no puede ser nulo");
    }

    public ServicioID getEntityId() {
        return entityId;
    }

    public TipoDeServicio getTipoDeServicio() {
        return tipoDeServicio;
    }
}
