package com.jcaa.udec.collections.application.service.dto.command;

import java.util.List;

public record CrearEquipoComando(String id, String nombre, String pais, List<String> jugadores,
                                 String directorTecnico, List<String> personalLogistica) {
}
