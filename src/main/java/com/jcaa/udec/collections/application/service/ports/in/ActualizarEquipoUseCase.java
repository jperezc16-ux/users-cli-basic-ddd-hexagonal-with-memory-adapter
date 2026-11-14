package com.jcaa.udec.collections.application.service.ports.in;

import com.jcaa.udec.collections.application.service.dto.command.ActualizarEquipoComando;

public interface ActualizarEquipoUseCase {
    void actualizar(ActualizarEquipoComando comando);
}
