package com.sofka.Turismo_DDD.domain.reserva.entity;

import co.com.sofka.domain.generic.Entity;
import com.sofka.Turismo_DDD.domain.genericvalues.Contacto;
import com.sofka.Turismo_DDD.domain.genericvalues.Direccion;
import com.sofka.Turismo_DDD.domain.genericvalues.Identificacion;
import com.sofka.Turismo_DDD.domain.genericvalues.Nombre;
import com.sofka.Turismo_DDD.domain.reserva.values.VendedorID;

import java.util.Objects;

public class Vendedor extends Entity<VendedorID> {

    private final Identificacion identificacion;
    private Nombre nombre;
    private final Direccion direccion;
    private final Contacto contacto;

    public Vendedor(VendedorID entityId, Identificacion identificacion, Nombre nombre, Direccion direccion,Contacto contacto) {
        super(entityId);
        Objects.requireNonNull(entityId, "El ID del vendedor no puede ser nulo");
        this.identificacion = identificacion;
        this.nombre = nombre;
        this.direccion = direccion;
        this.contacto = contacto;
    }

    public void actualizarNombre(Nombre nombre){
        this.nombre = nombre;
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

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), nombre);
    }
}
