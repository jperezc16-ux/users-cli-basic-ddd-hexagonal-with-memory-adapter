package com.jcaa.udec.collections.application.service.dto.command;

import java.util.List;

public record ActualizarEquipoComando(String id, String nombre, String pais, List<String> jugadores,
                                      String directorTecnico, List<String> personalLogistica) {
}
