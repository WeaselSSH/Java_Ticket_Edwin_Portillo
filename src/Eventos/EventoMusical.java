package Eventos;

import Eventos.Evento;
import java.util.Calendar;
import java.util.ArrayList;
import Tipos.TipoMusica;

public class EventoMusical extends Evento {

    private TipoMusica tipoMusica;
    private ArrayList<String> staffTecnico;
    private double seguro;

    public static final int cantidadMaxima = 25000;
    public static final double porcentajeSeguro = 0.30;

    public EventoMusical(String codigo, String titulo, String descripcion, Calendar fechaRealizar, double montoRenta,
            TipoMusica tipoMusica) {

        super(codigo, titulo, descripcion, fechaRealizar, montoRenta);
        this.tipoMusica = tipoMusica;
        staffTecnico = new ArrayList<>();
        this.seguro = montoRenta * porcentajeSeguro;
    }

    @Override
    public String getTipo() {
        return "Musical";
    }

    public void agregarStaff(String nombre) {
        staffTecnico.add(nombre);
    }
    
    public double getSeguro() {
        return seguro;
    }
}
