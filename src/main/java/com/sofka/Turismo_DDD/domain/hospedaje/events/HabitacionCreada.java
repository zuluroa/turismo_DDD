package com.sofka.Turismo_DDD.domain.hospedaje.events;

import co.com.sofka.domain.generic.DomainEvent;
import com.sofka.Turismo_DDD.domain.genericvalues.CupoMaximo;
import com.sofka.Turismo_DDD.domain.hospedaje.values.HabitacionID;
import com.sofka.Turismo_DDD.domain.hospedaje.values.PrecioHabitacion;
import com.sofka.Turismo_DDD.domain.hospedaje.values.TipoDeHabitacion;

import java.util.Objects;

public class HabitacionCreada extends DomainEvent {
    private final HabitacionID habitacionID;
    private final CupoMaximo cupoMaximo;
    private final TipoDeHabitacion tipoDeHabitacion;
    private final PrecioHabitacion precioHabitacion;

    public HabitacionCreada(HabitacionID habitacionID, CupoMaximo cupoMaximo, TipoDeHabitacion tipoDeHabitacion, PrecioHabitacion precioHabitacion) {
        super("sofka.hospedaje.habitacioncreada");
        this.habitacionID = Objects.requireNonNull(habitacionID, "El ID de la habitacion no puede ser nula");
        this.cupoMaximo = Objects.requireNonNull(cupoMaximo, "El cupo maximo no puede ser nulo");
        this.tipoDeHabitacion = Objects.requireNonNull(tipoDeHabitacion, "El tipo de habitacion no puede ser nulo");
        this.precioHabitacion = Objects.requireNonNull(precioHabitacion, "El precio de la habitacion no puede ser nulo");
        if (precioHabitacion.value().isNaN())
            throw new IllegalArgumentException("El precio de la habitacion debe ser de tipo número");
        if (precioHabitacion.value() < 0)
            throw new IllegalArgumentException("El precio debe de la habitacion ser de mayor a 0");
    }

    public HabitacionID getHabitacionID() {
        return habitacionID;
    }

    public CupoMaximo getCupoMaximo() {
        return cupoMaximo;
    }

    public TipoDeHabitacion getTipoDeHabitacion() {
        return tipoDeHabitacion;
    }

    public PrecioHabitacion getPrecioHabitacion() {
        return precioHabitacion;
    }

    @Override
    public String toString() {
        return "HabitacionCreada{" +
                "habitacionID=" + habitacionID +
                ", cupoMaximo=" + cupoMaximo +
                ", tipoDeHabitacion=" + tipoDeHabitacion +
                ", precioHabitacion=" + precioHabitacion +
                ", when=" + when +
                ", uuid=" + uuid +
                ", type='" + type + '\'' +
                '}';
    }
}
