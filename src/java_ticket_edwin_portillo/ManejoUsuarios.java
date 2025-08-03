package java_ticket_edwin_portillo;

import java.util.ArrayList;

public class ManejoUsuarios {

    public static Usuario usuarioLogeado;
    private ArrayList<Usuario> usuarios;

    public ManejoUsuarios() {
        usuarios = new ArrayList<>();
        Usuario admin = new Administrador("Admin Default", "admin", "supersecreto", 30);
        usuarios.add(admin);
    }

    public Usuario buscarUsuario(String usuario) {
        for (Usuario u : usuarios) {
            if (u.getUsuario().equals(usuario)) {
                return u;
            }
        }
        return null;
    }

    public boolean contraseniaValida(String contrasenia) {
        if (contrasenia.length() < 8) {
            return false;
        }

        boolean contieneLetra = false;
        boolean contieneNumero = false;
        boolean contieneSimbolo = false;

        for (char c : contrasenia.toCharArray()) {
            if (Character.isLetter(c)) {
                contieneLetra = true;
            } else if (Character.isDigit(c)) {
                contieneNumero = true;
            } else {
                contieneSimbolo = true;
            }
        }

        return contieneLetra && contieneNumero && contieneSimbolo;

    }

    public boolean iniciarSesion(String usuario, String contrasenia) {
        Usuario u = buscarUsuario(usuario);
        if (u != null && u.verificarContrasenia(contrasenia)) {
            usuarioLogeado = u;
            return true;
        }
        return false;
    }

    public boolean registrarUsuario(String rol, String nombre, String usuario, String contrasenia, int edad) {
        if (buscarUsuario(usuario) != null || !contraseniaValida(contrasenia)) {
            return false;
        }

        switch (rol.toLowerCase()) {
            case "administrador":
                usuarios.add(new Administrador(nombre, usuario, contrasenia, edad));
                break;
            case "contenido":
                usuarios.add(new Contenido(nombre, usuario, contrasenia, edad));
                break;
            case "limitado":
                usuarios.add(new Limitado(nombre, usuario, contrasenia, edad));
                break;
        }
        return true;
    }

    public boolean eliminarUsuario(String usuario) {
        Usuario u = buscarUsuario(usuario);
        if (u != null) {
            usuarios.remove(u);
            return true;
        }
        return false;
    }

}
