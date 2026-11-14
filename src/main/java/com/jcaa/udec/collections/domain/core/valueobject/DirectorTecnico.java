package com.jcaa.udec.collections.domain.core.valueobject;

import com.jcaa.udec.collections.domain.core.exception.EquipoInvalidoException;

public record DirectorTecnico(String valor) {
    public DirectorTecnico {
        if (valor == null || valor.trim().length() < 3) {
            throw new EquipoInvalidoException("El director tecnico debe tener minimo 3 caracteres");
        }
        valor = valor.trim();
    }
}
