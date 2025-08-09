package Usuarios;

import java.util.ArrayList;

public final class Contenido extends Usuario {

    private final ArrayList<String> eventosCreados = new ArrayList<>();

    public Contenido(String nombre, String usuario, String contrasenia, int edad) {
        super(nombre, usuario, contrasenia, edad);
    }

    @Override
    public String getRol() {
        return "contenido";
    }

    public ArrayList<String> getEventosCreados() {
        return eventosCreados;
    }

    public boolean agregarEvento(String codigoEvento) {
        if (codigoEvento == null || codigoEvento.trim().isEmpty()) {
            return false;
        }
        return eventosCreados.add(codigoEvento);
    }

    public boolean eliminarEvento(String codigoEvento) {
        return eventosCreados.remove(codigoEvento);
    }
}
