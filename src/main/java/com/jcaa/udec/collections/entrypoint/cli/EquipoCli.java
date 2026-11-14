package com.jcaa.udec.collections.entrypoint.cli;

import com.jcaa.udec.collections.domain.core.exception.EquipoInvalidoException;
import com.jcaa.udec.collections.domain.core.exception.EquipoNoExisteException;
import com.jcaa.udec.collections.domain.core.exception.EquipoYaExisteException;
import com.jcaa.udec.collections.domain.core.valueobject.DirectorTecnico;
import com.jcaa.udec.collections.domain.core.valueobject.EquipoId;
import com.jcaa.udec.collections.domain.core.valueobject.NombreEquipo;
import com.jcaa.udec.collections.domain.core.valueobject.PaisEquipo;
import com.jcaa.udec.collections.entrypoint.controller.EquipoControlador;
import com.jcaa.udec.collections.entrypoint.controller.dto.request.ActualizarEquipoPeticion;
import com.jcaa.udec.collections.entrypoint.controller.dto.request.RegistrarEquipoPeticion;
import com.jcaa.udec.collections.entrypoint.controller.dto.response.ObtenerEquiposResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EquipoCli {
    private static final int OPCION_AGREGAR = 1;
    private static final int OPCION_BUSCAR = 2;
    private static final int OPCION_MOSTRAR_TODOS = 3;
    private static final int OPCION_ACTUALIZAR = 4;
    private static final int OPCION_ELIMINAR = 5;
    private static final int OPCION_SALIR = 6;

    private static final String TEXTO_TITULO = "** GESTION DE EQUIPOS DEL CAMPEONATO **";
    private static final String TITULO_REGISTRO = "** INGRESE LOS DATOS DEL NUEVO EQUIPO **";
    private static final String TITULO_ACTUALIZACION = "** INGRESE LOS NUEVOS DATOS DEL EQUIPO **";
    private static final String SEPARADOR = "- - - - - - - - - ";
    private static final String MENSAJE_OPCION_INVALIDA = "Opcion [%s] invalida%n";
    private static final String MENSAJE_ERROR = "ERROR: ";
    private static final String MENSAJE_REGISTRO_EXITOSO = "Equipo registrado correctamente.";
    private static final String MENSAJE_ACTUALIZACION_EXITOSA = "Equipo actualizado correctamente.";
    private static final String MENSAJE_ELIMINACION_EXITOSA = "Equipo eliminado correctamente.";
    private static final String MENSAJE_LISTA_VACIA = "No hay equipos registrados.";
    private static final String MENSAJE_DESPEDIDA = "Esperamos tu regreso. Bye, Bye";

    private final EquipoControlador equipoControlador;
    private final Scanner entrada;

    public EquipoCli(EquipoControlador equipoControlador) {
        this(equipoControlador, new Scanner(System.in));
    }

    EquipoCli(EquipoControlador equipoControlador, Scanner entrada) {
        this.equipoControlador = equipoControlador;
        this.entrada = entrada;
    }

    public int obtenerOpcionMenu() {
        while (true) {
            mostrarMenu();
            String valorIngresado = limpiarEntrada(entrada.nextLine());
            try {
                int opcion = Integer.parseInt(valorIngresado);
                if (opcion >= OPCION_AGREGAR && opcion <= OPCION_SALIR) {
                    return opcion;
                }
            } catch (NumberFormatException excepcion) {
                // No era un numero: se informa abajo como opcion invalida.
            }
            System.out.printf(MENSAJE_OPCION_INVALIDA, valorIngresado);
        }
    }

    public void ejecutarAccion() {
        boolean continuar = true;
        while (continuar) {
            int opcion = obtenerOpcionMenu();
            try {
                switch (opcion) {
                    case OPCION_AGREGAR:
                        registrarEquipo();
                        break;
                    case OPCION_BUSCAR:
                        mostrarEquipoPorId();
                        break;
                    case OPCION_MOSTRAR_TODOS:
                        mostrarTodosLosEquipos();
                        break;
                    case OPCION_ACTUALIZAR:
                        actualizarEquipo();
                        break;
                    case OPCION_ELIMINAR:
                        eliminarEquipo();
                        break;
                    case OPCION_SALIR:
                        continuar = false;
                        break;
                    default:
                        break;
                }
            } catch (EquipoNoExisteException | EquipoYaExisteException | EquipoInvalidoException excepcion) {
                System.out.println(MENSAJE_ERROR + excepcion.getMessage());
            }
        }
        System.out.println(MENSAJE_DESPEDIDA);
    }

    private void mostrarMenu() {
        System.out.println();
        System.out.println(TEXTO_TITULO);
        System.out.println(SEPARADOR);
        System.out.println("Opciones:");
        System.out.println(SEPARADOR);
        System.out.println("1 - Agregar");
        System.out.println("2 - Buscar por Id");
        System.out.println("3 - Ver todos");
        System.out.println("4 - Actualizar");
        System.out.println("5 - Eliminar");
        System.out.println("6 - Salir");
        System.out.print("Ingrese el numero de la opcion: ");
    }

    private void registrarEquipo() {
        System.out.println();
        System.out.println(TITULO_REGISTRO);
        equipoControlador.registrar(new RegistrarEquipoPeticion(capturarId(), capturarNombre(), capturarPais(),
                capturarJugadores(), capturarDirectorTecnico(), capturarPersonalLogistica()));
        System.out.println(MENSAJE_REGISTRO_EXITOSO);
    }

    private void mostrarEquipoPorId() {
        System.out.println(equipoControlador.obtenerPorId(capturarId()));
    }

    private void mostrarTodosLosEquipos() {
        ObtenerEquiposResponse response = equipoControlador.obtenerTodos();
        if (response.estaVacia()) {
            System.out.println(MENSAJE_LISTA_VACIA);
        } else {
            System.out.println(response);
        }
    }

    private void actualizarEquipo() {
        System.out.println();
        System.out.println(TITULO_ACTUALIZACION);
        equipoControlador.actualizar(new ActualizarEquipoPeticion(capturarId(), capturarNombre(), capturarPais(),
                capturarJugadores(), capturarDirectorTecnico(), capturarPersonalLogistica()));
        System.out.println(MENSAJE_ACTUALIZACION_EXITOSA);
    }

    private void eliminarEquipo() {
        equipoControlador.eliminar(capturarId());
        System.out.println(MENSAJE_ELIMINACION_EXITOSA);
    }

    private String capturarId() {
        while (true) {
            System.out.print("ID: ");
            String id = limpiarEntrada(entrada.nextLine());
            if (esValido(() -> new EquipoId(id))) {
                return id;
            }
            System.out.println("ID INVALIDO: debe ser un numero entero");
        }
    }

    private String capturarNombre() {
        while (true) {
            System.out.print("NOMBRE DEL EQUIPO: ");
            String nombre = limpiarEntrada(entrada.nextLine());
            if (esValido(() -> new NombreEquipo(nombre))) {
                return nombre;
            }
            System.out.println("NOMBRE INVALIDO: minimo 3 caracteres");
        }
    }

    private String capturarPais() {
        while (true) {
            System.out.print("PAIS: ");
            String pais = limpiarEntrada(entrada.nextLine());
            if (esValido(() -> new PaisEquipo(pais))) {
                return pais;
            }
            System.out.println("PAIS INVALIDO: minimo 2 caracteres");
        }
    }

    private String capturarDirectorTecnico() {
        while (true) {
            System.out.print("DIRECTOR TECNICO: ");
            String director = limpiarEntrada(entrada.nextLine());
            if (esValido(() -> new DirectorTecnico(director))) {
                return director;
            }
            System.out.println("DIRECTOR TECNICO INVALIDO: minimo 3 caracteres");
        }
    }

    private List<String> capturarJugadores() {
        return capturarLista("JUGADORES (separados por coma): ");
    }

    private List<String> capturarPersonalLogistica() {
        return capturarLista("PERSONAL DE LOGISTICA (separado por coma): ");
    }

    private List<String> capturarLista(String solicitud) {
        System.out.print(solicitud);
        String linea = limpiarEntrada(entrada.nextLine());
        List<String> elementos = new ArrayList<>();
        for (String parte : linea.split(",")) {
            if (!parte.isBlank()) {
                elementos.add(parte.trim());
            }
        }
        return elementos;
    }

    private static String limpiarEntrada(String valor) {
        return valor.replace("\ufeff", "").trim();
    }

    private static boolean esValido(Runnable validacion) {
        try {
            validacion.run();
            return true;
        } catch (EquipoInvalidoException excepcion) {
            return false;
        }
    }
}