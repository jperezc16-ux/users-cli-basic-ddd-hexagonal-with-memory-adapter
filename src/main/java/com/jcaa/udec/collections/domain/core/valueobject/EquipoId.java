package com.jcaa.udec.collections.domain.core.valueobject;

import com.jcaa.udec.collections.domain.core.exception.EquipoInvalidoException;

public record EquipoId(String valor) {
    public EquipoId {
        if (valor == null || !valor.trim().matches("\\d+")) {
            throw new EquipoInvalidoException("El id del equipo debe ser un numero entero");
        }
        valor = valor.trim();
    }
}