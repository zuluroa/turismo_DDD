package com.sofka.Turismo_DDD.domain.reserva.events;

import co.com.sofka.domain.generic.DomainEvent;
import com.sofka.Turismo_DDD.domain.reserva.Reserva;
import com.sofka.Turismo_DDD.domain.reserva.values.Fecha;

import java.util.Objects;

public class FechaActualizada extends DomainEvent {
    private final Reserva reserva;
    private final Fecha fecha;

    public FechaActualizada(Reserva reserva, Fecha fecha) {
        super("sofka.reserva.fechaactualizada");
        this.reserva = Objects.requireNonNull(reserva,"La reserva no puede ser nula");
        this.fecha = Objects.requireNonNull(fecha, "La fecha no puede ser nula");
    }

    public Reserva getReserva() {
        return reserva;
    }

    public Fecha getFecha() {
        return fecha;
    }
}
