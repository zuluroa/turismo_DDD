package com.sofka.Turismo_DDD.usecase.reserva;

import co.com.sofka.business.generic.BusinessException;
import co.com.sofka.business.generic.UseCase;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.business.support.ResponseEvents;
import com.sofka.Turismo_DDD.domain.reserva.Reserva;
import com.sofka.Turismo_DDD.domain.reserva.commands.AgregarClienteCommand;

public class AgregarClienteUseCase extends UseCase<RequestCommand<AgregarClienteCommand>, ResponseEvents> {
    @Override
    public void executeUseCase(RequestCommand<AgregarClienteCommand> requestCommand) {
        var command = requestCommand.getCommand();
        var reserva = Reserva.from(command.getReservaID(), retrieveEvents());

        if (reserva.getCliente().size() == 3) throw new BusinessException(command.getReservaID().value(),
                "Llego el tope de clientes agregados");

        reserva.agregarCliente(command.getClienteID(), command.getIdentificacion(), command.getNombre(), command.getDireccion(), command.getContacto());

        emit().onResponse(new ResponseEvents(reserva.getUncommittedChanges()));
    }
}
