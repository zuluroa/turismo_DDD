package com.sofka.Turismo_DDD.usecase.hospedaje;

import co.com.sofka.business.generic.BusinessException;
import co.com.sofka.business.generic.UseCase;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.business.support.ResponseEvents;
import com.sofka.Turismo_DDD.domain.hospedaje.Hospedaje;
import com.sofka.Turismo_DDD.domain.hospedaje.commands.CrearHospedajeCommand;

public class CrearHospedajeUseCase extends UseCase<RequestCommand<CrearHospedajeCommand>, ResponseEvents> {
    @Override
    public void executeUseCase(RequestCommand<CrearHospedajeCommand> requestCommand) {
        var command = requestCommand.getCommand();
        var hospedaje = new Hospedaje(command.getHospedajeID(), command.getLocalizacion(), command.getPrecioTotalHabitacion());

        if (hospedaje.getLocalizacion().getCiudad().value().equals("DESCONOCIDO"))
            throw new BusinessException(command.getHospedajeID().value(),
                    "La ciudad no puede ser desconocida");

        emit().onResponse(new ResponseEvents(hospedaje.getUncommittedChanges()));
    }
}
