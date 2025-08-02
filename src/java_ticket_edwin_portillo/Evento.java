package java_ticket_edwin_portillo;

import java.util.Calendar;

public abstract class Evento {

    protected String codigo;
    protected String titulo;
    protected String descripcion;
    protected Calendar fechaRealizar;
    protected double montoRenta;
    protected boolean cancelado = false;
    protected boolean realizado = false;

    public Evento(String codigo, String titulo, String descripcion, Calendar fechaRealizar, double montoRenta) {
        this.codigo = codigo;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fechaRealizar = fechaRealizar;
        this.montoRenta = montoRenta;
    }

    public abstract String getTipo();

    public boolean getCancelado() {
        return cancelado;
    }

    public boolean getRealizado() {
        return realizado;
    }

    public void setRealizado() {
        this.realizado = true;
    }

    public void cancelar() {
        this.cancelado = true;
    }

}
