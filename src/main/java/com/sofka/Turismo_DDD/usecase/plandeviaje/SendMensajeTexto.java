package com.sofka.Turismo_DDD.usecase.plandeviaje;

import com.sofka.Turismo_DDD.domain.reserva.values.ReservaID;

public interface SendMensajeTexto {
    boolean sendMessage(String numero, String body);
}
