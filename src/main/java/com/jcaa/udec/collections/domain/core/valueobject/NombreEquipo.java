package com.jcaa.udec.collections.domain.core.valueobject;

import com.jcaa.udec.collections.domain.core.exception.EquipoInvalidoException;

public record NombreEquipo(String valor) {
    public NombreEquipo {
        if (valor == null || valor.trim().length() < 3) {
            throw new EquipoInvalidoException("El nombre del equipo debe tener minimo 3 caracteres");
        }
        valor = valor.trim();
    }
}