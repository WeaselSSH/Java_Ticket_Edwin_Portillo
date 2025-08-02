package java_ticket_edwin_portillo;

public final class Limitado extends Usuario {

    public Limitado(String nombre, String usuario, String contrasenia, int edad) {
        super(nombre, usuario, contrasenia, edad);
    }

    @Override
    public String getRol() {
        return "limitado";
    }
}
