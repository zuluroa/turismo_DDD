package com.sofka.Turismo_DDD.domain.reserva.events;

import co.com.sofka.domain.generic.DomainEvent;
import com.sofka.Turismo_DDD.domain.reserva.entity.Vendedor;

import java.util.Objects;

public class VendedorCambiado extends DomainEvent {


    private final Vendedor vendedor;

    public VendedorCambiado(Vendedor vendedor) {
        super("sofka.reserva.vendedorcambiado");
        this.vendedor = Objects.requireNonNull(vendedor, "El objeto vendedor no puede ser nulo");
        Objects.requireNonNull(vendedor.getIdentificacion(), "La identificacion no puede ser nula");
        Objects.requireNonNull(vendedor.getNombre(), "El nombre no puede ser nulo");
        Objects.requireNonNull(vendedor.getDireccion(), "La direccion no puede ser nula");
        Objects.requireNonNull(vendedor.getContacto(), "El contacto no puede ser nulo");
    }

    public Vendedor getVendedor() {
        return vendedor;
    }
}
