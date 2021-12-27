package com.sofka.Turismo_DDD.domain.reserva.events;

import co.com.sofka.domain.generic.DomainEvent;
import com.sofka.Turismo_DDD.domain.reserva.entity.Vendedor;
import com.sofka.Turismo_DDD.domain.reserva.values.Fecha;

import java.util.Objects;

public class ReservaCreada extends DomainEvent {
    private final Vendedor vendedor;
    private final Fecha fecha;

    public ReservaCreada(Vendedor vendedor, Fecha fecha) {
        super("sofka.reserva.fechaactualizada");
        this.vendedor = Objects.requireNonNull(vendedor, "El vendedor no puede ser nulo");
        this.fecha = Objects.requireNonNull(fecha, "La fecha no puede ser nula");
    }

    public Vendedor getVendedor() {
        return vendedor;
    }

    public Fecha getFecha() {
        return fecha;
    }
}
