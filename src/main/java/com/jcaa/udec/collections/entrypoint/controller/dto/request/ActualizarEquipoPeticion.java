package com.jcaa.udec.collections.entrypoint.controller.dto.request;

import java.util.List;

public record ActualizarEquipoPeticion(String id, String nombre, String pais, List<String> jugadores,
                                       String directorTecnico, List<String> personalLogistica) {
}