package com.jcaa.udec.collections.domain.core.exception;

public class EquipoNoExisteException extends RuntimeException {
    public EquipoNoExisteException() {
        super("El equipo no existe");
    }
}
