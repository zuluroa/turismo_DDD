package com.sofka.Turismo_DDD.domain.reserva.entity;

import co.com.sofka.domain.generic.Entity;
import com.sofka.Turismo_DDD.domain.reserva.values.CondicionID;
import com.sofka.Turismo_DDD.domain.genericvalues.Observacion;

import java.util.Objects;

public class Condicion extends Entity<CondicionID> {

    private Observacion observacion;

    public Condicion(CondicionID entityId,Observacion observacion) {
        super(entityId);
        Objects.requireNonNull(entityId, "El ID del condicion no puede ser nulo");
        this.observacion = Objects.requireNonNull(observacion,"La observacion no puede ser nula");
    }

    public void actualizarObservacion(Observacion observacion){
        this.observacion = Objects.requireNonNull(observacion,"La observacion no puede ser nula");
    }

    public Observacion getObservacion() {
        return observacion;
    }
}
