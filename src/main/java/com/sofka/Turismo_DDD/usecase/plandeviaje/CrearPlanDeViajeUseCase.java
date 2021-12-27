package com.sofka.Turismo_DDD.usecase.plandeviaje;

import co.com.sofka.business.generic.BusinessException;
import co.com.sofka.business.generic.UseCase;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.business.support.ResponseEvents;
import com.sofka.Turismo_DDD.domain.genericvalues.CupoMaximo;
import com.sofka.Turismo_DDD.domain.plandeviaje.PlanDeViaje;
import com.sofka.Turismo_DDD.domain.plandeviaje.commands.CrearPlanDeViajeCommand;
import com.sofka.Turismo_DDD.domain.plandeviaje.values.PlanDeViajeID;

import java.util.Calendar;
import java.util.Date;

public class CrearPlanDeViajeUseCase extends UseCase<RequestCommand<CrearPlanDeViajeCommand>, ResponseEvents> {
    @Override
    public void executeUseCase(RequestCommand<CrearPlanDeViajeCommand> requestCommand) {
        var command = requestCommand.getCommand();
        var planDeViaje = new PlanDeViaje(PlanDeViajeID.of("1234"),command.getFechaPlanDeViaje(),command.getCupoMaximo());

        var calendar = Calendar.getInstance();

        calendar.setTime(planDeViaje.getFechaPlanDeViaje().getFechaIda().value());
        var dayOFWeek = calendar.get(Calendar.DAY_OF_WEEK);
        if(dayOFWeek == Calendar.SATURDAY || dayOFWeek == Calendar.SUNDAY){
            throw new BusinessException(command.getPlanDeViajeID().value(), "No puede crear fechas en fines de semana");
        }

        emit().onResponse(new ResponseEvents(planDeViaje.getUncommittedChanges()));
    }
}
