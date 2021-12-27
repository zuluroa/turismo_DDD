package com.sofka.Turismo_DDD.usecase.hospedaje;

import co.com.sofka.business.generic.BusinessException;
import co.com.sofka.business.generic.UseCase;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.business.support.ResponseEvents;
import com.sofka.Turismo_DDD.domain.hospedaje.Hospedaje;
import com.sofka.Turismo_DDD.domain.hospedaje.commands.CrearHabitacionCommand;

public class CrearHabitacionUseCase extends UseCase<RequestCommand<CrearHabitacionCommand>, ResponseEvents> {
    @Override
    public void executeUseCase(RequestCommand<CrearHabitacionCommand> requestCommand) {
        var command = requestCommand.getCommand();
        var hospedaje = Hospedaje.from(command.getHospedajeID(),retrieveEvents());

        if(hospedaje.getLocalizacion().getCiudad().value() == null || hospedaje.getLocalizacion().getCiudad().value().isBlank()) throw new BusinessException(command.getHospedajeID().value(),
                "El hospedaje no puede ser nulo o vacio");
        if (hospedaje.getPrecioTotalHabitacion().value() < 0) throw  new BusinessException(command.getHospedajeID().value(),
                "El precio del hospedaje debe ser mayor a 0");

        if(hospedaje.getHabitaciones().size() == 4)throw  new BusinessException(command.getHospedajeID().value(),
                "Alcanzo el limite de habitaciones creadas");

        hospedaje.crearHabitacion(command.getHabitacionID(),command.getCupoMaximo(),command.getTipoDeHabitacion(),command.getPrecioHabitacion());
        emit().onResponse(new ResponseEvents(retrieveEvents()));
    }
}
