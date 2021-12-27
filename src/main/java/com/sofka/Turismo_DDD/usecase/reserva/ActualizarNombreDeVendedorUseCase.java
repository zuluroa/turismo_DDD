package com.sofka.Turismo_DDD.usecase.reserva;

import co.com.sofka.business.generic.BusinessException;
import co.com.sofka.business.generic.UseCase;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.business.support.ResponseEvents;
import com.sofka.Turismo_DDD.domain.genericvalues.Nombre;
import com.sofka.Turismo_DDD.domain.reserva.Reserva;
import com.sofka.Turismo_DDD.domain.reserva.commands.ActualizarNombreDeVendedorCommand;

public class ActualizarNombreDeVendedorUseCase extends UseCase<RequestCommand<ActualizarNombreDeVendedorCommand>, ResponseEvents> {
    @Override
    public void executeUseCase(RequestCommand<ActualizarNombreDeVendedorCommand> requestCommand) {
        var command = requestCommand.getCommand();
        var reserva = Reserva.from(command.getReservaID(), retrieveEvents());
        var nombre = new Nombre("NO TIENE", "NO TIENE");

        if (reserva.getVendedor().getNombre().value().nombres().equals(nombre.value().nombres()) ||
                reserva.getVendedor().getNombre().value().apellidos().equals(nombre.value().apellidos()))
            throw new BusinessException(command.getReservaID().value(), "El vendedor debe tener un nombre");

        reserva.actualizarNombreDeVendedor(command.getVendedor(), command.getNombre());

        emit().onResponse(new ResponseEvents(reserva.getUncommittedChanges()));

    }
}
