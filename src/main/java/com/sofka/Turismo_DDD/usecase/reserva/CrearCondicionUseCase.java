package com.sofka.Turismo_DDD.usecase.reserva;

import co.com.sofka.business.generic.BusinessException;
import co.com.sofka.business.generic.UseCase;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.business.support.ResponseEvents;
import com.sofka.Turismo_DDD.domain.reserva.Reserva;
import com.sofka.Turismo_DDD.domain.reserva.commands.CrearCondicionCommand;

public class CrearCondicionUseCase extends UseCase<RequestCommand<CrearCondicionCommand>, ResponseEvents> {
    @Override
    public void executeUseCase(RequestCommand<CrearCondicionCommand> requestCommand) {
        var command = requestCommand.getCommand();
        var reserva = Reserva.from(command.getReservaID(), retrieveEvents());

        if (reserva.getCondiciones().size() == 6) throw new BusinessException(command.getReservaID().value(),
                "Llego el tope de condiciones agregadas");

        reserva.crearCondicion(command.getCondicionID(), command.getObservacion());
        emit().onResponse(new ResponseEvents(reserva.getUncommittedChanges()));
    }
}
