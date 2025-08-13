package Eventos;

import java.util.Calendar;

public abstract class Evento {

    protected final String codigo;
    protected String titulo;
    protected String descripcion;
    protected Calendar fechaRealizar;
    protected double montoRenta;
    protected boolean cancelado = false;
    protected boolean realizado = false;
    protected double multa = 0;
    protected boolean multaPagada = false;

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
        return (Calendar) fechaRealizar.clone();
    }

    public double getMontoRenta() {
        return montoRenta;
    }

    public boolean getCancelado() {
        return cancelado;
    }

    public boolean getRealizado() {
        return realizado;
    }

    public boolean setRealizado() {
        if (cancelado || realizado) {
            return false;
        }
        realizado = true;
        return true;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setFechaRealizar(Calendar fechaRealizar) {
        this.fechaRealizar = fechaRealizar;
    }

    public void setMontoRenta(double montoRenta) {
        this.montoRenta = montoRenta;
    }

    public double getMulta() {
        return multa;
    }

    public void setMulta(double multa) {
        this.multa = multa;
    }

    public boolean setCancelado() {
        if (realizado || cancelado) {
            return false;
        } else {
            cancelado = true;
            return true;
        }
    }

    public void pagarMulta() {
        this.multaPagada = true;
    }

    public boolean getMultaPagada() {
        return multaPagada;
    }

    @Override
    public String toString() {
        String fecha = new java.text.SimpleDateFormat("dd/MM/yyyy")
                .format(fechaRealizar.getTime());

        String base = "Código: " + codigo
                + "\nTítulo: " + titulo
                + "\nDescripción: " + descripcion
                + "\nFecha: " + fecha
                + "\nMonto de Renta: L." + montoRenta
                + "\nTipo: " + getTipo();

        String estado;
        if (cancelado) {
            estado = "\nEstado: Cancelado";
        } else if (realizado) {
            estado = "\nEstado: Realizado";
        } else {
            estado = "\nEstado: Por realizar";
        }

        String multaTexto = "";
        if (cancelado) {
            multaTexto = "\nMulta: L." + String.format("%.2f", multa)
                    + (multa > 0 ? (multaPagada ? " (pagada)" : " (pendiente)") : "");
        }

        return base + estado + multaTexto;
    }

}
