package com.sofka.Turismo_DDD.usecase.reserva;

import com.sofka.Turismo_DDD.domain.reserva.values.ReservaID;

public interface SendEmailService {
    boolean send(ReservaID reservaID, String email, String body);
}
