package java_ticket_edwin_portillo;

import Usuarios.Limitado;
import Usuarios.Contenido;
import Usuarios.Administrador;
import Usuarios.Usuario;
import java.util.ArrayList;

public class ManejoUsuarios {

    public static Usuario usuarioLogeado;
    public static ArrayList<Usuario> usuarios = new ArrayList<>();

    private static boolean adminInicializado = false;

    public ManejoUsuarios() {
        if (!adminInicializado) {
            Usuario admin = new Administrador("Admin Default", "admin", "supersecreto", 30);
            usuarios.add(admin);
            adminInicializado = true;
        }
    }

    public Usuario buscarUsuario(String usuario) {
        for (Usuario u : usuarios) {
            if (u.getUsuario().equals(usuario) && u.getEliminado() == false) {
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
        if (u != null && u.verificarContrasenia(contrasenia) && u.getEliminado() == false) {
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
            if (u.getUsuario().equalsIgnoreCase("admin")) { //admin no se puede eliminar
                return false;
            }
            u.setEliminado();
            return true;
        }
        return false;
    }

    public boolean editarUsuario(String usuarioEvaluar, String nuevoNombre, String nuevaContrasenia, int nuevaEdad) {
        Usuario u = buscarUsuario(usuarioEvaluar);
        if (u != null) {
            if (u.getUsuario().equalsIgnoreCase("admin")) { //no se puede editar admin
                return false;
            }

            if (!contraseniaValida(nuevaContrasenia) || nuevaEdad <= 0) {
                return false;
            }

            u.setNombre(nuevoNombre);
            u.setContrasenia(nuevaContrasenia);
            u.setEdad(nuevaEdad);
            return true;
        }
        return false;
    }

}
