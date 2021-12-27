package com.sofka.Turismo_DDD.usecase.plandeviaje;

import co.com.sofka.business.generic.BusinessException;
import co.com.sofka.business.generic.UseCase;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.business.support.ResponseEvents;
import com.sofka.Turismo_DDD.domain.plandeviaje.PlanDeViaje;
import com.sofka.Turismo_DDD.domain.plandeviaje.commands.CrearFechaPlanDeViajeCommand;

import java.util.Calendar;

public class CrearFechaPlanDeViajeUseCase extends UseCase<RequestCommand<CrearFechaPlanDeViajeCommand>, ResponseEvents> {
    @Override
    public void executeUseCase(RequestCommand<CrearFechaPlanDeViajeCommand> requestCommand) {
        var command = requestCommand.getCommand();
        var planDeViaje = PlanDeViaje.from(command.getPlanDeViajeID(), retrieveEvents());
        var calendar = Calendar.getInstance();

        calendar.setTime(planDeViaje.getFechaPlanDeViaje().getFechaIda().value());
        var dayOFWeek = calendar.get(Calendar.DAY_OF_WEEK);
        if (dayOFWeek == Calendar.SATURDAY || dayOFWeek == Calendar.SUNDAY) {
            throw new BusinessException(command.getPlanDeViajeID().value(), "No puede crear fechas en fines de semana");
        }

        planDeViaje.crearFechaPlanDeViaje(command.getFechaPlanDeViaje());
        emit().onResponse(new ResponseEvents(planDeViaje.getUncommittedChanges()));
    }
}
