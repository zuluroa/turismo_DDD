package com.sofka.Turismo_DDD.usecase.plandeviaje;

import co.com.sofka.business.generic.BusinessException;
import co.com.sofka.business.generic.UseCase;
import co.com.sofka.business.support.ResponseEvents;
import co.com.sofka.business.support.TriggeredEvent;
import com.sofka.Turismo_DDD.domain.plandeviaje.events.ActividadCreado;

import java.util.List;

public class NuevaActividadCreadaMensajeDeTexto extends UseCase<TriggeredEvent<ActividadCreado>, ResponseEvents> {
    @Override
    public void executeUseCase(TriggeredEvent<ActividadCreado> triggeredEvent) {
        var event = triggeredEvent.getDomainEvent();
        var service = getService(SendMensajeTexto.class).orElseThrow();
        boolean isSend = service.sendMessage(
                "3202040834",
                "NUEVA ACTIVIDAD CREADA! " + event.getActividadPlanDeVIaje().value()
        );

        if (!isSend) throw new BusinessException(event.aggregateRootId(), "No se pudo enviar el mensaje");

        emit().onResponse(new ResponseEvents(List.of()));
    }
}
