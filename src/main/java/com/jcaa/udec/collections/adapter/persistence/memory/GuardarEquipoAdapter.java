package com.jcaa.udec.collections.adapter.persistence.memory;

import com.jcaa.udec.collections.domain.core.exception.EquipoYaExisteException;
import com.jcaa.udec.collections.domain.core.model.Equipo;
import com.jcaa.udec.collections.domain.port.out.GuardarEquipoPort;

import java.util.List;
import java.util.Objects;

public class GuardarEquipoAdapter implements GuardarEquipoPort {
    private final List<Equipo> equipos = EquiposMemoria.obtenerEquipos();

    @Override
    public void guardar(Equipo equipo) {
        for (Equipo registrado : equipos) {
            if (Objects.equals(registrado.getId(), equipo.getId())) {
                throw new EquipoYaExisteException("Ya existe un equipo con el id " + equipo.getId());
            }
            if (registrado.getNombre().equalsIgnoreCase(equipo.getNombre())) {
                throw new EquipoYaExisteException("Ya existe un equipo con el nombre " + equipo.getNombre());
            }
        }
        equipos.add(equipo);
    }
}
