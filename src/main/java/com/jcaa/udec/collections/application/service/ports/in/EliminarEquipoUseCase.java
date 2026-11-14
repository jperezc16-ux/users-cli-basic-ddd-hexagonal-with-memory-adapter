package com.jcaa.udec.collections.application.service.ports.in;

import com.jcaa.udec.collections.application.service.dto.command.EliminarEquipoComando;

public interface EliminarEquipoUseCase {
    void eliminar(EliminarEquipoComando comando);
}
