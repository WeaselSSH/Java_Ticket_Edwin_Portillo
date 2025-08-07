package Eventos;

import Eventos.Evento;
import java.util.ArrayList;
import java.util.Calendar;
import Tipos.TipoDeporte;

public class EventoDeportivo extends Evento {

    private TipoDeporte tipoDeporte;
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

    @Override
    public String toString() {
        return super.toString()
                + "\nEquipo 1: " + equipo1
                + "\nEquipo 2: " + equipo2
                + "\nTipo de Deporte: " + tipoDeporte;
    }

    public void agregarJugadorEquipo1(String jugador) {
        jugadoresEquipo1.add(jugador);
    }

    public void agregarJugadorEquipo2(String jugador) {
        jugadoresEquipo2.add(jugador);
    }

}
