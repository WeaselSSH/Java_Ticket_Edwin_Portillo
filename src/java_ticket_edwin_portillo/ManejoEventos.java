package java_ticket_edwin_portillo;

import java.util.ArrayList;
import java.util.Calendar;

public class ManejoEventos {

    private ArrayList<Evento> eventos;

    public ManejoEventos() {
        eventos = new ArrayList<>();
    }

    public void crearEventoReligioso(Usuario usuario, String codigo, String titulo, String descripcion,
            Calendar fechaRealizar, double montoRenta) {

        eventos.add(new EventoReligioso(codigo, titulo, descripcion, fechaRealizar, montoRenta));

        if (usuario instanceof Administrador) {
            Administrador usuarioAdministrador = (Administrador) usuario;
            usuarioAdministrador.agregarEvento(codigo);
        }

        if (usuario instanceof Contenido) {
            Contenido usuarioAdministrador = (Contenido) usuario;
            usuarioAdministrador.agregarEvento(codigo);
        }
    }
}
