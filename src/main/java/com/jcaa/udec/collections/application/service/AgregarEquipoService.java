package com.jcaa.udec.collections.application.service;

import com.jcaa.udec.collections.application.service.dto.command.CrearEquipoComando;
import com.jcaa.udec.collections.application.service.ports.in.AgregarEquipoUseCase;
import com.jcaa.udec.collections.domain.core.model.Equipo;
import com.jcaa.udec.collections.domain.port.out.GuardarEquipoPort;

public class AgregarEquipoService implements AgregarEquipoUseCase {
    private final GuardarEquipoPort guardarEquipoPort;

    public AgregarEquipoService(GuardarEquipoPort guardarEquipoPort) {
        this.guardarEquipoPort = guardarEquipoPort;
    }

    @Override
    public void guardar(CrearEquipoComando comando) {
        Equipo equipo = new Equipo(comando.id(), comando.nombre(), comando.pais(), comando.jugadores(),
                comando.directorTecnico(), comando.personalLogistica());
        guardarEquipoPort.guardar(equipo);
    }
}
