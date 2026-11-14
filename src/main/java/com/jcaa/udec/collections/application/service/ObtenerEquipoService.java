package com.jcaa.udec.collections.application.service;

import com.jcaa.udec.collections.application.service.dto.query.ObtenerEquipoConsulta;
import com.jcaa.udec.collections.application.service.ports.in.ObtenerEquipoUseCase;
import com.jcaa.udec.collections.domain.core.model.Equipo;
import com.jcaa.udec.collections.domain.core.valueobject.EquipoId;
import com.jcaa.udec.collections.domain.port.out.ObtenerEquiposPort;

import java.util.List;

public class ObtenerEquipoService implements ObtenerEquipoUseCase {
    private final ObtenerEquiposPort obtenerEquiposPort;

    public ObtenerEquipoService(ObtenerEquiposPort obtenerEquiposPort) {
        this.obtenerEquiposPort = obtenerEquiposPort;
    }

    @Override
    public List<Equipo> obtenerTodos() {
        return obtenerEquiposPort.obtenerTodos();
    }

    @Override
    public Equipo obtenerPorId(ObtenerEquipoConsulta consulta) {
        return obtenerEquiposPort.buscarPorId(new EquipoId(consulta.id()).valor());
    }
}