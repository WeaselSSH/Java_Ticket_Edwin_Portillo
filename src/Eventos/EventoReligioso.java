package Eventos;

import Eventos.Evento;
import java.util.Calendar;

public class EventoReligioso extends Evento {

    private int cantidadConvertidos;

    public static final int cantidadMaxima = 30000;
    public static final int montoFijo = 2000;

    public EventoReligioso(String codigo, String titulo, String descripcion, Calendar fechaRealizar, double montoRenta) {
        super(codigo, titulo, descripcion, fechaRealizar, montoRenta);
        this.cantidadConvertidos = 0; //por defecto
    }

    @Override
    public String getTipo() {
        return "Religioso";
    }

    public void setCantidadConvertidos(int cantidad) {
        this.cantidadConvertidos = cantidad;
    }

    public int getSeguro() {
        return montoFijo;
    }

}
