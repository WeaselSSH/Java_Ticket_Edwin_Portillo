package Usuarios;

public abstract class Usuario {

    protected String nombre;
    protected String usuario;
    protected String contrasenia;
    protected int edad;

    protected boolean eliminado = false;

    public Usuario(String nombre, String usuario, String contrasenia, int edad) {
        this.nombre = nombre;
        this.usuario = usuario;
        this.contrasenia = contrasenia;
        this.edad = edad;
    }

    public abstract String getRol();

    public final String getNombre() {
        return nombre;
    }

    public final String getUsuario() {
        return usuario;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public final int getEdad() {
        return edad;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public void setEdad(int edad) {
        if (edad >= 0) {
            this.edad = edad;
        }
    }

    public boolean verificarContrasenia(String input) {
        return contrasenia != null && contrasenia.equals(input);
    }

    public void setEliminado() {
        eliminado = true;
    }

    public final boolean getEliminado() {
        return eliminado;
    }

    @Override
    public String toString() {
        return "Usuario: " + usuario
                + "\nNombre: " + nombre
                + "\nRol: " + getRol()
                + "\nEdad: " + edad
                + (eliminado ? "\nELIMINADO" : "");
    }
}
