package com.sofka.Turismo_DDD.usecase.plandeviaje;

import co.com.sofka.business.generic.BusinessException;
import co.com.sofka.business.generic.UseCase;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.business.support.ResponseEvents;
import com.sofka.Turismo_DDD.domain.plandeviaje.PlanDeViaje;
import com.sofka.Turismo_DDD.domain.plandeviaje.commands.CrearActividadCommand;

public class CrearActividadUseCase extends UseCase<RequestCommand<CrearActividadCommand>, ResponseEvents> {
    @Override
    public void executeUseCase(RequestCommand<CrearActividadCommand> requestCommand) {
        var command = requestCommand.getCommand();
        var planDeViaje =  PlanDeViaje.from(command.getPlanDeViajeID(),retrieveEvents());

        if (planDeViaje.getActividades().size() == 8) throw new BusinessException(command.getPlanDeViajeID().value(),
                "Llego el tope de actividades agregadas");

        planDeViaje.crearActividad(command.getActividadID(),command.getActividadPlanDeVIaje());
        emit().onResponse(new ResponseEvents(planDeViaje.getUncommittedChanges()));
    }
}
