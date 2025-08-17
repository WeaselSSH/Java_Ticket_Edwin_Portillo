package Eventos;

import java.util.Calendar;
import java.util.ArrayList;
import Tipos.TipoMusica;

public final class EventoMusical extends Evento {

    private TipoMusica tipoMusica;
    private final ArrayList<String> staffTecnico;

    public static final int CANTIDAD_MAXIMA = 25_000;
    public static final double PORCENTAJE_SEGURO = 0.30;

    public EventoMusical(String codigo, String titulo, String descripcion, Calendar fechaRealizar,
            double montoRenta, TipoMusica tipoMusica) {
        super(codigo, titulo, descripcion, fechaRealizar, montoRenta);
        this.tipoMusica = tipoMusica;
        this.staffTecnico = new ArrayList<>();
    }

    @Override
    public String getTipo() {
        return "Musical";
    }

    public TipoMusica getTipoMusica() {
        return tipoMusica;
    }

    public void setTipoMusica(TipoMusica tipoMusica) {
        this.tipoMusica = tipoMusica;
    }

    public void agregarStaff(String nombre) {
        staffTecnico.add(nombre);
    }

    public boolean eliminarStaff(String nombre) {
        return staffTecnico.remove(nombre);
    }

    public ArrayList<String> getStaffTecnico() {
        return staffTecnico;
    }

    public double getSeguro() {
        return getMontoRenta() * PORCENTAJE_SEGURO;
    }

    @Override
    public String toString() {
        String base = super.toString()
                + "\nTipo de Música: " + tipoMusica
                + "\nSeguro (30%): L." + String.format("%.2f", getSeguro())
                + "\nStaff técnico: " + staffTecnico.size();

        String listaStaff = "";
        for (String s : staffTecnico) {
            if (s == null) {
                continue;
            }
            s = s.trim();
            if (s.isEmpty()) {
                continue;
            }
            listaStaff += (listaStaff.isEmpty() ? "" : ", ") + s;
        }

        if (!listaStaff.isEmpty()) {
            base += "\n- Staff: " + listaStaff;
        }

        return base;
    }

}
