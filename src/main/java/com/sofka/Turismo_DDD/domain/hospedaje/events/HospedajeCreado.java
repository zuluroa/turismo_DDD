package com.sofka.Turismo_DDD.domain.hospedaje.events;

import co.com.sofka.domain.generic.DomainEvent;
import com.sofka.Turismo_DDD.domain.hospedaje.entity.Localizacion;
import com.sofka.Turismo_DDD.domain.hospedaje.values.PrecioTotalHospedaje;

import java.util.Objects;

public class HospedajeCreado extends DomainEvent {
    private final Localizacion localizacion;
    private final PrecioTotalHospedaje precioTotalHabitacion;

    public HospedajeCreado(Localizacion localizacion, PrecioTotalHospedaje precioTotalHabitacion) {
        super("sofka.hospedaje.hospedajecreado");
        this.localizacion = Objects.requireNonNull(localizacion, "La localizacion no puede ser nula");
        this.precioTotalHabitacion = Objects.requireNonNull(precioTotalHabitacion, "El precio del hospedaje no puede ser nulo");
        if (precioTotalHabitacion.value().isNaN())
            throw new IllegalArgumentException("El precio del hospedaje debe ser de tipo número");
        if (precioTotalHabitacion.value() < 0)
            throw new IllegalArgumentException("El precio debe del hospedaje ser de mayor a 0");
    }

    public Localizacion getLocalizacion() {
        return localizacion;
    }

    public PrecioTotalHospedaje getPrecioTotalHabitacion() {
        return precioTotalHabitacion;
    }
}
