package com.sofka.Turismo_DDD.usecase.plandeviaje;

import co.com.sofka.business.generic.BusinessException;
import co.com.sofka.business.generic.UseCase;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.business.support.ResponseEvents;
import com.sofka.Turismo_DDD.domain.plandeviaje.PlanDeViaje;
import com.sofka.Turismo_DDD.domain.plandeviaje.commands.CrearSitioCommand;

public class CrearSitioUseCase extends UseCase<RequestCommand<CrearSitioCommand>, ResponseEvents> {
    @Override
    public void executeUseCase(RequestCommand<CrearSitioCommand> requestCommand) {
        var command = requestCommand.getCommand();
        var planDeViaje =  PlanDeViaje.from(command.getPlanDeViajeID(),retrieveEvents());
        if (planDeViaje.getSitios().size() == 3) throw new BusinessException(command.getPlanDeViajeID().value(),
                "Llego el tope de sitios agregadas");
        planDeViaje.crearSitio(command.getSitioID(),command.getSitio());
        emit().onResponse(new ResponseEvents(planDeViaje.getUncommittedChanges()));
    }
}
