package java_ticket_edwin_portillo;

import java.util.ArrayList;

public final class Contenido extends Usuario {

    private ArrayList<String> eventosCreados;

    public Contenido(String nombre, String usuario, String contrasenia, int edad) {
        super(nombre, usuario, contrasenia, edad);
        this.eventosCreados = new ArrayList<>();
    }

    @Override
    public String getRol() {
        return "contenido";
    }

    public ArrayList<String> getEventosCreados() {
        return eventosCreados;
    }

    public void agregarEvento(String idEvento) {
        eventosCreados.add(idEvento);
    }

    public void eliminarEvento(String idEvento) {
        eventosCreados.remove(idEvento);
    }
}
