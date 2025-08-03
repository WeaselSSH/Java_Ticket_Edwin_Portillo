package java_ticket_edwin_portillo;

import java.util.ArrayList;
import java.util.Calendar;

public class ManejoEventos {

    public static ArrayList<Evento> eventos = new ArrayList<>();

    public void crearEventoReligioso(Usuario usuario, String codigo, String titulo, String descripcion,
            Calendar fechaRealizar, double montoRenta) {

        eventos.add(new EventoReligioso(codigo, titulo, descripcion, fechaRealizar, montoRenta));

        agregarCodigoUsuario(usuario, codigo);
    }

    private void agregarCodigoUsuario(Usuario usuario, String codigo) {
        if (usuario instanceof Administrador) {
            ((Administrador) usuario).agregarEvento(codigo);
        } else if (usuario instanceof Contenido) {
            ((Contenido) usuario).agregarEvento(codigo);
        }
    }

}
