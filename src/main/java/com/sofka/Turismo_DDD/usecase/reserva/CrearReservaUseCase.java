package com.sofka.Turismo_DDD.usecase.reserva;

import co.com.sofka.business.generic.BusinessException;
import co.com.sofka.business.generic.UseCase;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.business.support.ResponseEvents;
import com.sofka.Turismo_DDD.domain.genericvalues.Nombre;
import com.sofka.Turismo_DDD.domain.reserva.Reserva;
import com.sofka.Turismo_DDD.domain.reserva.commands.CrearReservaCommand;


public class CrearReservaUseCase extends UseCase<RequestCommand<CrearReservaCommand>, ResponseEvents> {
    @Override
    public void executeUseCase(RequestCommand<CrearReservaCommand> requestCommand) {
        var command = requestCommand.getCommand();
        var reserva = new Reserva(command.getReservaID(), command.getVendedor(), command.getFecha());
        var nombre = new Nombre("NO TIENE", "NO TIENE");

        if (reserva.getVendedor().getNombre().value().nombres().equals(nombre.value().nombres()) ||
                reserva.getVendedor().getNombre().value().apellidos().equals(nombre.value().apellidos()))
            throw new BusinessException(command.getReservaID().value(), "El vendedor debe tener un nombre");

        emit().onResponse(new ResponseEvents(reserva.getUncommittedChanges()));
    }
}
