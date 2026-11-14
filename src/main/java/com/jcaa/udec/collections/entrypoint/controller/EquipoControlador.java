package com.jcaa.udec.collections.entrypoint.controller;

import com.jcaa.udec.collections.application.service.dto.command.ActualizarEquipoComando;
import com.jcaa.udec.collections.application.service.dto.command.CrearEquipoComando;
import com.jcaa.udec.collections.application.service.dto.command.EliminarEquipoComando;
import com.jcaa.udec.collections.application.service.dto.query.ObtenerEquipoConsulta;
import com.jcaa.udec.collections.application.service.ports.in.ActualizarEquipoUseCase;
import com.jcaa.udec.collections.application.service.ports.in.AgregarEquipoUseCase;
import com.jcaa.udec.collections.application.service.ports.in.EliminarEquipoUseCase;
import com.jcaa.udec.collections.application.service.ports.in.ObtenerEquipoUseCase;
import com.jcaa.udec.collections.domain.core.model.Equipo;
import com.jcaa.udec.collections.entrypoint.controller.dto.request.ActualizarEquipoPeticion;
import com.jcaa.udec.collections.entrypoint.controller.dto.request.RegistrarEquipoPeticion;
import com.jcaa.udec.collections.entrypoint.controller.dto.response.EquipoResponse;
import com.jcaa.udec.collections.entrypoint.controller.dto.response.ObtenerEquiposResponse;

import java.util.List;

public class EquipoControlador {
    private final AgregarEquipoUseCase agregarEquipoUseCase;
    private final ObtenerEquipoUseCase obtenerEquipoUseCase;
    private final ActualizarEquipoUseCase actualizarEquipoUseCase;
    private final EliminarEquipoUseCase eliminarEquipoUseCase;

    public EquipoControlador(AgregarEquipoUseCase agregarEquipoUseCase,
                             ObtenerEquipoUseCase obtenerEquipoUseCase,
                             ActualizarEquipoUseCase actualizarEquipoUseCase,
                             EliminarEquipoUseCase eliminarEquipoUseCase) {
        this.agregarEquipoUseCase = agregarEquipoUseCase;
        this.obtenerEquipoUseCase = obtenerEquipoUseCase;
        this.actualizarEquipoUseCase = actualizarEquipoUseCase;
        this.eliminarEquipoUseCase = eliminarEquipoUseCase;
    }

    public void registrar(RegistrarEquipoPeticion peticion) {
        agregarEquipoUseCase.guardar(new CrearEquipoComando(peticion.id(), peticion.nombre(), peticion.pais(),
                peticion.jugadores(), peticion.directorTecnico(), peticion.personalLogistica()));
    }

    public void actualizar(ActualizarEquipoPeticion peticion) {
        actualizarEquipoUseCase.actualizar(new ActualizarEquipoComando(peticion.id(), peticion.nombre(),
                peticion.pais(), peticion.jugadores(), peticion.directorTecnico(), peticion.personalLogistica()));
    }

    public void eliminar(String id) {
        eliminarEquipoUseCase.eliminar(new EliminarEquipoComando(id));
    }

    public EquipoResponse obtenerPorId(String id) {
        return aRespuesta(obtenerEquipoUseCase.obtenerPorId(new ObtenerEquipoConsulta(id)));
    }

    public ObtenerEquiposResponse obtenerTodos() {
        List<EquipoResponse> equipos = obtenerEquipoUseCase.obtenerTodos().stream()
                .map(EquipoControlador::aRespuesta)
                .toList();
        return new ObtenerEquiposResponse(equipos);
    }

    private static EquipoResponse aRespuesta(Equipo equipo) {
        return new EquipoResponse(equipo.getId(), equipo.getNombre(), equipo.getPais(),
                equipo.getDirectorTecnico(), equipo.getJugadores(), equipo.getPersonalLogistica());
    }
}