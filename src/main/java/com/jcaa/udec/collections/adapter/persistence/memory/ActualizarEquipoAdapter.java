package com.jcaa.udec.collections.adapter.persistence.memory;

import com.jcaa.udec.collections.domain.core.exception.EquipoNoExisteException;
import com.jcaa.udec.collections.domain.core.exception.EquipoYaExisteException;
import com.jcaa.udec.collections.domain.core.model.Equipo;
import com.jcaa.udec.collections.domain.port.out.ActualizarEquipoPort;

import java.util.List;
import java.util.Objects;

public class ActualizarEquipoAdapter implements ActualizarEquipoPort {
    private final List<Equipo> equipos = EquiposMemoria.obtenerEquipos();

    @Override
    public void actualizar(Equipo equipo) {
        int posicion = -1;
        for (int i = 0; i < equipos.size(); i++) {
            Equipo registrado = equipos.get(i);
            if (Objects.equals(registrado.getId(), equipo.getId())) {
                posicion = i;
            } else if (registrado.getNombre().equalsIgnoreCase(equipo.getNombre())) {
                throw new EquipoYaExisteException("Ya existe otro equipo con el nombre " + equipo.getNombre());
            }
        }
        if (posicion < 0) {
            throw new EquipoNoExisteException();
        }
        equipos.set(posicion, equipo);
    }
}
