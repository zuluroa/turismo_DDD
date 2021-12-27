package com.sofka.Turismo_DDD.domain.hospedaje.values;

import co.com.sofka.domain.generic.Identity;

public class LocalizacionID extends Identity {
    public LocalizacionID() {
    }

    private LocalizacionID(String id) {
        super(id);
    }

    public static LocalizacionID of(String id) {
        return new LocalizacionID(id);
    }
}
