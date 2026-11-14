package com.jcaa.udec.collections.application.service;

import com.jcaa.udec.collections.application.service.dto.command.EliminarEquipoComando;
import com.jcaa.udec.collections.application.service.ports.in.EliminarEquipoUseCase;
import com.jcaa.udec.collections.domain.core.valueobject.EquipoId;
import com.jcaa.udec.collections.domain.port.out.EliminarEquipoPort;

public class EliminarEquipoService implements EliminarEquipoUseCase {
    private final EliminarEquipoPort eliminarEquipoPort;

    public EliminarEquipoService(EliminarEquipoPort eliminarEquipoPort) {
        this.eliminarEquipoPort = eliminarEquipoPort;
    }

    @Override
    public void eliminar(EliminarEquipoComando comando) {
        eliminarEquipoPort.eliminar(new EquipoId(comando.id()).valor());
    }
}