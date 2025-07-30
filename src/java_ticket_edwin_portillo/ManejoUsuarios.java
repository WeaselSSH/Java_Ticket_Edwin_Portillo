package java_ticket_edwin_portillo;
import java.util.ArrayList;

public class ManejoUsuarios {
    public static ArrayList<Usuario> usuarios;
    
    public ManejoUsuarios() {
        usuarios = new ArrayList<>();
        
        Usuario admin = new Administrador("Admin Default", "admin", "supersecreto", 30);
        usuarios.add(admin);
    }
}
