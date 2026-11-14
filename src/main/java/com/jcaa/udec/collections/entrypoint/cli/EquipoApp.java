package com.jcaa.udec.collections.entrypoint.cli;

import com.jcaa.udec.collections.adapter.persistence.memory.ActualizarEquipoAdapter;
import com.jcaa.udec.collections.adapter.persistence.memory.EliminarEquipoAdapter;
import com.jcaa.udec.collections.adapter.persistence.memory.GuardarEquipoAdapter;
import com.jcaa.udec.collections.adapter.persistence.memory.ObtenerEquiposAdapter;
import com.jcaa.udec.collections.application.service.ActualizarEquipoService;
import com.jcaa.udec.collections.application.service.AgregarEquipoService;
import com.jcaa.udec.collections.application.service.EliminarEquipoService;
import com.jcaa.udec.collections.application.service.ObtenerEquipoService;
import com.jcaa.udec.collections.entrypoint.controller.EquipoControlador;

public class EquipoApp {
    public static void main(String[] args) {
        EquipoControlador controlador = new EquipoControlador(
                new AgregarEquipoService(new GuardarEquipoAdapter()),
                new ObtenerEquipoService(new ObtenerEquiposAdapter()),
                new ActualizarEquipoService(new ActualizarEquipoAdapter()),
                new EliminarEquipoService(new EliminarEquipoAdapter()));
        new EquipoCli(controlador).ejecutarAccion();
    }
}