package com.sofka.Turismo_DDD.domain.hospedaje.commands;

import co.com.sofka.domain.generic.Command;
import com.sofka.Turismo_DDD.domain.hospedaje.entity.Localizacion;
import com.sofka.Turismo_DDD.domain.hospedaje.values.HospedajeID;
import com.sofka.Turismo_DDD.domain.hospedaje.values.PrecioTotalHospedaje;

import java.util.Objects;

public class CrearHospedajeCommand extends Command {
    private final HospedajeID hospedajeID;
    private final Localizacion localizacion;
    private final PrecioTotalHospedaje precioTotalHabitacion;

    public CrearHospedajeCommand(HospedajeID hospedajeID, Localizacion localizacion, PrecioTotalHospedaje precioTotalHabitacion) {
        this.hospedajeID = Objects.requireNonNull(hospedajeID, "El ID del hospedaje no puede ser vacio");
        this.localizacion = Objects.requireNonNull(localizacion, "La localizacion no puede estar vacia");
        this.precioTotalHabitacion = Objects.requireNonNull(precioTotalHabitacion, "El precio total de la habitacion no puede estar vacia");
    }

    public HospedajeID getHospedajeID() {
        return hospedajeID;
    }

    public Localizacion getLocalizacion() {
        return localizacion;
    }

    public PrecioTotalHospedaje getPrecioTotalHabitacion() {
        return precioTotalHabitacion;
    }
}
