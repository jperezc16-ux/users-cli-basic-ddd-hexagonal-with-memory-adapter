package com.jcaa.udec.collections.domain.core.valueobject;

import com.jcaa.udec.collections.domain.core.exception.EquipoInvalidoException;

public record PaisEquipo(String valor) {
    public PaisEquipo {
        if (valor == null || valor.trim().length() < 2) {
            throw new EquipoInvalidoException("El pais debe tener minimo 2 caracteres");
        }
        valor = valor.trim();
    }
}