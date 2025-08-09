package java_ticket_edwin_portillo;

import Usuarios.Limitado;
import Usuarios.Contenido;
import Usuarios.Administrador;
import Usuarios.Usuario;
import java.util.ArrayList;

public class ManejoUsuarios {

    //Misma idea que en manejo eventos
    private static final ManejoUsuarios instManejoUsuarios = new ManejoUsuarios();

    private final ArrayList<Usuario> usuarios = new ArrayList<>();
    private Usuario usuarioLogeado;

    public static ManejoUsuarios getInstancia() {
        return instManejoUsuarios;
    }

    private ManejoUsuarios() {
        usuarios.add(new Administrador("Admin Default", "admin", "supersecreto", 30));
    }

    public Usuario getUsuarioLogeado() {
        return usuarioLogeado;
    }

    public ArrayList<Usuario> getUsuarios() {
        return usuarios;
    }

    public Usuario buscarUsuario(String usuario) {
        for (Usuario u : usuarios) {
            if (!u.getEliminado() && u.getUsuario().equalsIgnoreCase(usuario)) {
                return u;
            }
        }
        return null;
    }

    public boolean contraseniaValida(String contrasenia) {
        if (contrasenia == null || contrasenia.length() < 8) {
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
        if (u != null && u.verificarContrasenia(contrasenia) && u.getEliminado() == false) {
            usuarioLogeado = u;
            return true;
        }
        return false;
    }

    public boolean registrarUsuario(String rol, String nombre, String usuario, String contrasenia, int edad) {
        if (nombre == null || nombre.isBlank()) {
            return false;
        }
        if (usuario == null || usuario.isBlank()) {
            return false;
        }
        if (edad <= 0) {
            return false;
        }
        if (!contraseniaValida(contrasenia)) {
            return false;
        }
        if (buscarUsuario(usuario) != null) {
            return false;
        }

        switch (rol.toLowerCase()) {
            case "administrador":
                usuarios.add(new Administrador(nombre, usuario, contrasenia, edad));
                return true;
            case "contenido":
                usuarios.add(new Contenido(nombre, usuario, contrasenia, edad));
                return true;
            case "limitado":
                usuarios.add(new Limitado(nombre, usuario, contrasenia, edad));
                return true;
            default:
                return false;
        }
    }

    public boolean eliminarUsuario(String usuario) {
        Usuario u = buscarUsuario(usuario);
        if (u != null) {
            if (u.getUsuario().equalsIgnoreCase("admin")) { //admin no se puede eliminar (supongo)
                return false;
            }
            u.setEliminado();
            return true;
        }
        return false;
    }
}
