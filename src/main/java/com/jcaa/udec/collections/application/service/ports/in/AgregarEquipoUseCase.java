package com.jcaa.udec.collections.application.service.ports.in;

import com.jcaa.udec.collections.application.service.dto.command.CrearEquipoComando;

public interface AgregarEquipoUseCase {
    void guardar(CrearEquipoComando comando);
}