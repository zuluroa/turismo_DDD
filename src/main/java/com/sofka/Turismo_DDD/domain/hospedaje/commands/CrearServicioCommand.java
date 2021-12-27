package com.sofka.Turismo_DDD.domain.hospedaje.commands;

import co.com.sofka.domain.generic.Command;
import com.sofka.Turismo_DDD.domain.hospedaje.values.HospedajeID;
import com.sofka.Turismo_DDD.domain.hospedaje.values.ServicioID;
import com.sofka.Turismo_DDD.domain.hospedaje.values.TipoDeServicio;

import java.util.Objects;

public class CrearServicioCommand extends Command {

    private final HospedajeID hospedajeID;
    private final ServicioID servicioID;
    private final TipoDeServicio tipoDeServicio;

    public CrearServicioCommand(HospedajeID hospedajeID, ServicioID servicioID, TipoDeServicio tipoDeServicio) {
        this.hospedajeID = hospedajeID;
        this.servicioID = Objects.requireNonNull(servicioID, "El ID del servicio no puede ser nulo");
        this.tipoDeServicio = Objects.requireNonNull(tipoDeServicio, "El tipo de servicio no puede ser nulo");
    }

    public HospedajeID getHospedajeID() {
        return hospedajeID;
    }

    public ServicioID getServicioID() {
        return servicioID;
    }

    public TipoDeServicio getTipoDeServicio() {
        return tipoDeServicio;
    }
}
