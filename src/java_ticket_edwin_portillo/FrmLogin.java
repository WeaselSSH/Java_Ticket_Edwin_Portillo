package java_ticket_edwin_portillo;

import Usuarios.Usuario;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JOptionPane;

public class FrmLogin extends BaseFrame {
    
    private final ManejoUsuarios manejoUsuarios = ManejoUsuarios.getInstancia();

    public FrmLogin() {
        super("Inicio de Sesi�n", 400, 260);
    }

    @Override
    protected void initComponents() {
        //panel principal
        JPanel panelPrincipal = new JPanel(new BorderLayout());

        //resto de paneles
        JPanel panelNorte = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 15));
        panelNorte.setPreferredSize(new Dimension(0, 40));
        panelPrincipal.add(panelNorte, BorderLayout.NORTH);

        JPanel panelSur = new JPanel(null);
        panelSur.setPreferredSize(new Dimension(0, 90));
        panelPrincipal.add(panelSur, BorderLayout.SOUTH);

        JPanel panelCentro = new JPanel(null);
        panelPrincipal.add(panelCentro, BorderLayout.CENTER);

        //resto de UI
         JLabel lblTitulo = crearLabel("INICIO DE SESION", 0, 0, 0, 0, Font.BOLD, 24f);
        panelNorte.add(lblTitulo);

        JLabel lblUsuario = crearLabel("Nombre de Usuario:", 40, 25, 150, 25, Font.BOLD, 14f);
        panelCentro.add(lblUsuario);

        JTextField txtUsuario = crearTextField(190, 29, 150, 20);
        panelCentro.add(txtUsuario);

        JLabel lblContrasenia = crearLabel("Contrase�a:", 40, 60, 150, 25, Font.BOLD, 14f);
        panelCentro.add(lblContrasenia);

        JPasswordField txtContrasenia = new JPasswordField();
        txtContrasenia.setBounds(140, 65, 200, 20);
        panelCentro.add(txtContrasenia);

        JButton btnInicio = crearBoton("Iniciar Sesi�n", 40, 20, 140, 35);
        panelSur.add(btnInicio);

        JButton btnSalir = crearBoton("Salir", 200, 20, 140, 35);
        panelSur.add(btnSalir);

        //Acciones
        btnInicio.addActionListener(e -> {
            String contrasenia = new String(txtContrasenia.getPassword());
            String NombreUsuario = txtUsuario.getText();
            
            if (NombreUsuario.trim().isEmpty() || contrasenia.trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Error: uno de los campos se encuentra vac�o.");
                return;
            }

            if (manejoUsuarios.iniciarSesion(NombreUsuario, contrasenia)) {
                Usuario usuario = manejoUsuarios.getUsuarioLogeado();

                switch (usuario.getRol().toLowerCase()) {
                    case "administrador":
                        new FrmMenuAdmin().setVisible(true);
                        this.dispose();
                        break;
                    case "contenido":
                        new FrmMenuContenido().setVisible(true);
                        this.dispose();
                        break;
                    case "limitado":
                        new FrmMenuLimitado().setVisible(true);
                        this.dispose();
                        break;
                }

            } else {
                JOptionPane.showMessageDialog(this, "Error: Usuario o contrase�a incorrectos.");
            }
        });

        btnSalir.addActionListener(e -> {
            System.exit(0);
        });

        setContentPane(panelPrincipal);
    }

    public static void main(String[] args) {
        new FrmLogin().setVisible(true);
    }
}
