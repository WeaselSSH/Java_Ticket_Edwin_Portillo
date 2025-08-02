package java_ticket_edwin_portillo;

import java.util.ArrayList;
import java.util.Calendar;

public class EventoDeportivo extends Evento {

    private String equipo1;
    private String equipo2;
    private TipoDeporte tipo;
    private final ArrayList<String> jugadoresEquipo1;
    private final ArrayList<String> jugadoresEquipo2;

    public static final int cantidadMaxima = 20000;

    public EventoDeportivo(String codigo, String titulo, String descripcion, Calendar fechaRealizar, double montoRenta,
                           String equipo1, String equipo2, TipoDeporte tipo) {
        
        super(codigo, titulo, descripcion, fechaRealizar, montoRenta);
        this.equipo1 = equipo1;
        this.equipo2 = equipo2;
        this.tipo = tipo;
        this.jugadoresEquipo1 = new ArrayList<>();
        this.jugadoresEquipo2 = new ArrayList<>();
    }

    @Override
    public String getTipo() {
        return "Deportivo";
    }

    public void agregarJugadorEquipo1(String jugador) {
        jugadoresEquipo1.add(jugador);
    }

    public void agregarJugadorEquipo2(String jugador) {
        jugadoresEquipo2.add(jugador);
    }

}
