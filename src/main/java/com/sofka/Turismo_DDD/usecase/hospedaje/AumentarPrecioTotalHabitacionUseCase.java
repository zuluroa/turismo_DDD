package com.sofka.Turismo_DDD.usecase.hospedaje;

import co.com.sofka.business.generic.BusinessException;
import co.com.sofka.business.generic.UseCase;
import co.com.sofka.business.support.ResponseEvents;
import co.com.sofka.business.support.TriggeredEvent;
import com.sofka.Turismo_DDD.domain.hospedaje.Hospedaje;
import com.sofka.Turismo_DDD.domain.hospedaje.events.PrecioTotalHospedajeActulizado;
import com.sofka.Turismo_DDD.domain.hospedaje.values.HospedajeID;
import com.sofka.Turismo_DDD.domain.hospedaje.values.PrecioTotalHospedaje;

public class AumentarPrecioTotalHabitacionUseCase extends UseCase<TriggeredEvent<PrecioTotalHospedajeActulizado>, ResponseEvents> {

    @Override
    public void executeUseCase(TriggeredEvent<PrecioTotalHospedajeActulizado> triggeredEvent) {
        var event = triggeredEvent.getDomainEvent();
        var hospedaje = Hospedaje.from(HospedajeID.of(event.aggregateRootId()),retrieveEvents());
        if (event.getPrecioTotalHabitacion().value() < 0) throw  new BusinessException(event.aggregateRootId(),
                "El precio del hospedaje debe ser mayor a 0");
        hospedaje.ActualizarPrecioTotalHospedaje(hospedaje,new PrecioTotalHospedaje(hospedaje.getPrecioTotalHabitacion().value()+event.getPrecioTotalHabitacion().value()));
        emit().onResponse(new ResponseEvents(hospedaje.getUncommittedChanges()));
    }
}
