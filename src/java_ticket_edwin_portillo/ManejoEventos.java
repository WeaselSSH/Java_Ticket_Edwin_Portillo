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

    public void crearEventoMusical(Usuario usuario, String codigo, String titulo, String descripcion,
            Calendar fechaRealizar, double montoRenta, TipoMusica tipoMusica) {

        EventoMusical evento = new EventoMusical(codigo, titulo, descripcion, fechaRealizar, montoRenta, tipoMusica);
        eventos.add(evento);

        agregarCodigoUsuario(usuario, codigo);
    }

    public void crearEventoDeportivo(Usuario usuario, String codigo, String titulo, String descripcion,
            Calendar fechaRealizar, double montoRenta, String equipo1, String equipo2, TipoDeporte tipoDeporte) {

        EventoDeportivo evento = new EventoDeportivo(codigo, titulo, descripcion, fechaRealizar, montoRenta,
                equipo1, equipo2, tipoDeporte);
        eventos.add(evento);

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
