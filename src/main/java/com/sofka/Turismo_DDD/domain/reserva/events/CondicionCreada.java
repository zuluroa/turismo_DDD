package com.sofka.Turismo_DDD.domain.reserva.events;

import co.com.sofka.domain.generic.DomainEvent;
import com.sofka.Turismo_DDD.domain.genericvalues.Observacion;
import com.sofka.Turismo_DDD.domain.reserva.values.CondicionID;

import java.util.Objects;

public class CondicionCreada extends DomainEvent {

    private final CondicionID condicionID;
    private final Observacion observacion;

    public CondicionCreada(CondicionID condicionID, Observacion observacion) {
        super("sofka.reserva.condicioncreada");
        this.condicionID = Objects.requireNonNull(condicionID, "El ID de la condicion no puede ser nula");
        this.observacion = Objects.requireNonNull(observacion, "La observacion no puede ser nula");
    }

    public CondicionID getCondicionID() {
        return condicionID;
    }

    public Observacion getObservacion() {
        return observacion;
    }
}
