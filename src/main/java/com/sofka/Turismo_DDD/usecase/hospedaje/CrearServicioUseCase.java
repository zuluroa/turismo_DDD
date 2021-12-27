package com.sofka.Turismo_DDD.usecase.hospedaje;

import co.com.sofka.business.generic.BusinessException;
import co.com.sofka.business.generic.UseCase;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.business.support.ResponseEvents;
import com.sofka.Turismo_DDD.domain.hospedaje.Hospedaje;
import com.sofka.Turismo_DDD.domain.hospedaje.commands.CrearServicioCommand;

public class CrearServicioUseCase extends UseCase<RequestCommand<CrearServicioCommand>, ResponseEvents> {
    @Override
    public void executeUseCase(RequestCommand<CrearServicioCommand> requestCommand) {
        var command = requestCommand.getCommand();
        var hospedaje = Hospedaje.from(command.getHospedajeID(), retrieveEvents());

        if (hospedaje.getHabitaciones() == null) throw new BusinessException(command.getHospedajeID().value(),
                "Debe existir por lo menos una habitacion");

        if (hospedaje.getLocalizacion().getCiudad().value() == null || hospedaje.getLocalizacion().getCiudad().value().isBlank())
            throw new BusinessException(command.getHospedajeID().value(), "" +
                    "El hospedaje no puede ser nulo o vacio");
        if (hospedaje.getPrecioTotalHabitacion().value() < 0)
            throw new BusinessException(command.getHospedajeID().value(),
                    "El precio del hospedaje debe ser mayor a 0");

        if (hospedaje.getServicios().size() == 5) throw new BusinessException(command.getHospedajeID().value(),
                "Alcanzo el limite de servicios creados");

        hospedaje.crearServicio(command.getServicioID(), command.getTipoDeServicio());
        emit().onResponse(new ResponseEvents(hospedaje.getUncommittedChanges()));
    }
}
