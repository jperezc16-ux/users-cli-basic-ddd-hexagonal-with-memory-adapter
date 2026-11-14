package com.jcaa.udec.collections.domain.port.out;

import com.jcaa.udec.collections.domain.core.model.Equipo;

import java.util.List;

public interface ObtenerEquiposPort {
    List<Equipo> obtenerTodos();

    Equipo buscarPorId(String id);
}