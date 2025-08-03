package java_ticket_edwin_portillo;

import java.util.ArrayList;
import java.util.Calendar;

public class ManejoEventos {

    public static ArrayList<Evento> eventos = new ArrayList<>();
    private static int contador = 0;
    
    public static String generarCodigo() {
        String codigoGenerado = "EVT" + contador;
        contador++;
        
        return codigoGenerado;
    }

    public void crearEventoReligioso(Usuario usuario, String codigo, String titulo, String descripcion,
            Calendar fechaRealizar, double montoRenta) {

        eventos.add(new EventoReligioso(codigo, titulo, descripcion, fechaRealizar, montoRenta));

        agregarCodigoUsuario(usuario, codigo);
    }

    public void crearEventoMusical(Usuario usuario, String codigo, String titulo, String descripcion,
            Calendar fechaRealizar, double montoRenta, TipoMusica tipoMusica) {

        eventos.add(new EventoMusical(codigo, titulo, descripcion, fechaRealizar, montoRenta, tipoMusica));

        agregarCodigoUsuario(usuario, codigo);
    }

    public void crearEventoDeportivo(Usuario usuario, String codigo, String titulo, String descripcion,
            Calendar fechaRealizar, double montoRenta, String equipo1, String equipo2, TipoDeporte tipoDeporte) {

        eventos.add(new EventoDeportivo(codigo, titulo, descripcion, fechaRealizar, montoRenta, equipo1,
                equipo2, tipoDeporte));

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
