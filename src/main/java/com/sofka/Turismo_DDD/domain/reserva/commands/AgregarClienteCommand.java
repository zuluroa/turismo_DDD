package com.sofka.Turismo_DDD.domain.reserva.commands;

import co.com.sofka.domain.generic.Command;
import com.sofka.Turismo_DDD.domain.genericvalues.Contacto;
import com.sofka.Turismo_DDD.domain.genericvalues.Direccion;
import com.sofka.Turismo_DDD.domain.genericvalues.Identificacion;
import com.sofka.Turismo_DDD.domain.genericvalues.Nombre;
import com.sofka.Turismo_DDD.domain.reserva.values.ClienteID;
import com.sofka.Turismo_DDD.domain.reserva.values.ReservaID;

import java.util.Objects;

public class AgregarClienteCommand extends Command {
    private final ReservaID reservaID;
    private final ClienteID clienteID;
    private final Identificacion identificacion;
    private final Nombre nombre;
    private final Direccion direccion;
    private final Contacto contacto;

    public AgregarClienteCommand(ReservaID reservaID, ClienteID clienteID, Identificacion identificacion, Nombre nombre, Direccion direccion, Contacto contacto) {
        this.reservaID = reservaID;
        this.clienteID = Objects.requireNonNull(clienteID, "El ID del cliente no puede ser nulo");
        this.identificacion = Objects.requireNonNull(identificacion, "El tipo de identificación no puede ser nulo");
        this.nombre = Objects.requireNonNull(nombre, "El nombre no puede ser nulo");
        this.direccion = Objects.requireNonNull(direccion, "La direccion no puede ser nula");
        this.contacto = Objects.requireNonNull(contacto, "El contacto no puede ser nulo");
        ;
    }

    public ReservaID getReservaID() {
        return reservaID;
    }

    public ClienteID getClienteID() {
        return clienteID;
    }

    public Identificacion getIdentificacion() {
        return identificacion;
    }

    public Nombre getNombre() {
        return nombre;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public Contacto getContacto() {
        return contacto;
    }
}
