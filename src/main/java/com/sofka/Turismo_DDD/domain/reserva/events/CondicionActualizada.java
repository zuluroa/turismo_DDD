package com.sofka.Turismo_DDD.domain.reserva.events;

import co.com.sofka.domain.generic.DomainEvent;
import com.sofka.Turismo_DDD.domain.genericvalues.Observacion;
import com.sofka.Turismo_DDD.domain.reserva.values.CondicionID;
import com.sofka.Turismo_DDD.domain.utils.CompararVacioONulo;
import com.sofka.Turismo_DDD.domain.utils.mayorOIgual;

import java.util.Objects;

public class CondicionActualizada extends DomainEvent {
    private final CondicionID condicionID;
    private final Observacion observacion;

    public CondicionActualizada(CondicionID condicionID, Observacion observacion) {
        super("sofka.reserva.condicionactualizada");
        this.condicionID = Objects.requireNonNull(condicionID, "El ID de condicion no puede ser nula");
        this.observacion = Objects.requireNonNull(observacion, "La observacion no puede ser nula");
        if (CompararVacioONulo.compararSiNuloOEsVacio(observacion.value()))
            throw new IllegalArgumentException("La observacion no puede ser vacia");
        if (mayorOIgual.mayorOIgualQue(observacion.value().length(), 5))
            throw new IllegalArgumentException("La observacion debe tener más de 5 caracteres");
    }

    public CondicionID getCondicionID() {
        return condicionID;
    }

    public Observacion getObservacion() {
        return observacion;
    }
}
