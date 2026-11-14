package com.jcaa.udec.collections.adapter.persistence.memory;

import com.jcaa.udec.collections.domain.core.exception.EquipoNoExisteException;
import com.jcaa.udec.collections.domain.core.model.Equipo;
import com.jcaa.udec.collections.domain.port.out.ObtenerEquiposPort;

import java.util.List;
import java.util.Objects;

public class ObtenerEquiposAdapter implements ObtenerEquiposPort {
    private final List<Equipo> equipos = EquiposMemoria.obtenerEquipos();

    @Override
    public List<Equipo> obtenerTodos() {
        return List.copyOf(equipos);
    }

    @Override
    public Equipo buscarPorId(String id) {
        for (Equipo equipo : equipos) {
            if (Objects.equals(equipo.getId(), id)) {
                return equipo;
            }
        }
        throw new EquipoNoExisteException();
    }
}
