package com.sofka.Turismo_DDD.domain.hospedaje.commands;

import co.com.sofka.domain.generic.Command;
import com.sofka.Turismo_DDD.domain.genericvalues.CupoMaximo;
import com.sofka.Turismo_DDD.domain.hospedaje.values.HabitacionID;
import com.sofka.Turismo_DDD.domain.hospedaje.values.HospedajeID;
import com.sofka.Turismo_DDD.domain.hospedaje.values.PrecioHabitacion;
import com.sofka.Turismo_DDD.domain.hospedaje.values.TipoDeHabitacion;

import java.util.Objects;

public class CrearHabitacionCommand extends Command {
    private final HospedajeID hospedajeID;
    private final HabitacionID habitacionID;
    private final CupoMaximo cupoMaximo;
    private final TipoDeHabitacion tipoDeHabitacion;
    private final PrecioHabitacion precioHabitacion;

    public CrearHabitacionCommand(HospedajeID hospedajeID,HabitacionID habitacionID, CupoMaximo cupoMaximo, TipoDeHabitacion tipoDeHabitacion, PrecioHabitacion precioHabitacion) {
        this.hospedajeID = Objects.requireNonNull(hospedajeID, "El ID del hospedaje no puede ser nulo");
        this.habitacionID = Objects.requireNonNull(habitacionID, "El ID de la habitacion no puede ser nula");
        this.cupoMaximo = Objects.requireNonNull(cupoMaximo, "El cupo maximo no puede ser nulo");
        this.tipoDeHabitacion = Objects.requireNonNull(tipoDeHabitacion, "El tipo de habitacion no puede ser nulo");
        this.precioHabitacion = Objects.requireNonNull(precioHabitacion, "El precio de la habitacion no puede ser nulo");
    }

    public HospedajeID getHospedajeID() {
        return hospedajeID;
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
}
