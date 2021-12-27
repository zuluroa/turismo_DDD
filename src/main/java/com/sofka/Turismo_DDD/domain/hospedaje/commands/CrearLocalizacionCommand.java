package com.sofka.Turismo_DDD.domain.hospedaje.commands;

import co.com.sofka.domain.generic.Command;
import com.sofka.Turismo_DDD.domain.hospedaje.entity.Localizacion;
import com.sofka.Turismo_DDD.domain.hospedaje.values.HospedajeID;

import java.util.Objects;

public class CrearLocalizacionCommand extends Command {
    private final HospedajeID hospedajeID;
    private final Localizacion localizacion;

    public CrearLocalizacionCommand(HospedajeID hospedajeID, Localizacion localizacion) {
        this.hospedajeID = Objects.requireNonNull(hospedajeID, "El ID del hospedaje no puede ser nulo");
        this.localizacion = Objects.requireNonNull(localizacion, "La localizacion no puede ser nula");
    }

    public HospedajeID getHospedajeID() {
        return hospedajeID;
    }

    public Localizacion getLocalizacion() {
        return localizacion;
    }
}
