package com.sofka.Turismo_DDD.usecase.reserva;

import co.com.sofka.business.generic.BusinessException;
import co.com.sofka.business.generic.UseCase;
import co.com.sofka.business.support.ResponseEvents;
import co.com.sofka.business.support.TriggeredEvent;
import com.sofka.Turismo_DDD.domain.reserva.events.ReservaCreada;
import com.sofka.Turismo_DDD.domain.reserva.values.ReservaID;

import java.util.List;

public class NotificarEmailReservaCreadaUseCase extends UseCase<TriggeredEvent<ReservaCreada>, ResponseEvents> {
    @Override
    public void executeUseCase(TriggeredEvent<ReservaCreada> triggeredEvent) {
        var event = triggeredEvent.getDomainEvent();
        var service = getService(SendEmailService.class).orElseThrow();
        boolean isSend = service.send(
                ReservaID.of(event.aggregateRootId()),
                "support@sofka.com.co",
                "Nueva reserva con fecha " + event.getFecha().value()
        );

        if (!isSend) throw new BusinessException(event.aggregateRootId(), "No se pudo enviar el correo");

        emit().onResponse(new ResponseEvents(List.of()));
    }
}
