package com.jcaa.udec.collections.adapter.persistence.memory;

import com.jcaa.udec.collections.domain.core.exception.EquipoNoExisteException;
import com.jcaa.udec.collections.domain.core.model.Equipo;
import com.jcaa.udec.collections.domain.port.out.EliminarEquipoPort;

import java.util.List;
import java.util.Objects;

public class EliminarEquipoAdapter implements EliminarEquipoPort {
    private final List<Equipo> equipos = EquiposMemoria.obtenerEquipos();

    @Override
    public void eliminar(String id) {
        boolean eliminado = equipos.removeIf(equipo -> Objects.equals(equipo.getId(), id));
        if (!eliminado) {
            throw new EquipoNoExisteException();
        }
    }
}
