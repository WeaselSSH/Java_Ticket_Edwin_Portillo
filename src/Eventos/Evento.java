package Eventos;

import java.util.Calendar;

public abstract class Evento {

    protected String codigo;
    protected String titulo;
    protected String descripcion;
    protected Calendar fechaRealizar;
    protected double montoRenta;
    protected boolean cancelado = false;
    protected boolean realizado = false;
    protected double multa = 0;
    
    protected boolean eliminado = false;

    public Evento(String codigo, String titulo, String descripcion, Calendar fechaRealizar, double montoRenta) {
        this.codigo = codigo;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fechaRealizar = fechaRealizar;
        this.montoRenta = montoRenta;
    }

    public abstract String getTipo();

    public String getCodigo() {
        return codigo;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Calendar getFechaRealizar() {
        return fechaRealizar;
    }

    public double getMontoRenta() {
        return montoRenta;
    }

    public boolean Cancelado() {
        return cancelado;
    }

    public void setCancelado() {
        cancelado = true;
    }

    public boolean Realizado() {
        return realizado;
    }

    public void setRealizado() {
        this.realizado = true;
    }

    public void setMulta(double multa) {
        this.multa = multa;
    }

    public double getMulta() {
        return multa;
    }
    
    public void setEliminado() {
        eliminado = true;
    }
    
    public boolean getEliminado() {
        return eliminado;
    }

}
