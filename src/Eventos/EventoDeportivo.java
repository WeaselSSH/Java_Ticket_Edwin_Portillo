package Eventos;

import java.util.ArrayList;
import java.util.Calendar;
import Tipos.TipoDeporte;

public class EventoDeportivo extends Evento {

    private TipoDeporte tipoDeporte;
    private String equipo1;
    private String equipo2;

    private final ArrayList<String> jugadoresEquipo1;
    private final ArrayList<String> jugadoresEquipo2;

    public static final int CANTIDAD_MAXIMA = 20000;

    public EventoDeportivo(String codigo, String titulo, String descripcion, Calendar fechaRealizar, double montoRenta,
            String equipo1, String equipo2, TipoDeporte tipo) {

        super(codigo, titulo, descripcion, fechaRealizar, montoRenta);
        this.equipo1 = equipo1;
        this.equipo2 = equipo2;
        this.tipoDeporte = tipo;
        this.jugadoresEquipo1 = new ArrayList<>();
        this.jugadoresEquipo2 = new ArrayList<>();
    }

    @Override
    public String getTipo() {
        return "Deportivo";
    }

    public TipoDeporte getTipoDeporte() {
        return tipoDeporte;
    }

    public void setTipoDeporte(TipoDeporte tipoDeporte) {
        this.tipoDeporte = tipoDeporte;
    }

    public String getEquipo1() {
        return equipo1;
    }

    public void setEquipo1(String equipo1) {
        this.equipo1 = equipo1;
    }

    public String getEquipo2() {
        return equipo2;
    }

    public void setEquipo2(String equipo2) {
        this.equipo2 = equipo2;
    }

    public void agregarJugadorEquipo1(String jugador) {
        jugadoresEquipo1.add(jugador);
    }

    public void agregarJugadorEquipo2(String jugador) {
        jugadoresEquipo2.add(jugador);
    }

    public boolean eliminarJugadorEquipo1(String jugador) {
        return jugadoresEquipo1.remove(jugador);
    }

    public boolean eliminarJugadorEquipo2(String jugador) {
        return jugadoresEquipo2.remove(jugador);
    }

    public ArrayList<String> getJugadoresEquipo1() {
        return jugadoresEquipo1;
    }

    public ArrayList<String> getJugadoresEquipo2() {
        return jugadoresEquipo2;
    }

    @Override
    public String toString() {
        return super.toString()
                + "\nTipo de Deporte: " + tipoDeporte
                + "\nEquipo 1: " + equipo1 + " (jugadores: " + jugadoresEquipo1.size() + ")"
                + "\nEquipo 2: " + equipo2 + " (jugadores: " + jugadoresEquipo2.size() + ")";
    }

}
