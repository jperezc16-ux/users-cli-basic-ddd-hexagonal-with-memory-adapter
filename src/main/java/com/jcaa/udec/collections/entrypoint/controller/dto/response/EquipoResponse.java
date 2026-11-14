package com.jcaa.udec.collections.entrypoint.controller.dto.response;

import java.util.List;

public record EquipoResponse(String id, String nombre, String pais, String directorTecnico,
                             List<String> jugadores, List<String> personalLogistica) {
    @Override
    public String toString() {
        return "Equipo { id=" + id
                + ", nombre=" + nombre
                + ", pais=" + pais
                + ", director tecnico=" + directorTecnico
                + ", jugadores=" + jugadores
                + ", logistica=" + personalLogistica + " }";
    }
}