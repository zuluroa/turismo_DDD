package com.sofka.Turismo_DDD.domain.reserva.entity;

import co.com.sofka.domain.generic.Entity;
import com.sofka.Turismo_DDD.domain.genericvalues.Contacto;
import com.sofka.Turismo_DDD.domain.genericvalues.Direccion;
import com.sofka.Turismo_DDD.domain.genericvalues.Identificacion;
import com.sofka.Turismo_DDD.domain.genericvalues.Nombre;
import com.sofka.Turismo_DDD.domain.reserva.values.ClienteID;

import java.util.Objects;

public class Cliente extends Entity<ClienteID> {
    private final Identificacion identificacion;
    private final Nombre nombre;
    private final Direccion direccion;
    private Contacto contacto;

    public Cliente(ClienteID entityId, Identificacion identificacion, Nombre nombre, Direccion direccion, Contacto contacto) {
        super(entityId);
        Objects.requireNonNull(entityId, "El ID del cliente no puede ser nulo");
        this.identificacion =  Objects.requireNonNull(identificacion, "La identificacion del vendedor no puede ser nulo");
        this.nombre = Objects.requireNonNull(nombre, "El nombre del vendedor no puede ser nulo");
        this.direccion = Objects.requireNonNull(direccion, "La direccion del vendedor no puede ser nulo");
        this.contacto = Objects.requireNonNull(contacto, "El contacto del vendedor no puede ser nulo");
    }


    public void actualizarContacto(Contacto contacto) {
        this.contacto = contacto;
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
