package com.jcaa.udec.collections.entrypoint.controller.dto.response;

import java.util.List;
import java.util.stream.Collectors;

public record ObtenerEquiposResponse(List<EquipoResponse> equipos) {
    public boolean estaVacia() {
        return equipos.isEmpty();
    }

    @Override
    public String toString() {
        return equipos.stream()
                .map(EquipoResponse::toString)
                .collect(Collectors.joining(System.lineSeparator()));
    }
}