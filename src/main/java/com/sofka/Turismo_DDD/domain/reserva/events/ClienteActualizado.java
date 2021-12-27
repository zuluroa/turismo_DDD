package com.sofka.Turismo_DDD.domain.reserva.events;

import co.com.sofka.domain.generic.DomainEvent;
import com.sofka.Turismo_DDD.domain.genericvalues.Contacto;
import com.sofka.Turismo_DDD.domain.reserva.values.ClienteID;
import com.sofka.Turismo_DDD.domain.utils.CompararVacioONulo;
import com.sofka.Turismo_DDD.domain.utils.CorreoElectronicoValido;

import java.util.Objects;

import static com.sofka.Turismo_DDD.domain.utils.mayorOIgual.mayorOIgualQue;

public class ClienteActualizado extends DomainEvent {
    private final ClienteID clienteID;
    private final Contacto contacto;

    public ClienteActualizado(ClienteID clienteID, Contacto contacto) {
        super("sofka.reserva.clienteactualizado");
        this.clienteID = Objects.requireNonNull(clienteID, "Para actualizar el cliente  el ID del cliente no puede ser nulo");
        this.contacto = Objects.requireNonNull(contacto, "Para actualizar el cliente  el contacto del cliente no puede ser nulo");
        if (CompararVacioONulo.compararSiNuloOEsVacio(contacto.value().email()))
            throw new IllegalArgumentException("Para actualizar el cliente el email no puede ser nulo o vacio");
        if (CorreoElectronicoValido.emailValido(contacto.value().email()))
            throw new IllegalArgumentException("Para actualizar el cliente el email debe ser un correo electronico valido");
        if (CompararVacioONulo.compararSiNuloOEsVacio(contacto.value().telefono()))
            throw new IllegalArgumentException("Para actualizar el cliente el telefono no puede ser nulo o vacio");
        if (mayorOIgualQue(contacto.value().telefono().length(), 10))
            throw new IllegalArgumentException("Para actualizar el cliente el telefono debe ser mayor a 10 digitos");
    }


    public ClienteID getClienteID() {
        return clienteID;
    }

    public Contacto getContacto() {
        return contacto;
    }
}
