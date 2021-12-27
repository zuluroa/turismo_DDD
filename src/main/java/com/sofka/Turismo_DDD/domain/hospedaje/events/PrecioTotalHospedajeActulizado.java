package com.sofka.Turismo_DDD.domain.hospedaje.events;

import co.com.sofka.domain.generic.DomainEvent;
import com.sofka.Turismo_DDD.domain.hospedaje.Hospedaje;
import com.sofka.Turismo_DDD.domain.hospedaje.values.PrecioTotalHospedaje;

import java.util.Objects;

public class PrecioTotalHospedajeActulizado extends DomainEvent {
    private final Hospedaje hospedaje;
    private final PrecioTotalHospedaje precioTotalHabitacion;

    public PrecioTotalHospedajeActulizado(Hospedaje hospedaje, PrecioTotalHospedaje precioTotalHabitacion) {
        super("sofka.hospedaje.preciototalhospedajeactulizado");
        this.hospedaje = Objects.requireNonNull(hospedaje, "El hospedaje no puede estar nulo");
        this.precioTotalHabitacion = Objects.requireNonNull(precioTotalHabitacion, "El precio del hospedaje no puede ser nulo");
        if (precioTotalHabitacion.value().isNaN())
            throw new IllegalArgumentException("El precio del hospedaje debe ser de tipo número");
        if (precioTotalHabitacion.value() < 0)
            throw new IllegalArgumentException("El precio debe del hospedaje ser de mayor a 0");
    }

    public Hospedaje getHospedaje() {
        return hospedaje;
    }

    public PrecioTotalHospedaje getPrecioTotalHabitacion() {
        return precioTotalHabitacion;
    }
}
