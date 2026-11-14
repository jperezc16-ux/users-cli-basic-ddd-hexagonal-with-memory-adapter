package com.jcaa.udec.collections.adapter.persistence.memory;

import com.jcaa.udec.collections.domain.core.model.Equipo;

import java.util.ArrayList;
import java.util.List;

final class EquiposMemoria {
    private static final List<Equipo> EQUIPOS = new ArrayList<>();

    private EquiposMemoria() {
    }

    static List<Equipo> obtenerEquipos() {
        return EQUIPOS;
    }
}
