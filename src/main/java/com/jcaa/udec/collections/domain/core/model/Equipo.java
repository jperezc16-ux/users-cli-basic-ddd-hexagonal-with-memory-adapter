package com.jcaa.udec.collections.domain.core.model;

import com.jcaa.udec.collections.domain.core.exception.EquipoInvalidoException;
import com.jcaa.udec.collections.domain.core.valueobject.DirectorTecnico;
import com.jcaa.udec.collections.domain.core.valueobject.EquipoId;
import com.jcaa.udec.collections.domain.core.valueobject.NombreEquipo;
import com.jcaa.udec.collections.domain.core.valueobject.PaisEquipo;
import lombok.Builder;

import java.util.ArrayList;
import java.util.List;

public class Equipo {
    private final EquipoId id;
    private final NombreEquipo nombre;
    private final PaisEquipo pais;
    private final List<String> jugadores;
    private final DirectorTecnico directorTecnico;
    private final List<String> personalLogistica;

    @Builder
    public Equipo(String id, String nombre, String pais, List<String> jugadores,
                  String directorTecnico, List<String> personalLogistica) {
        this.id = new EquipoId(id);
        this.nombre = new NombreEquipo(nombre);
        this.pais = new PaisEquipo(pais);
        this.jugadores = limpiarLista(jugadores, "jugador");
        this.directorTecnico = new DirectorTecnico(directorTecnico);
        this.personalLogistica = limpiarLista(personalLogistica, "miembro de logistica");
    }

    private static List<String> limpiarLista(List<String> valores, String descripcion) {
        if (valores == null) {
            return List.of();
        }
        List<String> limpia = new ArrayList<>();
        for (String valor : valores) {
            if (valor == null || valor.isBlank()) {
                throw new EquipoInvalidoException("Hay un " + descripcion + " con nombre vacio");
            }
            limpia.add(valor.trim());
        }
        return List.copyOf(limpia);
    }

    public String getId() {
        return id.valor();
    }

    public String getNombre() {
        return nombre.valor();
    }

    public String getPais() {
        return pais.valor();
    }

    public List<String> getJugadores() {
        return jugadores;
    }

    public String getDirectorTecnico() {
        return directorTecnico.valor();
    }

    public List<String> getPersonalLogistica() {
        return personalLogistica;
    }
}
