package com.jcaa.udec.collections.application.service;

import com.jcaa.udec.collections.application.service.dto.command.ActualizarEquipoComando;
import com.jcaa.udec.collections.application.service.ports.in.ActualizarEquipoUseCase;
import com.jcaa.udec.collections.domain.core.model.Equipo;
import com.jcaa.udec.collections.domain.port.out.ActualizarEquipoPort;

public class ActualizarEquipoService implements ActualizarEquipoUseCase {
    private final ActualizarEquipoPort actualizarEquipoPort;

    public ActualizarEquipoService(ActualizarEquipoPort actualizarEquipoPort) {
        this.actualizarEquipoPort = actualizarEquipoPort;
    }

    @Override
    public void actualizar(ActualizarEquipoComando comando) {
        Equipo equipo = new Equipo(comando.id(), comando.nombre(), comando.pais(), comando.jugadores(),
                comando.directorTecnico(), comando.personalLogistica());
        actualizarEquipoPort.actualizar(equipo);
    }
}