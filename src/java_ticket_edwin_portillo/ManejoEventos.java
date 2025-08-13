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
        if (codigo == null) {
            return false;
        }

        if (usuario.getRol().equalsIgnoreCase("administrador")) {
            for (String c : ((Administrador) usuario).getEventosCreados()) {
                if (c != null && c.equalsIgnoreCase(codigo)) {
                    return true;
                }
            }
            return false;
        }

        if (usuario.getRol().equalsIgnoreCase("contenido")) {
            for (String c : ((Contenido) usuario).getEventosCreados()) {
                if (c != null && c.equalsIgnoreCase(codigo)) {
                    return true;
                }
            }
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

    public boolean editarEventoReligioso(Usuario usuario, String codigo, String titulo, String descripcion,
            Calendar fechaRealizar, double montoRenta, int convertidos) {

        Evento e = buscarEvento(codigo);

        if (e == null) {
            return false;
        }

        if (!usuarioCreador(usuario, codigo)) {
            return false;
        }

        EventoReligioso evt = (EventoReligioso) e;

        evt.setTitulo(titulo);
        evt.setDescripcion(descripcion);
        evt.setFechaRealizar(fechaRealizar);
        evt.setMontoRenta(montoRenta);

        evt.setCantidadConvertidos(convertidos);

        return true;
    }

    public boolean editarEventoMusical(Usuario usuario, String codigo, String titulo, String descripcion,
            Calendar fechaRealizar, double montoRenta, TipoMusica tipoMusica, ArrayList<String> StaffTecnico) {

        Evento e = buscarEvento(codigo);

        if (e == null) {
            return false;
        }

        if (!usuarioCreador(usuario, codigo)) {
            return false;
        }

        EventoMusical evt = (EventoMusical) e;

        evt.setTitulo(titulo);
        evt.setDescripcion(descripcion);
        evt.setFechaRealizar(fechaRealizar);
        evt.setMontoRenta(montoRenta);

        evt.setTipoMusica(tipoMusica);

        evt.getStaffTecnico().clear();

        for (String staffTecnico : StaffTecnico) {
            evt.agregarStaff(staffTecnico);
        }

        return true;
    }

    public boolean editarEventoDeportivo(Usuario usuario, String codigo, String titulo, String descripcion,
            Calendar fechaRealizar, double montoRenta, String equipo1, String equipo2, TipoDeporte tipoDeporte,
            String jugadores1[], String jugadores2[]) {

        Evento e = buscarEvento(codigo);

        if (e == null) {
            return false;
        }

        if (!usuarioCreador(usuario, codigo)) {
            return false;
        }

        EventoDeportivo evt = (EventoDeportivo) e;

        evt.setTitulo(titulo);
        evt.setDescripcion(descripcion);
        evt.setFechaRealizar(fechaRealizar);
        evt.setMontoRenta(montoRenta);
        evt.setEquipo1(equipo1);
        evt.setEquipo2(equipo2);
        evt.setTipoDeporte(tipoDeporte);

        evt.getJugadoresEquipo1().clear();
        evt.getJugadoresEquipo2().clear();

        for (String jugador : jugadores1) {
            evt.agregarJugadorEquipo1(jugador);
        }

        for (String jugador : jugadores2) {
            evt.agregarJugadorEquipo2(jugador);
        }

        return true;
    }

    public boolean actualizarJugadoresDeportivo(Usuario usuario, String codigo,
            String[] jugadores1, String[] jugadores2) {
        Evento evt = buscarEvento(codigo);
        if (evt == null || !(evt instanceof EventoDeportivo)) {
            return false;
        }

        if (!usuarioCreador(usuario, codigo)) {
            return false;
        }

        EventoDeportivo d = (EventoDeportivo) evt;
        d.getJugadoresEquipo1().clear();
        d.getJugadoresEquipo2().clear();
        if (jugadores1 != null) {
            for (String j : jugadores1) {
                if (j != null && !j.trim().isEmpty()) {
                    d.agregarJugadorEquipo1(j.trim());
                }
            }
        }
        if (jugadores2 != null) {
            for (String j : jugadores2) {
                if (j != null && !j.trim().isEmpty()) {
                    d.agregarJugadorEquipo2(j.trim());
                }
            }
        }
        return true;
    }

    public boolean actualizarStaffMusical(Usuario usuario, String codigo, String[] staff) {
        Evento evt = buscarEvento(codigo);
        if (evt == null || !(evt instanceof EventoMusical)) {
            return false;
        }

        if (!usuarioCreador(usuario, codigo)) {
            return false;
        }

        EventoMusical m = (EventoMusical) evt;
        m.getStaffTecnico().clear();

        if (staff != null) {
            for (String s : staff) {
                if (s != null) {
                    String nombre = s.trim();
                    if (!nombre.isEmpty()) {
                        m.agregarStaff(nombre);
                    }
                }
            }
        }
        return true;
    }

    private boolean mismaFecha(Calendar a, Calendar b) {
        return a.get(Calendar.YEAR) == b.get(Calendar.YEAR)
                && a.get(Calendar.DAY_OF_YEAR) == b.get(Calendar.DAY_OF_YEAR);
    }

    public Evento choqueFecha(Calendar fecha) {
        for (Evento evt : eventos) {
            if (!evt.getCancelado() && mismaFecha(evt.getFechaRealizar(), fecha)) {
                return evt;
            }
        }
        return null;
    }

}