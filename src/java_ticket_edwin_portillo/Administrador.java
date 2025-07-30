package java_ticket_edwin_portillo;

import java.util.ArrayList;

public final class Administrador extends Usuario {
    private ArrayList<Integer> eventosCreados;

    public Administrador(String nombre, String usuario, String contrasenia, int edad) {
        super(nombre, usuario, contrasenia, edad);
        this.eventosCreados = new ArrayList<>();
    }

    @Override
    public String getRol() {
        return "Administrador";
    }

    public ArrayList<Integer> getEventosCreados() {
        return eventosCreados;
    }

    public void agregarEvento(Integer idEvento) {
        eventosCreados.add(idEvento);
    }

    public void eliminarEvento(Integer idEvento) {
        eventosCreados.remove(idEvento);
    }
}
