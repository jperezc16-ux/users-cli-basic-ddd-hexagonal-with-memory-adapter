package com.jcaa.udec.collections.application.service.ports.in;

import com.jcaa.udec.collections.application.service.dto.query.ObtenerEquipoConsulta;
import com.jcaa.udec.collections.domain.core.model.Equipo;

import java.util.List;

public interface ObtenerEquipoUseCase {
    List<Equipo> obtenerTodos();

    Equipo obtenerPorId(ObtenerEquipoConsulta consulta);
}
