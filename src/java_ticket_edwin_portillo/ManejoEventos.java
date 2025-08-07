package java_ticket_edwin_portillo;

import Tipos.TipoMusica;
import Tipos.TipoDeporte;
import Eventos.EventoReligioso;
import Eventos.EventoMusical;
import Eventos.EventoDeportivo;
import Eventos.Evento;
import Usuarios.Contenido;
import Usuarios.Administrador;
import Usuarios.Usuario;
import java.util.ArrayList;
import java.util.Calendar;

public class ManejoEventos {

    public static ArrayList<Evento> eventos = new ArrayList<>();
    private static int contador = 0;

    public static String generarCodigo() {
        return "EVT" + (contador++);
    }

    public static String codigoSiguiente() {
        return "EVT" + contador;
    }

    public Evento buscarEvento(String idEvento) {
        for (Evento evt : eventos) {
            if (evt.getCodigo().equalsIgnoreCase(idEvento) && !evt.getEliminado()) {
                return evt;
            }
        }
        return null;
    }

    public boolean eliminarEvento(String idEvento) {
        Evento eventoEliminar = buscarEvento(idEvento);
        if (eventoEliminar != null) {
            eventoEliminar.setEliminado();
            return true;
        }
        return false;
    }

    public void crearEventoReligioso(Usuario usuario, String titulo, String descripcion,
            Calendar fechaRealizar, double montoRenta) {

        String codigo = generarCodigo();
        eventos.add(new EventoReligioso(codigo, titulo, descripcion, fechaRealizar, montoRenta));
        agregarCodigoUsuario(usuario, codigo);
    }

    public void crearEventoMusical(Usuario usuario, String titulo, String descripcion,
            Calendar fechaRealizar, double montoRenta, TipoMusica tipoMusica) {

        String codigo = generarCodigo();
        eventos.add(new EventoMusical(codigo, titulo, descripcion, fechaRealizar, montoRenta, tipoMusica));
        agregarCodigoUsuario(usuario, codigo);
    }

    public void crearEventoDeportivo(Usuario usuario, String titulo, String descripcion,
            Calendar fechaRealizar, double montoRenta, String equipo1, String equipo2, TipoDeporte tipoDeporte) {

        String codigo = generarCodigo();
        eventos.add(new EventoDeportivo(codigo, titulo, descripcion, fechaRealizar, montoRenta, equipo1,
                equipo2, tipoDeporte));
        agregarCodigoUsuario(usuario, codigo);
    }

    public void agregarCodigoUsuario(Usuario usuario, String codigo) {
        if (usuario instanceof Administrador) {
            ((Administrador) usuario).agregarEvento(codigo);
        } else if (usuario instanceof Contenido) {
            ((Contenido) usuario).agregarEvento(codigo);
        }
    }

    public String verDatos(String codigo) {
        Evento eventoVer = buscarEvento(codigo);
        return eventoVer.toString();
    }
}
