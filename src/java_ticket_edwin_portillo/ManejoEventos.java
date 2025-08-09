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

    //El propósito de esta variable es usar una sola instancia por todo el proyecto
    private static final ManejoEventos instanciaEventos = new ManejoEventos();

    public static ManejoEventos getInstancia() { //get de la instancia previa
        return instanciaEventos;
    }

    private final ArrayList<Evento> eventos = new ArrayList<>();
    private int contador = 0;

    public String codigoSiguiente() {
        return "EVT" + contador;
    }

    public Evento buscarEvento(String codigoEvento) {
        for (Evento evt : eventos) {
            if (evt.getCodigo().equalsIgnoreCase(codigoEvento)) {
                return evt;
            }
        }
        return null;
    }

    private boolean usuarioCreador(Usuario usuario, String codigo) {
        if (usuario.getRol().equalsIgnoreCase("administrador")) {
            return ((Administrador) usuario).getEventosCreados().contains(codigo);
        }
        if (usuario.getRol().equalsIgnoreCase("contenido")) {
            return ((Contenido) usuario).getEventosCreados().contains(codigo);
        }
        return false;
    }

    private boolean unDiaOMenos(Calendar ahora, Calendar fechaEvento) {
        long diferenciaTiempo = fechaEvento.getTimeInMillis() - ahora.getTimeInMillis();
        return diferenciaTiempo <= 86400000 && diferenciaTiempo >= 0; //también compara que no sea diferencia negativa
    }

    public boolean cancelarEvento(Usuario usuario, String codigoEvento) {
        Evento evt = buscarEvento(codigoEvento);

        if (evt == null) {
            return false;
        }

        if (evt.getRealizado() || evt.getCancelado()) {
            return false;
        }

        if (!usuarioCreador(usuario, codigoEvento)) {
            return false;
        }

        if (!(evt.getTipo().equalsIgnoreCase("religioso"))) {
            if (unDiaOMenos(Calendar.getInstance(), evt.getFechaRealizar())) {
                evt.setMulta(evt.getMontoRenta() * 0.50);
            }
        }

        return evt.setCancelado();
    }

    private void agregarCodigoUsuario(Usuario usuario, String codigo) {
        if (usuario.getRol().equalsIgnoreCase("administrador")) {
            ((Administrador) usuario).agregarEvento(codigo);
        } else if (usuario.getRol().equalsIgnoreCase("contenido")) {
            ((Contenido) usuario).agregarEvento(codigo);
        }
    }

    public void crearEventoReligioso(Usuario usuario, String codigo, String titulo, String descripcion,
            Calendar fechaRealizar, double montoRenta) {

        eventos.add(new EventoReligioso(codigo, titulo, descripcion, fechaRealizar, montoRenta));

        agregarCodigoUsuario(usuario, codigo);

        contador++;
    }

    public void crearEventoMusical(Usuario usuario, String codigo, String titulo, String descripcion,
            Calendar fechaRealizar, double montoRenta, TipoMusica tipoMusica) {

        eventos.add(new EventoMusical(codigo, titulo, descripcion, fechaRealizar, montoRenta, tipoMusica));

        agregarCodigoUsuario(usuario, codigo);

        contador++;
    }

    public void crearEventoDeportivo(Usuario usuario, String codigo, String titulo, String descripcion,
            Calendar fechaRealizar, double montoRenta, String equipo1, String equipo2, TipoDeporte tipoDeporte) {

        eventos.add(new EventoDeportivo(codigo, titulo, descripcion, fechaRealizar, montoRenta, equipo1, equipo2,
                tipoDeporte));

        agregarCodigoUsuario(usuario, codigo);

        contador++;
    }

    public String verDatos(String codigo) {
        Evento evtVer = buscarEvento(codigo);
        return (evtVer != null) ? evtVer.toString() : "Evento no encontrado";
    }
}
